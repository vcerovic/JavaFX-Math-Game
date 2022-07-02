package com.veljkocerovic.models;

import javafx.beans.property.SimpleIntegerProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameManager {
    private static GameManager manager;

    private SimpleIntegerProperty score = new SimpleIntegerProperty(this, "score", 0);

    private GameManager() {
    }

    public static GameManager getInstance() {
        if (manager == null)
            manager = new GameManager();

        return manager;
    }

    public void increaseScore(int score){
        this.score.setValue(this.score.getValue() + score);
    }

    public void decreaseScore(int score){
        this.score.setValue(this.score.getValue() - score);
    }

    public void resetScore(){
        this.score.setValue(0);
    }
}
