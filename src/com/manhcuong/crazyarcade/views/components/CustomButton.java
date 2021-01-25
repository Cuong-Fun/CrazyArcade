package com.manhcuong.crazyarcade.views.components;

import javax.swing.JButton;
import java.awt.Graphics;
import java.awt.RenderingHints;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomButton extends JButton implements MouseListener {
  private Image backgroundImgON;
  private Image backgroundImgOFF;
  private Image image;

  public CustomButton(Image image, int x, int y, int width, int height) {
    this.image = image;
    setSize(width, height);
    setLocation(x, y);
    setOpaque(false);
    setContentAreaFilled(false);
    setBorder(null);
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    addMouseListener(this);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2D = (Graphics2D) graphics;
    graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    graphics2D.drawImage(image, 0, 0, getWidth(), getHeight(), null);
  }

  public Image getBackgroundImgON() {
    return backgroundImgON;
  }

  public void setBackgroundImgON(Image backgroundImg) {
    this.backgroundImgON = backgroundImg;
  }

  public Image getBackgroundImgOFF() {
    return backgroundImgOFF;
  }

  public void setBackgroundImgOFF(Image backgroundImgOFF) {
    this.backgroundImgOFF = backgroundImgOFF;
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {
    image = backgroundImgON;
    repaint();
  }

  @Override
  public void mouseExited(MouseEvent e) {
    image = backgroundImgOFF;
    repaint();
  }
}
