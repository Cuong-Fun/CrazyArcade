package com.manhcuong.crazyarcade.models.entitys;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Map {
  public static final String MAP_1 = "map_1";
  public static final String MAP_2 = "map_2";

  public Map() {

  }

  public ImpedimentItem[][] readMap(String mapName) {
    ImpedimentItem[][] items = new ImpedimentItem[Constants.ROW][Constants.COLUMN];
    try {
      URL url = Map.class.getResource("/assets/maps/" + mapName + ".txt");
      InputStream inputStream = url.openStream();
      InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      int row = 0;
      while ((line = bufferedReader.readLine()) != null) {
        for (int i = 0; i < line.length(); i++) {
          int type = line.charAt(i) - '0';
          ImpedimentItem item = new ImpedimentItem();
          item.setX(Constants.SIZE_ITEM * (i + 1));
          item.setY(Constants.SIZE_ITEM * (row + 1));
          item.setWidth(Constants.SIZE_ITEM);
          item.setHeight(Constants.SIZE_ITEM);
          item.setType(type);
          item.setImage(type);
          items[row][i] = item;
        }
        row++;
      }
      bufferedReader.close();
      inputStreamReader.close();
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return items;
  }
}
