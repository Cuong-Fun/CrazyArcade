package com.manhcuong.crazyarcade.models.entitys.characters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Boz extends Character {

  public Boz() {
    super();
    delay = 15;
    delayMax = 6;
    numOfBombsMax = 6;
    numOfBomb = 1;
    rangeExplosiveMax = 7;
    rangeExplosive = 1;
  }

  @Override
  protected void initImage() {
    images = new Image[5];
    images[UP] = new ImageIcon(Boz.class.getResource("/assets/images/characters/boz/boz_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Boz.class.getResource("/assets/images/characters/boz/boz_right.png")).getImage();
    images[DOWN] = new ImageIcon(Boz.class.getResource("/assets/images/characters/boz/boz_down.png")).getImage();
    images[LEFT] = new ImageIcon(Boz.class.getResource("/assets/images/characters/boz/boz_left.png")).getImage();
    images[DEAD] = new ImageIcon(Boz.class.getResource("/assets/images/characters/boz/boz_dead.png")).getImage();
  }
}
