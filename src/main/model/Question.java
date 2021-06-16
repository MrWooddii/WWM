package main.model;

public class Question {

    private String question;
    private Answer correctAnswer;
    private Answer falseAnswer1;
    private Answer falseAnswer2;
    private Answer falseAnswer3;
    private int difficulty;
    private boolean used;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Answer getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Answer correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public Answer getFalseAnswer1() {
        return falseAnswer1;
    }

    public void setFalseAnswer1(Answer falseAnswer1) {
        this.falseAnswer1 = falseAnswer1;
    }

    public Answer getFalseAnswer2() {
        return falseAnswer2;
    }

    public void setFalseAnswer2(Answer falseAnswer2) {
        this.falseAnswer2 = falseAnswer2;
    }

    public Answer getFalseAnswer3() {
        return falseAnswer3;
    }

    public void setFalseAnswer3(Answer falseAnswer3) {
        this.falseAnswer3 = falseAnswer3;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String toString() {
        return this.question + "\nA: " + this.correctAnswer + "\nB: " + this.falseAnswer1 + "\nC: " + this.falseAnswer2 + "\nD: " + this.falseAnswer3;
    }
}
