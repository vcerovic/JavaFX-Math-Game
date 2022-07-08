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
    public void switchToOptionsScene(ActionEvent event) {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Options");
    }

    @FXML
    public void switchToLeaderboardsScene(ActionEvent event) {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Leaderboards");
    }

    public void quitGame() {
        Platform.exit();
    }
}
