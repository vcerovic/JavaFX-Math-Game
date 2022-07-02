package com.veljkocerovic.controllers;

import com.veljkocerovic.models.Question;
import com.veljkocerovic.utils.MathUtils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController {

    @FXML
    public Label questionLbl;
    @FXML
    public GridPane answersGrid;

    @FXML
    public void initialize() {
        Question question = Question.getInstance();
        question.generateQuestionAndAnswer();
        question.generateAnswers();

        questionLbl.textProperty().bindBidirectional(question.getQuestion());

        ObservableList<Node> children = answersGrid.getChildren();
        ArrayList<Integer> answers = question.getAnswers();

        if(!answers.isEmpty()){
            int answer = question.getAnswer();

            for (int i = 0; i < children.size(); i++) {
                Button btn = (Button) children.get(i);
                Integer randomAnswer = answers.get(i);

                btn.setText(String.valueOf(randomAnswer));

                btn.setOnAction(event -> {
                    if(Integer.parseInt(btn.getText()) == answer){
                        System.out.println("GOOD");
                    } else {
                        System.out.println("BAD");
                    }
                });
            }
        }

    }

    @FXML
    public void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Home.fxml")));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
