package com.veljkocerovic.models;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class GameManager {
    private static GameManager manager;

    private Difficulty difficulty;

    private int answerCountdown ;

    private SimpleIntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    private GameManager() {
        difficulty = Difficulty.EASY;
    }

    public static GameManager getInstance() {
        if (manager == null)
            manager = new GameManager();

        return manager;
    }

    public void increaseScore(int score) {
        this.score.setValue(this.score.getValue() + score);
    }

    public void decreaseScore(int score) {
        this.score.setValue(this.score.getValue() - score);
    }

    public void resetScore() {
        this.score.setValue(0);
    }

    public int getAnswerCountdownByDifficulty() {
        int period = 0;

        switch (difficulty) {
            case EASY -> period = 50;
            case MEDIUM -> period = 100;
            case HARD -> period = 200;
        }

        return period;
    }

}
