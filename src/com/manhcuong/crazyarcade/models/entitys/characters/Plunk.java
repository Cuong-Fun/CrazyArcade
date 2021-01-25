package com.manhcuong.crazyarcade.models.entitys.characters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Plunk extends Character {

  public Plunk() {
    super();
    delay = 16;
    delayMax = 7;
    numOfBombsMax = 8;
    numOfBomb = 1;
    rangeExplosiveMax = 8;
    rangeExplosive = 1;
  }
  @Override
  protected void initImage() {
    images = new Image[5];
    images[UP] = new ImageIcon(Plunk.class.getResource("/assets/images/characters/plunk/plunk_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Plunk.class.getResource("/assets/images/characters/plunk/plunk_right.png")).getImage();
    images[DOWN] = new ImageIcon(Plunk.class.getResource("/assets/images/characters/plunk/plunk_down.png")).getImage();
    images[LEFT] = new ImageIcon(Plunk.class.getResource("/assets/images/characters/plunk/plunk_left.png")).getImage();
    images[DEAD] = new ImageIcon(Plunk.class.getResource("/assets/images/characters/plunk/plunk_dead.png")).getImage();
  }
}
