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

    private Question() {
        generateQuestionAndAnswer();
        generateAnswers();
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
        int randomSpot = MathUtils.getRandomNumber(0, 3);
        answers[randomSpot] = answer;
    }

    public void generateQuestionAndAnswer() {
        char[] signs = {'+', '-', '*', '+', '*', '-', '-', '*', '+'};

        char randomSign = signs[getRandomNumber(0, signs.length - 1)];

        int x = getRandomNumber(1, 15);
        int y = getRandomNumber(1, 15);

        switch (randomSign) {
            case '+' -> answer = x + y;
            case '-' -> answer = x - y;
            case '*' -> answer = x * y;
        }

        question.set(x + " " + randomSign + " " + y + " = ?");
    }
}
