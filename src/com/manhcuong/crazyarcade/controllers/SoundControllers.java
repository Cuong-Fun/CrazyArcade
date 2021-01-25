package com.manhcuong.crazyarcade.controllers;

import com.manhcuong.crazyarcade.models.entitys.Sound;

import java.util.HashMap;
import java.util.Map;

public class SoundControllers {
  private static SoundControllers controllers;
  private HashMap<String, Sound> soundHashMap;

  private SoundControllers() {
    soundHashMap = new HashMap<>();
    soundHashMap.put(Sound.GAME, new Sound(Sound.GAME));
    soundHashMap.put(Sound.HOME, new Sound(Sound.HOME));
  }

  public static SoundControllers getInstance() {
    return controllers == null ? controllers = new SoundControllers() : controllers;
  }

  public Sound get(String key) {
    return soundHashMap.get(key);
  }

  public void stop() {
    for(Map.Entry<String, Sound> entry : soundHashMap.entrySet()) {
      entry.getValue().stop();
    }
  }

  public void close() {
    for(Map.Entry<String, Sound> entry : soundHashMap.entrySet()) {
      entry.getValue().stop();
      entry.getValue().close();
    }
  }

  public HashMap<String, Sound> getSoundHashMap() {
    return soundHashMap;
  }

  public void setSoundHashMap(HashMap<String, Sound> soundHashMap) {
    this.soundHashMap = soundHashMap;
  }
}
