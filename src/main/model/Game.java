package main.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Game {

    private static int questionNum = 1;
    private static Question question;
    private static boolean game = true;

    private static Game instance = new Game();

    private Game() {
    }

    //Singleton Pattern
    public static Game getInstance() {
        return instance;
    }

    public static boolean checkAnswer(ActionEvent e, Button button) {
        if(button.getText().equals(question.getCorrectAnswer().getAnswer())) {
            System.out.println("correct");
            return true;
        }

        game = false;
        return false;
    }

    public Question sendQuestion() {
        int difficulty = 1;

        if(questionNum > 6 && questionNum <= 10) difficulty = 2;
        if(questionNum > 10 && questionNum <= 13) difficulty = 3;
        if(questionNum > 13) difficulty = 4;

        System.out.println("Fragenanzahl: " + questionNum);
        System.out.println("Schwierigkeit: " + difficulty);

        question = Database.getInstance().getRandomQuestion(difficulty);

        return question;
    }

    public static Question getQuestion() {
        return question;
    }

    public static void setQuestion(Question question) {
        Game.question = question;
    }

    public static int getQuestionNum() {
        return questionNum;
    }

    public static void increaseFragenAnzahl() {
        questionNum++;
    }

    public static void setQuestionNum(int questionNum) {
        questionNum = questionNum;
    }

    public static boolean isGame() {
        return game;
    }

    public static void setGame(boolean game) {
        Game.game = game;
    }
}
