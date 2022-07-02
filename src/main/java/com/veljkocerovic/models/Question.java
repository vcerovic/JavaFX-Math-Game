package com.veljkocerovic.models;

public class Question {

    private static Question question_instance = null;

    private String question;
    private double answer;

    private Question() {

    }

    public static Question getInstance() {
        if (question_instance == null)
            question_instance = new Question();

        return question_instance;
    }

    private String generateQuestion() {
        String question = "";
        char[] signs = {'+', '-', '*', '/'};

        char randomSign = signs[getRandomNumber(0, 3)];

        double x = getRandomNumber(1, 15);
        double y = getRandomNumber(1, 15);

        switch (randomSign) {
            case '+' -> answer=x+y;
            case '-' -> answer=x-y;
            case '*' -> answer=x*y;
            case '/' -> answer=x/y;
        }


        return question;
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
