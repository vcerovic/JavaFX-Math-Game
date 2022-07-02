package com.veljkocerovic.models;

import javafx.beans.property.SimpleStringProperty;

public class Question {

    private static Question question_instance = null;

    private final SimpleStringProperty question = new SimpleStringProperty(this, "question", "");
    private int answer;

    private Question() {
        generateQuestionAndAnswer();
    }

    public static Question getInstance() {
        if (question_instance == null)
            question_instance = new Question();

        return question_instance;
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

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public String getQuestion() {
        return question.get();
    }

    public SimpleStringProperty questionProperty() {
        return question;
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
