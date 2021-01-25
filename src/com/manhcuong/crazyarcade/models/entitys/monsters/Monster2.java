package com.manhcuong.crazyarcade.models.entitys.monsters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Monster2 extends Monster {

  public Monster2() {
    super();
    delay = 25;
  }

  @Override
  protected void initImage() {
    images = new Image[4];
    images[UP] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_2_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_2_right.png")).getImage();
    images[DOWN] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_2_down.png")).getImage();
    images[LEFT] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_2_left.png")).getImage();
  }
}
