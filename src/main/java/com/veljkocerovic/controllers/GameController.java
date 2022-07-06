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
import java.util.*;

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
    private Question question;
    private Timer countdownTimer;
    private double countdown = 1;



    @FXML
    public void initialize() {
        question = Question.getInstance();
        gameManager = GameManager.getInstance();

        //Generate new questions and answers
        question.generateQuestionAndAnswer(gameManager.getDifficulty());
        question.generateAnswers();

        //Binding ui elements to properties
        questionLbl.textProperty().bindBidirectional(question.getQuestion());
        scoreLbl.textProperty().bind(gameManager.getScore().asString());

        initButtonsAndClickEvents(question, gameManager);
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
                        loseGame();
                    } else {
                        timerBar.setProgress(countdown);
                    }
                });
                countdown -= 0.01;
            }
        }, 0, gameManager.getAnswerCountdownByDifficulty());
    }

    private void initButtonsAndClickEvents(Question question, GameManager gameManager) {
        ObservableList<Node> children = answersGrid.getChildren();

        //Get answers and answer
        int[] answers = question.getAnswers();
        int answer = question.getAnswer();

        //Foreach answer create button and add action event listener
        for (int i = 0; i < children.size(); i++) {
            Button btn = (Button) children.get(i);
            //Set all buttons to visible if player used 50/50 option
            btn.setVisible(true);
            Integer randomAnswer = answers[i];

            //Set button text to answer
            btn.setText(String.valueOf(randomAnswer));

            btn.setOnAction(event -> {
                //If answer is right
                if (Integer.parseInt(btn.getText()) == answer) {
                    gameManager.increaseScore(1);
                    countdown = 1;
                } else {
                    //If answer is wrong
                    decreaseAndCheckScore();
                }

                //Generate new questions and answers
                question.generateQuestionAndAnswer(gameManager.getDifficulty());
                question.generateAnswers();
                initButtonsAndClickEvents(question, gameManager);
            });
        }
    }

    @FXML
    public void switchToHomeScene() throws IOException {
        SceneController sceneController = new SceneController(questionLbl.getScene());
        sceneController.addScreen("Home", FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../Home.fxml"))));
        sceneController.activate("Home");
    }

    @FXML
    public void splitAnswersAndDecreaseScore() {

        ArrayList<Integer> randomSpots = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if(i != question.getRandomSpot()) randomSpots.add(i);
        }

        Collections.shuffle(randomSpots);

        //Remove two random wrong answers
        for (int i = 0; i < 2; i++) {
            Button btn = (Button) answersGrid.getChildren().get(randomSpots.get(i));
            btn.setVisible(false);
        }

        decreaseAndCheckScore();
    }

    @FXML
    public void increaseTimeAndDecreaseScore() {
        countdown = 1;
        decreaseAndCheckScore();
    }

    @FXML
    public void loseGame(){
        countdownTimer.cancel();
        gameManager.resetScore();
        AlertUtils.showAlertMessage("GAME OVER", Alert.AlertType.ERROR);
        try {
            switchToHomeScene();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void decreaseAndCheckScore(){
        if (gameManager.getScore().intValue() <= 1) {
            loseGame();
        } else {
            gameManager.decreaseScore(1);
        }
    }
}
