package com.veljkocerovic.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.Objects;

@Getter
@Setter
public class SceneController {

    private static SceneController sceneController;
    private SceneController(){

    }

    public static SceneController getInstance() {
        if (sceneController == null)
            sceneController = new SceneController();

        return sceneController;
    }

    public void showScene(Scene scene, String viewName){
        try {
            Pane root = FXMLLoader.load(Objects
                    .requireNonNull(getClass().getResource("../views/%s.fxml".formatted(viewName))));
            scene.setRoot(root);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
