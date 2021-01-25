package com.manhcuong.crazyarcade.models.entitys.bombs;

import com.manhcuong.crazyarcade.Constants;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class ShadowBomb extends Bomb {

  public ShadowBomb() {
    super();
  }

  @Override
  protected void initImage() {
    images = new Image[3];
    images[0] = cropImage(0);
    images[1] = cropImage(1);
    images[2] = cropImage(2);
    image = images[0];
  }

  protected BufferedImage cropImage(int index) {
    URL url = ShadowBomb.class.getResource("/assets/images/bombs/shadow_bomb.png");
    try {
      BufferedImage originalImg = ImageIO.read(url);
      int width = originalImg.getWidth() / 3;
      return originalImg.getSubimage(index * width + Constants.SIZE_ITEM / 4,
          Constants.SIZE_ITEM / 4,
          width - Constants.SIZE_ITEM / 2,
          originalImg.getHeight() - Constants.SIZE_ITEM / 2);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
