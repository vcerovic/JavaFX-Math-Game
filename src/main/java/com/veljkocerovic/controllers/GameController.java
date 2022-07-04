package com.veljkocerovic.controllers;

import com.veljkocerovic.models.GameManager;
import com.veljkocerovic.models.Question;
import com.veljkocerovic.utils.AlertUtils;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    @FXML
    private Label questionLbl;
    @FXML
    private GridPane answersGrid;
    @FXML
    private Label scoreLbl;
    @FXML
    private ProgressBar timerBar;

    private GameManager gameManager;
    private Timer countdownTimer;
    private double countdown = 1;


    @FXML
    public void initialize() {
        Question question = Question.getInstance();
        gameManager = GameManager.getInstance();

        //Binding ui elements to properties
        questionLbl.textProperty().bindBidirectional(question.getQuestion());
        scoreLbl.textProperty().bind(gameManager.getScore().asString());

        handleGameLogic(question, gameManager);
        handleCountdownTimer();
    }

    private void handleCountdownTimer() {
        //Countdown logic
        countdownTimer = new Timer();
        countdownTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (countdown <= 0) {
                        try {
                            loseGame();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        timerBar.setProgress(countdown);
                    }
                });
                countdown -= 0.01;
            }
        }, 0, 150);
    }

    private void handleGameLogic(Question question, GameManager gameManager) {
        ObservableList<Node> children = answersGrid.getChildren();

        int[] answers = question.getAnswers();
        int answer = question.getAnswer();

        for (int i = 0; i < children.size(); i++) {
            Button btn = (Button) children.get(i);
            Integer randomAnswer = answers[i];

            btn.setText(String.valueOf(randomAnswer));

            btn.setOnAction(event -> {
                if (Integer.parseInt(btn.getText()) == answer) {
                    gameManager.increaseScore(1);
                    countdown = 1;
                } else {
                    if (gameManager.getScore().intValue() == 0 || gameManager.getScore().intValue() == 1) {
                        try {
                            loseGame();
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

    private void loseGame() throws IOException {
        countdownTimer.cancel();
        gameManager.resetScore();
        AlertUtils.showAlertMessage("GAME OVER", Alert.AlertType.ERROR);
        switchToHomeScene();
    }

    @FXML
    public void switchToHomeScene() throws IOException {
        SceneController sceneController = new SceneController(questionLbl.getScene());
        sceneController.addScreen("Home", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Home.fxml"))));
        sceneController.activate("Home");
    }
}
