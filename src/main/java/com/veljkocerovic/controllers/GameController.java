package com.veljkocerovic.controllers;

import com.veljkocerovic.models.GameManager;
import com.veljkocerovic.models.Question;
import com.veljkocerovic.utils.AlertUtils;
import com.veljkocerovic.utils.MathUtils;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public Label scoreLbl;

    @FXML
    public void initialize() {
        Question question = Question.getInstance();
        GameManager gameManager = GameManager.getInstance();

        questionLbl.textProperty().bindBidirectional(question.getQuestion());
        scoreLbl.textProperty().bind(gameManager.getScore().asString());

        handleGameLogic(question, gameManager);


    }

    private void handleGameLogic(Question question, GameManager gameManager) {
        ObservableList<Node> children = answersGrid.getChildren();
        ArrayList<Integer> answers = question.getAnswers();

        int answer = question.getAnswer();

        for (int i = 0; i < children.size(); i++) {
            Button btn = (Button) children.get(i);
            Integer randomAnswer = answers.get(i);

            btn.setText(String.valueOf(randomAnswer));

            btn.setOnAction(event -> {
                if (Integer.parseInt(btn.getText()) == answer) {
                    gameManager.increaseScore(1);
                } else {
                    if(gameManager.getScore().intValue() == 1){
                        try {
                            gameManager.resetScore();
                            switchToHomeScene(event);
                            AlertUtils.showAlertMessage("GAME OVER", Alert.AlertType.ERROR);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        gameManager.decreaseScore(1);
                    }

                }

                question.generateQuestionAndAnswer();
                question.generateAnswers();
                handleGameLogic(question, gameManager);
            });
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
