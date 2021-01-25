package com.manhcuong.crazyarcade.models.entitys;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.IOException;

public class Sound {
  public static final String HOME = "home";
  public static final String GAME = "game";
  public static final String EXPLOSIVE = "explosive";
  public static final String WIN = "win";
  public static final String DEATH = "die";
  private final String path;
  private Clip clip;
  private AudioInputStream audioInputStream;

  public Sound(String soundName) {
    path = "/assets/sounds/" + soundName + ".wav";
    load();
  }

  private void load() {
    try {
      audioInputStream = AudioSystem.getAudioInputStream(Sound.class.getResource(path));
      clip = AudioSystem.getClip();
      clip.open(audioInputStream);
    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
      e.printStackTrace();
    }
  }

  public void play() {
    clip.start();
  }

  public void loop() {
    clip.loop(-1);
  }

  public void stop() {
    clip.stop();
  }

  public void close() {
    try {
      clip.close();
      audioInputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
