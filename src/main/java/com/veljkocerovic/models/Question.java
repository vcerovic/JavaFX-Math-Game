package com.veljkocerovic.models;

import com.veljkocerovic.utils.MathUtils;
import javafx.beans.property.SimpleStringProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

import static com.veljkocerovic.utils.MathUtils.getRandomNumber;

@Getter
@Setter
public class Question {

    private static Question question_instance = null;
    private SimpleStringProperty question = new SimpleStringProperty(this, "question", "");

    private int answer;
    private ArrayList<Integer> answers;

    private Question() {
        generateQuestionAndAnswer();
        generateAnswers();
    }

    public static Question getInstance() {
        if (question_instance == null)
            question_instance = new Question();

        return question_instance;
    }


    public void generateAnswers(){
        answers = new ArrayList<>();
        int randomSpot = MathUtils.getRandomNumber(0, 3);

        for (int i = 0; i < 4; i++) {
            if(randomSpot == i){
                answers.add(answer);
            } else {
                //Get random answer that is lower than answer (-5) and higher the answer (+5)
                int randomNum = MathUtils.getRandomAnswer(answer);

                //Make sure random number doesn't already exist
                if(!answers.isEmpty()){
                    while (answers.contains(randomNum))
                    {
                        randomNum = MathUtils.getRandomAnswer(answer);
                    }
                }

                //Make sure random number is not equal to generated answer
                while (randomNum == answer)
                {
                    randomNum = MathUtils.getRandomAnswer(answer);
                }

                //Finally add random num to answers array
                answers.add(randomNum);
            }

        }
    }

    public void generateQuestionAndAnswer() {
        char[] signs = {'+', '-', '*', '+', '*', '-', '-', '*', '+'};

        char randomSign = signs[getRandomNumber(0, signs.length-1)];

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
