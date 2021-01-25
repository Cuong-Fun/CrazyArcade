package com.manhcuong.crazyarcade.models.entitys.bombs;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.entitys.Animation;
import com.manhcuong.crazyarcade.models.entitys.Sound;
import com.manhcuong.crazyarcade.models.entitys.characters.Character;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;
import com.manhcuong.crazyarcade.models.entitys.objects.Object2D;
import com.manhcuong.crazyarcade.models.entitys.monsters.Monster;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class Bomb extends Object2D {
  protected Image[] images;
  protected long createdTime;
  protected int delayExplosive = 2800;
  protected int range;
  private boolean isExplosive;

  private final List<Animation> animationList;
  private Animation danceAnimation;

  public Bomb() {
    initImage();
    width = height = Constants.SIZE_ITEM;
    animationList = new ArrayList<>();
  }

  protected abstract void initImage();

  @Override
  public void draw(@NotNull Graphics2D graphics2D) {
    if (isExplosive) {
      super.draw(graphics2D);
    } else {
      danceAnimation.draw(graphics2D);
    }
    for (int i = 0; i < animationList.size(); i++) {
      graphics2D.drawImage(
          animationList.get(i).getImage(),
          animationList.get(i).getX(),
          animationList.get(i).getY(),
          animationList.get(i).getWidth(),
          animationList.get(i).getHeight(),
          null
      );
    }
  }

  private void initDanceAnimation() {
    danceAnimation = new Animation();
    danceAnimation.setX(x);
    danceAnimation.setY(y);
    danceAnimation.setWidth(width);
    danceAnimation.setHeight(height);
    danceAnimation.setDeltaTime(200);
    List<Image> imageList = new ArrayList<>(Arrays.asList(images));
    danceAnimation.setImageList(imageList);
    danceAnimation.updateAnimation();
  }

  public void dance() {
    if (!isExplosive) {
      if (danceAnimation == null) {
        initDanceAnimation();
      }
      danceAnimation.updateAnimation();
    }
  }

  public boolean explosive(ImpedimentItem[][] impedimentItems, List<Monster> monsterList, Character player) {
    long currentTime = System.currentTimeMillis();
    int size = animationList.size();
    if (size > 0) {
      long timeDraw = animationList.get(size - 1).getTimeDraw();
      int deltaTime = animationList.get(size - 1).getDeltaTime();
      return currentTime - timeDraw >= deltaTime;
    }
    if (currentTime - createdTime >= delayExplosive) {
      handlingExplosive(impedimentItems, monsterList, player);
      handlingEffect();
      isExplosive = true;
    }
    return false;
  }

  private void handlingExplosive(ImpedimentItem[][] impedimentItems, List<Monster> monsterList, Character player) {
    int column = x / Constants.SIZE_ITEM - 1;
    int row = y / Constants.SIZE_ITEM - 1;

    // handling explosive UP direction
    for (int i = row - 1; i >= row - range; i--) {
      if (i >= 0) {
        if (handling(impedimentItems[i][column], monsterList, player)) {
          break;
        }
        animationList.add(createExplosiveAnimation(column, i, Character.UP));
      }
    }

    // handling explosive RIGHT direction
    for (int i = column + 1; i <= column + range; i++) {
      if (i < Constants.COLUMN) {
        if (handling(impedimentItems[row][i], monsterList, player)) {
          break;
        }
        animationList.add(createExplosiveAnimation(i, row, Character.RIGHT));
      }
    }

    // handling explosive DOWN direction
    for (int i = row + 1; i <= row + range; i++) {
      if (i < Constants.ROW) {
        if (handling(impedimentItems[i][column], monsterList, player)) {
          break;
        }
        animationList.add(createExplosiveAnimation(column, i, Character.DOWN));
      }
    }

    // handling explosive LEFT direction
    for (int i = column - 1; i >= column - range; i--) {
      if (i >= 0) {
        if (handling(impedimentItems[row][i], monsterList, player)) {
          break;
        }
        animationList.add(createExplosiveAnimation(i, row, Character.LEFT));
      }
    }
  }

  private boolean handling(ImpedimentItem item, List<Monster> monsterList, Character player) {
    int type = item.getType();
    if (type == ImpedimentItem.HOUSE || type == ImpedimentItem.STONE) return true;
    if (type == ImpedimentItem.BLOCK) {
      item.setType(1);
      return true;
    }
    Rectangle rect = new Rectangle(item.getX(), item.getY(), Constants.SIZE_ITEM, Constants.SIZE_ITEM);
    Rectangle rectPlayer = new Rectangle(
        player.getX() - 5, player.getY() + (player.getHeight() * 2) / 3 - 5, player.getWidth() - 10, player.getHeight() / 3
    );
    if (rect.intersects(rectPlayer)) {
      player.setDeath(true);
    }
    Iterator<Monster> monsterIterator = monsterList.listIterator();
    while (monsterIterator.hasNext()) {
      Monster monster = monsterIterator.next();
      Rectangle rectMonster = new Rectangle(monster.getX(), monster.getY(), Constants.SIZE_ITEM, Constants.SIZE_ITEM);
      if (rect.intersects(rectMonster)) {
        monsterIterator.remove();
      }
    }
    return false;
  }

  private void handlingEffect() {
    new Sound(Sound.EXPLOSIVE).play();
    try {
      URL url = getClass().getResource("/assets/images/effects/bomb_explosive.png");
      BufferedImage originalImg = ImageIO.read(url);
      image = originalImg.getSubimage(0, 0, originalImg.getWidth() / 4, originalImg.getHeight());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Animation createExplosiveAnimation(int column, int row, int orientation) {
    Animation animation = new Animation();
    List<Image> explosiveEffect = new ArrayList<>();
    String path = "/assets/images/effects/bomb_explosive_";
    switch (orientation) {
      case Character.UP -> path += "up.png";
      case Character.RIGHT -> path += "right.png";
      case Character.DOWN -> path += "down.png";
      case Character.LEFT -> path += "left.png";
    }
    explosiveEffect.add(new ImageIcon(Bomb.class.getResource(path)).getImage());
    animation.setImageList(explosiveEffect);
    animation.setX(Constants.SIZE_ITEM * (column + 1));
    animation.setY(Constants.SIZE_ITEM * (row + 1));
    animation.setWidth(Constants.SIZE_ITEM);
    animation.setHeight(Constants.SIZE_ITEM);
    animation.setDeltaTime(150);
    animation.updateAnimation();
    return animation;
  }

  public void setCreatedTime(long createdTime) {
    this.createdTime = createdTime;
  }

  public void setRange(int range) {
    this.range = range;
  }
}
