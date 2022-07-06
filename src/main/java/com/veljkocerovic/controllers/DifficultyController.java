package com.veljkocerovic.controllers;

import com.veljkocerovic.models.Difficulty;
import com.veljkocerovic.models.GameManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
import java.util.Objects;

public class DifficultyController {
    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private void playGame(ActionEvent event) throws IOException {
        RadioButton radioButton = (RadioButton) difficultyGroup.getSelectedToggle();

        switch (radioButton.getText()) {
            case "Easy" -> GameManager.getInstance().setDifficulty(Difficulty.EASY);
            case "Medium" -> GameManager.getInstance().setDifficulty(Difficulty.MEDIUM);
            case "Hard" -> GameManager.getInstance().setDifficulty(Difficulty.HARD);
        }

        SceneController sceneController = new SceneController(radioButton.getScene());
        sceneController.addScreen("Gameplay", FXMLLoader
                .load(Objects.requireNonNull(getClass().getResource("../Gameplay.fxml"))));

        sceneController.activate("Gameplay");
    }
}
