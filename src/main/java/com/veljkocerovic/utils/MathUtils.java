package com.veljkocerovic.utils;

public class MathUtils {

    public static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public static int getRandomAnswer(int answer){
        return answer > 5 ? MathUtils.getRandomNumber(answer - 5, answer + 5) : MathUtils.getRandomNumber(1, answer + 5);
    }

}
