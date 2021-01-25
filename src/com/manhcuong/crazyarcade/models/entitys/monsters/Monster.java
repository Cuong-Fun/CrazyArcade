package com.manhcuong.crazyarcade.models.entitys.monsters;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.entitys.Sound;
import com.manhcuong.crazyarcade.models.entitys.bombs.Bomb;
import com.manhcuong.crazyarcade.models.entitys.characters.Character;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;
import com.manhcuong.crazyarcade.models.entitys.objects.Movable;

import java.awt.Rectangle;
import java.util.List;
import java.util.Random;

public abstract class Monster extends Movable {

  public Monster() {
    super();
    width = height = Constants.SIZE_ITEM - Constants.SIZE_ITEM / 3;
  }

  public void move(ImpedimentItem[][] impedimentItems, List<Bomb> bombList, Character player) {
    if (isCollisionWithPlayer(player)) {
      player.setDeath(true);
    }
    if (!isCollisionWithItem(impedimentItems, y, height) || !isCollisionWithBomb(bombList)) {
      setOrientation((new Random().nextInt(4)));
      return;
    }
    switch (orientation) {
      case UP -> {
        if (y > Constants.SIZE_ITEM + Constants.SIZE_ITEM / 5) {
          move();
          return;
        }
      }
      case RIGHT -> {
        if (x < Constants.WIDTH_MAP + Constants.SIZE_ITEM / 5) {
          move();
          return;
        }
      }
      case DOWN -> {
        if (y < Constants.HEIGHT_MAP + Constants.SIZE_ITEM / 5) {
          move();
          return;
        }
      }
      case LEFT -> {
        if (x > Constants.SIZE_ITEM + Constants.SIZE_ITEM / 10) {
          move();
          return;
        }
      }
    }
    setOrientation((new Random().nextInt(4)));
  }

  protected boolean isCollisionWithBomb(List<Bomb> bombList) {
    if (bombList.size() == 0) return true;
    Rectangle rectObject;
    switch (orientation) {
      case UP -> rectObject = new Rectangle(x, y - 2, width, height);
      case RIGHT -> rectObject = new Rectangle(x + 2, y, width, height);
      case DOWN -> rectObject = new Rectangle(x, y + 2, width, height);
      default -> rectObject = new Rectangle(x - 2, y, width, height);
    }
    for (Bomb bomb : bombList) {
      Rectangle rectBomb = new Rectangle(bomb.getX(), bomb.getY(), bomb.getWidth(), bomb.getHeight());
      if (rectObject.intersects(rectBomb)) {
        return false;
      }
    }
    return true;
  }

  protected boolean isCollisionWithPlayer(Character player) {
    Rectangle rectMonster;
    switch (orientation) {
      case UP -> rectMonster = new Rectangle(x, y - 2, width, height);
      case RIGHT -> rectMonster = new Rectangle(x + 2, y, width, height);
      case DOWN -> rectMonster = new Rectangle(x, y + 2, width, height);
      default -> rectMonster = new Rectangle(x - 2, y, width, height);
    }
    int y = player.getY() + (player.getHeight() * 2) / 3;
    int height = player.getHeight() / 3;
    Rectangle rectPlayer = new Rectangle(player.getX(), y, player.getWidth(), height);
    if (rectMonster.intersects(rectPlayer)) {
      new Sound(Sound.DEATH).play();
      return true;
    }
    return false;
  }
}
