package com.manhcuong.crazyarcade.models.entitys.items;

import com.manhcuong.crazyarcade.controllers.GameControllers;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ImpedimentItem extends Item {
  public static final int GROUND0 = 0;
  public static final int GROUND1 = 1;
  public static final int BLOCK = 2;
  public static final int HOUSE = 3;
  public static final int STONE = 4;

  public void setImage(int type) {
    URL url = null;
    switch (type) {
      case GROUND0, GROUND1 -> url = Item.class.getResource("/assets/images/items/SandGround.png");
      case BLOCK -> url = Item.class.getResource("/assets/images/items/SandBlockBuff.png");
      case HOUSE -> url = Item.class.getResource("/assets/images/items/SandHouseBlue.png");
      case STONE -> url = Item.class.getResource("/assets/images/items/SandStone.png");
    }
    if (url != null) {
      if (type < 2) {
        try {
          BufferedImage originalImg = ImageIO.read(
              GameControllers.class.getResource("/assets/images/items/SandGround.png")
          );
          int width = originalImg.getWidth() / 2;
          image = originalImg.getSubimage(type * width, 0, width, originalImg.getHeight());
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        image = new ImageIcon(url).getImage();
      }
    }
  }

  @Override
  public void setType(int type) {
    super.setType(type);
    setImage(type);
  }
}
