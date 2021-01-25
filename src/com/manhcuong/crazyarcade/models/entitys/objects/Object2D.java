package com.manhcuong.crazyarcade.models.entitys.objects;

import org.jetbrains.annotations.NotNull;

import java.awt.Image;
import java.awt.Graphics2D;

public class Object2D {
  protected int x;
  protected int y;
  protected int width;
  protected int height;
  protected Image image;

  public void draw(@NotNull Graphics2D graphics2D) {
    graphics2D.drawImage(image, x, y, width, height, null);
//    graphics2D.drawRect(x, y, width, height);
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }

  public int getWidth() {
    return width;
  }

  public void setWidth(int width) {
    this.width = width;
  }

  public int getHeight() {
    return height;
  }

  public void setHeight(int height) {
    this.height = height;
  }

  public Image getImage() {
    return image;
  }

  public void setImage(Image image) {
    this.image = image;
  }
}
