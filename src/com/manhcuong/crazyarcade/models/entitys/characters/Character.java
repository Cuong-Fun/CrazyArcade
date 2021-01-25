package com.manhcuong.crazyarcade.models.entitys.characters;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.entitys.Sound;
import com.manhcuong.crazyarcade.models.entitys.bombs.Bomb;
import com.manhcuong.crazyarcade.models.entitys.bombs.ShadowBomb;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;
import com.manhcuong.crazyarcade.models.entitys.objects.Movable;
import com.manhcuong.crazyarcade.models.entitys.monsters.Monster;
import org.jetbrains.annotations.NotNull;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public abstract class Character extends Movable {
  public static final int DEAD = 4;
  protected List<Bomb> bombList = new ArrayList<>();
  protected int numOfBombsMax;
  protected int numOfBomb;
  protected long lastTimeToPutBombs;
  protected int delayPutBombs = Constants.SIZE_ITEM * 4;
  protected int rangeExplosive;
  protected int rangeExplosiveMax;
  protected int delayMax;
  protected boolean isDeath;

  public Character() {
    super();
    width = (Constants.SIZE_ITEM * 9) / 8 - Constants.SIZE_ITEM / 3;
    height = (Constants.SIZE_ITEM * 13) / 8 - Constants.SIZE_ITEM / 3;
  }

  @Override
  public void draw(@NotNull Graphics2D graphics2D) {
    for (int i = 0; i < bombList.size(); i++) {
      bombList.get(i).draw(graphics2D);
    }
    super.draw(graphics2D);
  }

  public void move(ImpedimentItem[][] impedimentItems, List<Monster> monsterList) {
    if (isCollisionWithMonster(monsterList)) {
      isDeath = true;
      image = images[DEAD];
      return;
    }
    super.move(impedimentItems);
  }

  protected boolean isCollisionWithMonster(List<Monster> monsterList) {
    Rectangle rectObject;
    int y = this.y + (height * 2) / 3;
    int height = this.height / 3;
    switch (orientation) {
      case UP -> rectObject = new Rectangle(x, y - 2, width, height);
      case RIGHT -> rectObject = new Rectangle(x + 2, y, width, height);
      case DOWN -> rectObject = new Rectangle(x, y + 2, width, height);
      default -> rectObject = new Rectangle(x - 2, y, width, height);
    }

    // handling collision
    for (Monster monster : monsterList) {
      Rectangle rectMonster = new Rectangle(monster.getX(), monster.getY(), monster.getWidth(), monster.getHeight());
      if (rectObject.intersects(rectMonster)) {
        new Sound(Sound.DEATH).play();
        return true;
      }
    }
    return false;
  }

  public void putBombs() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastTimeToPutBombs < delayPutBombs) return;
    if (bombList.size() < numOfBomb) {
      createBombs();
      lastTimeToPutBombs = currentTime;
    }
  }

  protected void createBombs() {
    int positionX = x + width / 2;
    int positionY = y + height - Constants.SIZE_ITEM / 3;
    int column = positionX / Constants.SIZE_ITEM;
    int row = positionY / Constants.SIZE_ITEM;
    int x = Constants.SIZE_ITEM * column;
    int y = Constants.SIZE_ITEM * row;
    for (Bomb bomb : bombList) {
      if (bomb.getX() == x && bomb.getY() == y) {
        return;
      }
    }
    Bomb bomb = new ShadowBomb();
    bomb.setX(x);
    bomb.setY(y);
    bomb.setCreatedTime(System.currentTimeMillis());
    bomb.setRange(rangeExplosive);
    bombList.add(bomb);
  }

  public void explosiveBombs(ImpedimentItem[][] impedimentItems, List<Monster> monsterList) {
    bombList.removeIf(bomb -> bomb.explosive(impedimentItems, monsterList, this));
  }

  public void animationBomb() {
    for (Bomb bomb : bombList) {
      bomb.dance();
    }
  }

  public List<Bomb> getBombList() {
    return bombList;
  }

  public void setDeath(boolean death) {
    isDeath = death;
    if (isDeath) {
      image = images[DEAD];
    }
  }

  public boolean isDeath() {
    return isDeath;
  }
}
