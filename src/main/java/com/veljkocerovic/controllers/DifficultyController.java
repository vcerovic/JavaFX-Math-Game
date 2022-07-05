package com.veljkocerovic.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class DifficultyController {
    @FXML
    private ToggleGroup difficultyGroup;

    @FXML
    private void playGame(ActionEvent event) {
        Toggle selectedToggle = difficultyGroup.getSelectedToggle();
        System.out.println(selectedToggle);
    }
}
