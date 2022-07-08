package com.veljkocerovic;

import com.veljkocerovic.controllers.SceneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Home.fxml")));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        SceneController sceneController = SceneController.getInstance();
        sceneController.setStage(stage);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}