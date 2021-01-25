package com.manhcuong.crazyarcade.models.entitys.monsters;

import javax.swing.ImageIcon;
import java.awt.Image;

public class Monster1 extends Monster {

  public Monster1() {
    super();
    delay = 30;
  }

  @Override
  protected void initImage() {
    images = new Image[4];
    images[UP] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_1_up.png")).getImage();
    images[RIGHT] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_1_right.png")).getImage();
    images[DOWN] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_1_down.png")).getImage();
    images[LEFT] = new ImageIcon(Monster.class.getResource("/assets/images/monsters/monster_1_left.png")).getImage();
  }
}
