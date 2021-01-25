package com.manhcuong.crazyarcade.models.entitys.characters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Dao extends Character {

  public Dao() {
    super();
    delay = 15;
    delayMax = 8;
    numOfBombsMax = 10;
    numOfBomb = 1;
    rangeExplosiveMax = 7;
    rangeExplosive = 1;
  }

  @Override
  protected void initImage() {
    images = new Image[5];
    images[UP] = new ImageIcon(Dao.class.getResource("/assets/images/characters/dao/dao_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Dao.class.getResource("/assets/images/characters/dao/dao_right.png")).getImage();
    images[DOWN] = new ImageIcon(Dao.class.getResource("/assets/images/characters/dao/dao_down.png")).getImage();
    images[LEFT] = new ImageIcon(Dao.class.getResource("/assets/images/characters/dao/dao_left.png")).getImage();
    images[DEAD] = new ImageIcon(Dao.class.getResource("/assets/images/characters/dao/dao_dead.png")).getImage();
  }
}
