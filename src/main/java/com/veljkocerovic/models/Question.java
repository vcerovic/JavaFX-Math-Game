package com.veljkocerovic.models;

import com.veljkocerovic.utils.MathUtils;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;

import static com.veljkocerovic.utils.MathUtils.getRandomNumber;

@Getter
@Setter
public class Question {

    private static Question question_instance = null;
    private SimpleStringProperty question = new SimpleStringProperty(this, "question", "");
    private int answer;
    private int[] answers = new int[4];
    private int randomSpot;

    private Question() {

    }

    public static Question getInstance() {
        if (question_instance == null)
            question_instance = new Question();

        return question_instance;
    }


    public void generateAnswers() {
        ArrayList<Integer> randomAnswers = new ArrayList<>();

        //Generate answers
        for (int i = answer - 5; i < answer + 5; i++) {
            if(i != answer) randomAnswers.add(i);
        }

        //Shuffle answers array
        Collections.shuffle(randomAnswers);

        for (int i = 0; i < 4; i++) {
            answers[i] = randomAnswers.get(i);
        }

        //Put real answer among dummy answers
        randomSpot = MathUtils.getRandomNumber(0, 3);
        answers[randomSpot] = answer;
    }

    public void generateQuestionAndAnswer(Difficulty difficulty) {
        char[] signs = {'+', '-', '*', '+', '*', '-', '-', '*', '+'};

        char randomSign = signs[getRandomNumber(0, signs.length - 1)];


        int x = 0, y = 0;

        switch (difficulty){
            case EASY -> {x = getRandomNumber(1, 9); y = getRandomNumber(1, 9);}
            case MEDIUM -> {x = getRandomNumber(9, 20); y = getRandomNumber(9, 20);}
            case HARD -> {x = getRandomNumber(10, 99); y = getRandomNumber(10, 99);}
        }


        switch (randomSign) {
            case '+' -> answer = x + y;
            case '-' -> answer = x - y;
            case '*' -> answer = x * y;
        }

        question.set(x + " " + randomSign + " " + y + " = ?");
    }
}
