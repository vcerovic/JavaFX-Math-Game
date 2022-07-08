package com.veljkocerovic.controllers;

import com.veljkocerovic.models.Difficulty;
import com.veljkocerovic.models.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.Objects;

public class DifficultyController {
    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private void playGame(ActionEvent event) {
        RadioButton radioButton = (RadioButton) difficultyGroup.getSelectedToggle();

        switch (radioButton.getText()) {
            case "Easy" -> GameManager.getInstance().setDifficulty(Difficulty.EASY);
            case "Medium" -> GameManager.getInstance().setDifficulty(Difficulty.MEDIUM);
            case "Hard" -> GameManager.getInstance().setDifficulty(Difficulty.HARD);
        }

        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(((Node) event.getSource()).getScene(),"Gameplay");
    }
}
