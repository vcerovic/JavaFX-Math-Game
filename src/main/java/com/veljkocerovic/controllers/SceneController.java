package com.veljkocerovic.controllers;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import java.util.HashMap;

public class SceneController {

    private final HashMap<String, Pane> screenMap = new HashMap<>();
    private final Scene main;

    public SceneController(Scene main) {
        this.main = main;
    }

    protected void addScreen(String name, Pane pane){
        screenMap.put(name, pane);
    }

    protected void removeScreen(String name){
        screenMap.remove(name);
    }

    protected void activate(String name){
        main.setRoot( screenMap.get(name) );
    }
}