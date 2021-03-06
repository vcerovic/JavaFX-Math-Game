package com.veljkocerovic;

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
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("views/Home.fxml")));
        stage.setTitle("JavaFX Math Game");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}