package com.manhcuong.crazyarcade.controllers;

import com.manhcuong.crazyarcade.Constants;
import com.manhcuong.crazyarcade.models.IRoutes;
import com.manhcuong.crazyarcade.models.entitys.Sound;
import com.manhcuong.crazyarcade.views.screens.HomeScreen;

import java.awt.event.ActionListener;

public class HomeControllers implements Constants {
  private final HomeScreen view;
  private IRoutes routes;
  private ActionListener actionListener;

  public HomeControllers() {
    view = new HomeScreen();
    initActionListeners();
    view.setActionListeners(actionListener);
    SoundControllers.getInstance().get(Sound.HOME).loop();
  }

  private void initActionListeners() {
    actionListener = actionEvent -> {
      String ID = actionEvent.getActionCommand();
      switch (ID) {
        case "Play" -> routes.start();
        case "HighScore" -> routes.highScore();
        case "Guide" -> routes.guide();
        case "Exit" -> routes.exit();
      }
    };
  }

  public void setRoutes(IRoutes routes) {
    this.routes = routes;
  }

  public HomeScreen getView() {
    return view;
  }
}
