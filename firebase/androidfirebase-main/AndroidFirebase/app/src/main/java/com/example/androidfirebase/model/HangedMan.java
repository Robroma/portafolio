package com.example.androidfirebase.model;

import java.util.ArrayList;

public class HangedMan {
    private ArrayList<String> answers;

    private int tries;
    private int wordNumber;

    public HangedMan() {
        answers = new ArrayList<>();
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public int getTries() {
        return tries;
    }

    public void setTries(int tries) {
        this.tries = tries;
    }

    public int getWordNumber() {
        return wordNumber;
    }

    public void setWordNumber(int wordNumber) {
        this.wordNumber = wordNumber;
    }

    @Override
    public String toString() {
        return "HangedMan{" +
                "answers=" + answers +
                ", tries=" + tries +
                ", wordNumber=" + wordNumber +
                '}';
    }
}
