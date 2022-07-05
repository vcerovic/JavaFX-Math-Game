package com.veljkocerovic.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    public void switchToDifficultySelectorScene(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController(((Node) event.getSource()).getScene());
        sceneController.addScreen("DifficultySelector", FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("../DifficultySelector.fxml"))));
        sceneController.activate("DifficultySelector");
    }

    public void quitGame() {
        Platform.exit();
    }
}
