package com.manhcuong.crazyarcade.models.entitys.bombs;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class DefaultBomb extends Bomb {

  @Override
  protected void initImage() {
    images = new Image[3];
    images[0] = cropImage(0);
    images[1] = cropImage(1);
    images[2] = cropImage(2);
    image = images[0];
  }

  protected BufferedImage cropImage(int index) {
    URL url = ShadowBomb.class.getResource("/assets/images/bombs/default_bomb.png");
    try {
      BufferedImage originalImg = ImageIO.read(url);
      int width = originalImg.getWidth() / 3;
      return originalImg.getSubimage(index * width, 0, width, originalImg.getHeight());
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }
}
