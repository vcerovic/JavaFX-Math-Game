package com.veljkocerovic.controllers;

import com.veljkocerovic.models.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class GameController {

    public Label questionLbl;

    @FXML
    public void initialize(){
        Question question = Question.getInstance();
        question.generateQuestionAndAnswer();
        questionLbl.textProperty().bindBidirectional(question.questionProperty());

    }

    public void switchToHomeScene(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Home.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
