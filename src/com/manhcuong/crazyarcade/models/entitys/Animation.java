package com.manhcuong.crazyarcade.models.entitys;

import com.manhcuong.crazyarcade.models.entitys.objects.Object2D;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Animation extends Object2D {
  protected List<Image> imageList = new ArrayList<>();
  protected int deltaTime;
  protected long timeDraw;
  protected int currentIndex;

  public void updateAnimation() {
    long currentTime = System.currentTimeMillis();
    if (currentTime - timeDraw >= deltaTime) {
      currentIndex = (currentIndex + 1) % imageList.size();
      image = imageList.get(currentIndex);
      timeDraw = currentTime;
    }
  }

  public void setImageList(List<Image> imageList) {
    this.imageList = imageList;
  }

  public int getDeltaTime() {
    return deltaTime;
  }

  public void setDeltaTime(int deltaTime) {
    this.deltaTime = deltaTime;
  }

  public long getTimeDraw() {
    return timeDraw;
  }
}
