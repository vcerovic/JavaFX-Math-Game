package com.veljkocerovic.controllers;

import com.veljkocerovic.database.UserDAO;
import com.veljkocerovic.models.GameManager;
import com.veljkocerovic.models.Question;
import com.veljkocerovic.models.User;
import com.veljkocerovic.models.UserSession;
import com.veljkocerovic.utils.AlertUtils;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Timer;
import java.util.TimerTask;

public class GameController {

    @FXML
    private Label userHighscoreLbl;
    @FXML
    private Label usernameLbl;
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
    private final int scorePerAnswer = 2;

    private User activeUser;
    private boolean isLost = false;


    @FXML
    public void initialize() {
        //User Session
        activeUser = UserSession.getInstance().getActiveUser();
        usernameLbl.setText(activeUser.getUsername());
        userHighscoreLbl.setText(String.valueOf(activeUser.getHighscore()));

        //Question and GameManager
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
                        if(!isLost){
                            loseGame();
                            isLost = true;
                        }

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
                    countdown = 1;
                    gameManager.increaseScore(scorePerAnswer);
                } else {
                    //If answer is wrong
                    decreaseAndCheckScore(2);
                    countdown = 1;
                }

                //Generate new questions and answers
                question.generateQuestionAndAnswer(gameManager.getDifficulty());
                question.generateAnswers();
                initButtonsAndClickEvents(question, gameManager);
            });
        }
    }

    @FXML
    public void switchToHomeScene() {
        SceneController sceneController = SceneController.getInstance();
        sceneController.showScene(userHighscoreLbl.getScene(), "Home");
    }

    @FXML
    public void splitAnswersAndDecreaseScore() {

        ArrayList<Integer> randomSpots = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            if (i != question.getRandomSpot()) randomSpots.add(i);
        }

        Collections.shuffle(randomSpots);

        //Remove two random wrong answers
        for (int i = 0; i < 2; i++) {
            Button btn = (Button) answersGrid.getChildren().get(randomSpots.get(i));
            btn.setVisible(false);
        }

        decreaseAndCheckScore(1);
    }

    @FXML
    public void increaseTimeAndDecreaseScore() {
        countdown = 1;
        decreaseAndCheckScore(1);
    }

    @FXML
    public void loseGame() {
        activeUser.setTimesPlayed(activeUser.getTimesPlayed() + 1);

        //Update user's highscore, if needed
        if (gameManager.getScore().get() > activeUser.getHighscore()) {
            activeUser.setHighscore(gameManager.getScore().get());
        }

        //Update user
        UserDAO.updateUser(activeUser.getId(), activeUser);

        countdownTimer.cancel();
        gameManager.resetScore();

        switchToHomeScene();
        AlertUtils.showAlertMessage("GAME OVER", Alert.AlertType.INFORMATION);
    }

    private void decreaseAndCheckScore(int score) {
        if (gameManager.getScore().intValue() <= score) {
            loseGame();
        } else {
            gameManager.decreaseScore(score);
        }
    }
}
