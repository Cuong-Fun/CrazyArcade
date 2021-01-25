package com.manhcuong.crazyarcade.models.entitys.characters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Ike extends Character {

  public Ike() {
    super();
    delay = 15;
    delayMax = 7;
    numOfBombsMax = 6;
    numOfBomb = 1;
    rangeExplosiveMax = 7;
    rangeExplosive = 2;
  }

  @Override
  protected void initImage() {
    images = new Image[5];
    images[UP] = new ImageIcon(Ike.class.getResource("/assets/images/characters/ike/ike_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Ike.class.getResource("/assets/images/characters/ike/ike_right.png")).getImage();
    images[DOWN] = new ImageIcon(Ike.class.getResource("/assets/images/characters/ike/ike_down.png")).getImage();
    images[LEFT] = new ImageIcon(Ike.class.getResource("/assets/images/characters/ike/ike_left.png")).getImage();
    images[DEAD] = new ImageIcon(Ike.class.getResource("/assets/images/characters/ike/ike_dead.png")).getImage();
  }
}
