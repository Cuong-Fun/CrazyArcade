package com.manhcuong.crazyarcade.controllers;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.IRoutes;
import com.manhcuong.crazyarcade.models.entitys.Map;
import com.manhcuong.crazyarcade.models.entitys.Sound;
import com.manhcuong.crazyarcade.models.entitys.characters.Character;
import com.manhcuong.crazyarcade.models.entitys.characters.Ike;
import com.manhcuong.crazyarcade.models.entitys.items.ImpedimentItem;
import com.manhcuong.crazyarcade.models.entitys.monsters.Monster;
import com.manhcuong.crazyarcade.models.entitys.monsters.Monster1;
import com.manhcuong.crazyarcade.models.entitys.monsters.Monster2;
import com.manhcuong.crazyarcade.models.entitys.objects.Movable;
import com.hieubui.crazyarcade.models.entitys.monsters.*;
import com.manhcuong.crazyarcade.views.screens.GameScreen;

import javax.swing.JOptionPane;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class GameControllers implements Constants, KeyListener, ActionListener {
  private GameScreen view;
  private IRoutes routes;
  private final SoundControllers soundControllers = SoundControllers.getInstance();
  private ImpedimentItem[][] items;
  private List<Monster> monsterList;
  private Character player;
  private boolean isUp, isRight, isDown, isLeft, isSpace;
  private boolean isRunning, isWin;
  private int time;
  private long lastTime;
  private int level = 1;
  private int score = 0;
  private int lastSize;

  public GameControllers() {
    newGame();
    initView();
    initThread();
    initSound();
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent keyEvent) {
    int keyCode = keyEvent.getKeyCode();
    switch (keyCode) {
      case KeyEvent.VK_UP -> isUp = true;
      case KeyEvent.VK_RIGHT -> isRight = true;
      case KeyEvent.VK_DOWN -> isDown = true;
      case KeyEvent.VK_LEFT -> isLeft = true;
      case KeyEvent.VK_SPACE -> isSpace = true;
    }
  }

  @Override
  public void keyReleased(KeyEvent keyEvent) {
    int keyCode = keyEvent.getKeyCode();
    switch (keyCode) {
      case KeyEvent.VK_UP -> isUp = false;
      case KeyEvent.VK_RIGHT -> isRight = false;
      case KeyEvent.VK_DOWN -> isDown = false;
      case KeyEvent.VK_LEFT -> isLeft = false;
      case KeyEvent.VK_SPACE -> isSpace = false;
    }
  }

  private void newGame() {
    time = 180;
    items = new Map().readMap(Map.MAP_2);
    initPlayer();
    initMonsters();
    lastSize = monsterList.size();
  }

  private void initPlayer() {
    player = new Ike();
    player.setX(SIZE_ITEM * 3);
    player.setY(SIZE_ITEM * 3 - SIZE_ITEM / 3);
    player.setOrientation(Character.DOWN);
  }

  private void initMonsters() {
    monsterList = new ArrayList<>();
    monsterList.add(createMonster(SIZE_ITEM * COLUMN, SIZE_ITEM, Movable.LEFT, 1));
//    monsterList.add(createMonster(SIZE_ITEM * 6 / 5, SIZE_ITEM, Movable.DOWN, 1));
//    monsterList.add(createMonster(SIZE_ITEM * 6 / 5, SIZE_ITEM * ROW - 1, Movable.RIGHT, 1));
//    monsterList.add(createMonster(SIZE_ITEM * COLUMN, SIZE_ITEM * ROW - 1, Movable.UP, 1));
//    monsterList.add(createMonster(SIZE_ITEM * (COLUMN + 1) / 2, SIZE_ITEM, Movable.LEFT, 2));
//    monsterList.add(createMonster(SIZE_ITEM * (COLUMN + 1) / 2, SIZE_ITEM * ROW - 1, Movable.RIGHT, 2));
//    monsterList.add(createMonster(SIZE_ITEM * 6 / 5, SIZE_ITEM * (ROW + 1) / 2, Movable.DOWN, 2));
//    monsterList.add(createMonster(SIZE_ITEM * COLUMN, SIZE_ITEM * (ROW + 1) / 2, Movable.UP, 2));
//    monsterList.add(createMonster(SIZE_ITEM * 7 + 4, SIZE_ITEM * 6, Movable.DOWN, 2));
//    monsterList.add(createMonster(SIZE_ITEM * 11 + 4, SIZE_ITEM * 6, Movable.LEFT, 2));
//    monsterList.add(createMonster(SIZE_ITEM * 11 + 4, SIZE_ITEM * 10, Movable.UP, 2));
//    monsterList.add(createMonster(SIZE_ITEM * 7 + 4, SIZE_ITEM * 10, Movable.RIGHT, 2));
  }

  private Monster createMonster(int x, int y, int orientation, int type) {
    Monster monster;
    if (type == 2) {
      monster = new Monster2();
    } else {
      monster = new Monster1();
    }
    monster.setX(x);
    monster.setY(y);
    monster.setOrientation(orientation);
    if (monster.getDelay() > 5) {
      monster.setDelay(monster.getDelay() - 5 * (level - 1));
    }
    return monster;
  }

  private void initView() {
    view = new GameScreen(this);
    view.addKeyListener(this);
    view.setActionListener(this);
  }

  private void initThread() {
    isRunning = true;
    Runnable runnable = () -> {
      while (isRunning && !player.isDeath()) {
        handling();

        if (isWin) {
          soundControllers.get(Sound.GAME).stop();
          new Sound(Sound.WIN).play();
          JOptionPane.showMessageDialog(view, "WIN MÀN " + level + "!");
          level++;
          view.setLevel(level);
          newGame();
          isWin = false;
          soundControllers.get(Sound.GAME).loop();
        }
        try {
          Thread.sleep(2);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      view.repaint();
      soundControllers.get(Sound.GAME).stop();
      if (player.isDeath()) {
        JOptionPane.showMessageDialog(view, "GAME OVER!");
        routes.back();
      }
      soundControllers.get(Sound.HOME).loop();
    };
    Thread thread = new Thread(runnable);
    thread.start();
  }

  private void initSound() {
    soundControllers.get(Sound.HOME).stop();
    soundControllers.get(Sound.GAME).loop();
  }

  public void draw(Graphics2D graphics2D) {
    // draw impediment items
    for (ImpedimentItem[] item : items) {
      for (int j = 0; j < items[0].length; j++) {
        item[j].draw(graphics2D);
      }
    }
    for (int i = 0; i < monsterList.size(); i++) {
      monsterList.get(i).draw(graphics2D);
    }
    player.draw(graphics2D);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    isRunning = false;
    soundControllers.get(Sound.GAME).stop();
    soundControllers.get(Sound.HOME).loop();
    routes.back();
  }

  private void handling() {
    movePlayer();
    if (isSpace) {
      player.putBombs();
    }
    for (Monster monster : monsterList) {
      monster.move(items, player.getBombList(), player);
    }
    player.animationBomb();
    view.repaint();
    player.explosiveBombs(items, monsterList);
    if (lastSize > monsterList.size()) {
      score += (lastSize - monsterList.size()) * 10;
      view.setScore(score);
      view.repaint();
      lastSize = monsterList.size();
      isWin = monsterList.size() == 0;
    }
    if (time < 0) player.setDeath(true);
    setTime();
    view.repaint();
  }

  private void movePlayer() {
    if (isUp) {
      player.setOrientation(Character.UP);
      player.move(items, monsterList);
      return;
    }
    if (isRight) {
      player.setOrientation(Character.RIGHT);
      player.move(items, monsterList);
      return;
    }
    if (isDown) {
      player.setOrientation(Character.DOWN);
      player.move(items, monsterList);
      return;
    }
    if (isLeft) {
      player.setOrientation(Character.LEFT);
      player.move(items, monsterList);
    }
  }

  private void setTime() {
    long current = System.currentTimeMillis();
    if (current - lastTime < 1000)  return;
    int minutes = time / 60;
    int seconds = time % 60;
    String formatTime = String.format("%02d", minutes) + ":" + String.format("%02d", seconds);
    view.setTime(formatTime);
    lastTime = current;
    time--;
  }

  public GameScreen getView() {
    return view;
  }

  public void setRoutes(IRoutes routes) {
    this.routes = routes;
  }
}
