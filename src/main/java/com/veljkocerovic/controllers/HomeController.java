package com.veljkocerovic.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.util.Objects;

public class HomeController {

    @FXML
    public void switchToOptionsScene(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController(((Node) event.getSource()).getScene());
        sceneController.addScreen("Options", FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("../Options.fxml"))));
        sceneController.activate("Options");
    }

    @FXML
    public void switchToLeaderboardsScene(ActionEvent event) throws IOException {
        SceneController sceneController = new SceneController(((Node) event.getSource()).getScene());
        sceneController.addScreen("Leaderboards", FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("../Leaderboards.fxml"))));
        sceneController.activate("Leaderboards");
    }

    public void quitGame() {
        Platform.exit();
    }
}
