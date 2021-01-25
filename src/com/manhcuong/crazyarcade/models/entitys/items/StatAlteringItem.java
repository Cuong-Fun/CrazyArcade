package com.manhcuong.crazyarcade.models.entitys.items;

import javax.swing.ImageIcon;
import java.net.URL;

public class StatAlteringItem extends Item {
  public static final int ROLLER_SKATES = 1;
  public static final int WATER_FLASK = 2;
  public static final int WATER_BALLOON = 3;
  public static final int ULTRA_BALLOON = 4;

  public void setImage(int type) {
    URL url = null;
    switch (type) {
      case ROLLER_SKATES -> url = Item.class.getResource("/assets/images/items/roller_skates.png");
      case WATER_FLASK -> url = Item.class.getResource("/assets/images/items/water_flask.png");
      case WATER_BALLOON -> url = Item.class.getResource("/assets/images/items/water_balloon.png");
      case ULTRA_BALLOON -> url = Item.class.getResource("/assets/images/items/ultra_balloon.png");
    }
    if (url != null) {
      image = new ImageIcon(url).getImage();
    }
  }
}
