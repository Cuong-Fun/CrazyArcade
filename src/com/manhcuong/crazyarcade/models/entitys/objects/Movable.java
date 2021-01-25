package com.manhcuong.crazyarcade.models.entitys.objects;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public abstract class Movable extends Object2D {
  public static final int UP = 0;
  public static final int RIGHT = 1;
  public static final int DOWN = 2;
  public static final int LEFT = 3;
  protected final int STEP = 2;

  protected Image[] images;
  protected int orientation;
  protected int delay;
  protected long lastTimeToMove;

  public Movable() {
    initImage();
  }

  protected abstract void initImage();

  protected void move() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - lastTimeToMove < delay) return;
    switch (orientation) {
      case UP -> y -= STEP;
      case RIGHT -> x += STEP;
      case DOWN -> y += STEP;
      case LEFT -> x -= STEP;
    }
    lastTimeToMove = currentTime;
  }

  public boolean move(ImpedimentItem[][] impedimentItems) {
    if (isCollisionWithItem(impedimentItems, y + (height * 2) / 3, height / 3)) {
      switch (orientation) {
        case UP -> {
          if (y > Constants.SIZE_ITEM / 3) {
            move();
            return true;
          }
        }
        case RIGHT -> {
          if (x < Constants.WIDTH_MAP + Constants.SIZE_ITEM / 9) {
            move();
            return true;
          }
        }
        case DOWN -> {
          if (y < Constants.HEIGHT_MAP - (Constants.SIZE_ITEM * 4) / 9) {
            move();
            return true;
          }
        }
        case LEFT -> {
          if (x > Constants.SIZE_ITEM) {
            move();
            return true;
          }
        }
      }
    }
    return false;
  }

  protected boolean isCollisionWithItem(ImpedimentItem[][] impedimentItems, int y, int height) {
    Rectangle rectObject;
    switch (orientation) {
      case UP -> rectObject = new Rectangle(x, y - 2, width, height);
      case RIGHT -> rectObject = new Rectangle(x + 2, y, width, height);
      case DOWN -> rectObject = new Rectangle(x, y + 2, width, height);
      default -> rectObject = new Rectangle(x - 2, y, width, height);
    }

    // handling collision
    for (ImpedimentItem[] item : impedimentItems) {
      for (int i = 0; i < impedimentItems[0].length; i++) {
        if (item[i].getType() > 1) {
          Rectangle rectangleItem = new Rectangle(item[i].getX(), item[i].getY(), item[i].getWidth(), item[i].getHeight());
          if (rectObject.intersects(rectangleItem)) {
            Rectangle rectangle = new Rectangle();
            Rectangle2D.intersect(rectangleItem, rectObject, rectangle);
            int delta = Constants.SIZE_ITEM / 10;
            if (rectangle.getWidth() <= delta && rectangle.getHeight() <= delta) {
              switch (orientation) {
                case UP, DOWN -> {
                  if (x <= rectangleItem.getX()) {
                    this.x -= 2;
                  } else this.x += 2;
                }
                case LEFT, RIGHT -> {
                  if (y <= rectangleItem.getY()) {
                    this.y -= 2;
                  } else this.y += 2;
                }
              }
            }
            return false;
          }
        }
      }
    }
    return true;
  }

  public int getOrientation() {
    return orientation;
  }

  public void setOrientation(int orientation) {
    this.orientation = orientation;
    image = images[orientation];
  }

  public int getDelay() {
    return delay;
  }

  public void setDelay(int delay) {
    this.delay = delay;
  }
}
