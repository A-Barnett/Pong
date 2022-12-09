package com.example.pong;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;

public class MySceneFactory extends SceneFactory {

    @Override
    public FXGLMenu newMainMenu() {
        return new CustomGameMenu.MyPauseMenu();
    }

    @Override
    public FXGLMenu newGameMenu() {
        return new CustomGameMenu.MyPauseMenu();
    }
}