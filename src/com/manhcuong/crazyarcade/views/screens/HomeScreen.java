package com.manhcuong.crazyarcade.views.screens;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.views.components.CustomButton;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.event.ActionListener;

public class HomeScreen extends JPanel {
  private CustomButton startBtn;
  private CustomButton highScoreBtn;
  private CustomButton guideBtn;
  private CustomButton exitBtn;

  public HomeScreen() {
    setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
    setLayout(null);
    initButtons();
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(
        new ImageIcon(getClass().getResource("/assets/images/backgrounds/bg_home.png")).getImage(),
        0, 0, Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME, null
    );
  }

  private void initButtons() {
    startBtn = setButton("start", 100, 120, "Play");
    guideBtn = setButton("guide", 70, 240, "Guide");
    highScoreBtn = setButton("highscore", 70, 360, "HighScore");
    exitBtn = setButton("exit", 100, 480, "Exit");
  }

  private CustomButton setButton(String imgName, int x, int y, String btnName) {
    String url = "/assets/images/backgrounds/" + imgName + "_btn_";
    Image imgON = new ImageIcon(getClass().getResource(url + "on.png")).getImage();
    Image imgOFF = new ImageIcon(getClass().getResource(url + "off.png")).getImage();
    CustomButton button = new CustomButton(imgOFF, x, y, imgON.getWidth(null), imgON.getHeight(null));
    button.setBackgroundImgON(imgON);
    button.setBackgroundImgOFF(imgOFF);
    button.setActionCommand(btnName);
    add(button);
    return button;
  }

  public void setActionListeners(ActionListener actionListener) {
    startBtn.addActionListener(actionListener);
    highScoreBtn.addActionListener(actionListener);
    guideBtn.addActionListener(actionListener);
    exitBtn.addActionListener(actionListener);
  }
}
