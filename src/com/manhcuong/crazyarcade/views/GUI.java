package com.manhcuong.crazyarcade.views;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.controllers.GameControllers;
import com.manhcuong.crazyarcade.controllers.HomeControllers;
import com.manhcuong.crazyarcade.controllers.SoundControllers;
import com.manhcuong.crazyarcade.models.IRoutes;
import com.manhcuong.crazyarcade.views.screens.GameScreen;
import com.manhcuong.crazyarcade.views.screens.GuideScreen;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.awt.Insets;

public class GUI extends JFrame {
  private GuideScreen guideScreen;
  private HomeControllers homeControllers;
  private GameControllers gameControllers;
  private IRoutes routes;

  public GUI() {
    initComponent();
  }

  private void initComponent() {
    setTitle("Crazy Arcade");
    setIconImage(new ImageIcon(GUI.class.getResource("/assets/images/icon.png")).getImage());
    setSize(Constants.WIDTH_FRAME, Constants.HEIGHT_FRAME);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setResizable(false);
    initRoutes();

    homeControllers = new HomeControllers();
    homeControllers.setRoutes(routes);
    add(homeControllers.getView());
  }

  @Override
  public void setVisible(boolean visible) {
    super.setVisible(visible);
    Insets insets = getInsets();
    setSize(getWidth() + insets.left + insets.right, getHeight() + insets.top + insets.bottom);
  }

  private void initRoutes() {
    routes = new IRoutes() {
      @Override
      public void start() {
        remove(homeControllers.getView());
        gameControllers = new GameControllers();
        gameControllers.setRoutes(routes);
        GameScreen gameScreen = gameControllers.getView();
        add(gameScreen);
        gameScreen.requestFocus();
        repaint();
      }

      @Override
      public void highScore() {

      }

      @Override
      public void guide() {
        if (guideScreen == null) {
          guideScreen = new GuideScreen();
          guideScreen.setActionListeners(actionEvent -> {
            remove(guideScreen);
            add(homeControllers.getView());
            repaint();
          });
        }
        remove(homeControllers.getView());
        add(guideScreen);
        System.out.println("k");
        repaint();
      }

      @Override
      public void exit() {
        SoundControllers.getInstance().close();
        System.exit(0);
      }

      @Override
      public void back() {
        remove(gameControllers.getView());
        add(homeControllers.getView());
        repaint();
      }
    };
  }
}
