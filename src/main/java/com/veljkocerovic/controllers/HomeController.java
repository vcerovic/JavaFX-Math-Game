package com.veljkocerovic.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    public void switchToGameScene(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController(((Node) event.getSource()).getScene());
        sceneController.addScreen("Gameplay", FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("../Gameplay.fxml"))));
        sceneController.activate("Gameplay");
    }

    public void quitGame() {
        Platform.exit();
    }
}
