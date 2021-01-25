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

public class GuideScreen extends JPanel {
  private final CustomButton backBtn;

  public GuideScreen() {
    setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
    setLayout(null);
    backBtn = setButton();
    setVisible(true);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2D.drawImage(
        new ImageIcon(getClass().getResource("/assets/images/backgrounds/bg_guide.png")).getImage(),
        0, 0, Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME, null
    );
  }

  private CustomButton setButton() {
    String url = "/assets/images/backgrounds/back_btn_";
    Image imgON = new ImageIcon(getClass().getResource(url + "on.png")).getImage();
    Image imgOFF = new ImageIcon(getClass().getResource(url + "off.png")).getImage();
    CustomButton button = new CustomButton(imgOFF, Constants.WIDTH_FRAME - 81, Constants.HEIGHT_FRAME - 88, imgON.getWidth(null), imgON.getHeight(null));
    button.setBackgroundImgON(imgON);
    button.setBackgroundImgOFF(imgOFF);
    button.setActionCommand("");
    add(button);
    return button;
  }

  public void setActionListeners(ActionListener actionListener) {
    backBtn.addActionListener(actionListener);
  }
}
