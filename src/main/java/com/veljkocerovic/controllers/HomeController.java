package com.veljkocerovic.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

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

    public void switchToProfileScene(ActionEvent event) {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Profile");
    }
}
