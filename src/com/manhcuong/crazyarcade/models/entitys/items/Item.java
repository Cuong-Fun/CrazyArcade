package com.manhcuong.crazyarcade.models.entitys.items;

import com.manhcuong.crazyarcade.models.entitys.objects.Object2D;

public class Item extends Object2D {
  protected int type;

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }
}
