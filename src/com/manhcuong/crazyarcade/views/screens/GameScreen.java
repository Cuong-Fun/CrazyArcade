package com.manhcuong.crazyarcade.views.screens;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.controllers.GameControllers;
import com.manhcuong.crazyarcade.views.components.CustomButton;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

public class GameScreen extends JPanel implements Constants {
  private JLabel level, score;
  private JLabel time;
  private CustomButton backBtn;
  private final GameControllers controllers;

  public GameScreen(GameControllers controllers) {
    this.controllers = controllers;
    initComponent();
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(
        new ImageIcon(GameScreen.class.getResource("/assets/images/backgrounds/bg_up.png")).getImage(),
        0, 0, WIDTH_FRAME, SIZE_ITEM, null
    );
    graphics2D.drawImage(
        new ImageIcon(GameScreen.class.getResource("/assets/images/backgrounds/bg_down.png")).getImage(),
        0, SIZE_ITEM + HEIGHT_MAP, WIDTH_FRAME, SIZE_ITEM, null
    );
    graphics2D.drawImage(
        new ImageIcon(GameScreen.class.getResource("/assets/images/backgrounds/bg_left.png")).getImage(),
        0, -3, SIZE_ITEM, HEIGHT_FRAME + 8, null
    );
    graphics2D.drawImage(
        new ImageIcon(GameScreen.class.getResource("/assets/images/backgrounds/bg_right.png")).getImage(),
        SIZE_ITEM + WIDTH_MAP, -3, SIZE_ITEM * 6, HEIGHT_FRAME + 8, null
    );

    controllers.draw(graphics2D);
  }

  private void initComponent() {
    setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
    setFocusable(true);
    setRequestFocusEnabled(true);
    setLayout(null);
    backBtn = new CustomButton(null, Constants.WIDTH_FRAME - 210, Constants.HEIGHT_FRAME - 40, 200, 40);
    backBtn.setText("THOÁT RA");
    backBtn.setForeground(Color.WHITE);
    backBtn.setFont(new Font("Arial", Font.BOLD, 24));
    backBtn.setActionCommand("Back");
    time = new JLabel();
    time.setSize(50, 50);
    time.setLocation((Constants.SIZE_ITEM * 7) / 3, -9);
    time.setForeground(Color.WHITE);
    time.setFont(new Font("Arial", Font.PLAIN, 16));
    level = new JLabel("MÀN 1");
    level.setSize(100, 100);
    level.setForeground(Color.WHITE);
    level.setLocation(Constants.WIDTH_FRAME - 150, 50);
    level.setFont(new Font("Arial", Font.BOLD, 24));
    score = new JLabel("Điểm: 0");
    score.setSize(150, 100);
    score.setForeground(Color.WHITE);
    score.setLocation(Constants.WIDTH_FRAME - 180, 150);
    score.setFont(new Font("Arial", Font.BOLD, 24));
    add(backBtn);
    add(time);
    add(level);
    add(score);
  }

  public void setActionListener(ActionListener actionListener) {
    backBtn.addActionListener(actionListener);
  }

  public void setTime(String time) {
    this.time.setText(time);
  }

  public void setLevel(int level) {
    this.level.setText("MÀN " + level);
  }

  public void setScore(int score) {
    this.score.setText("Điểm: " + score);
  }
}
