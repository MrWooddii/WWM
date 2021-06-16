package main.model;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class Game {

    private static int fragenAnzahl = 1;
    private static Question question;

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
        return false;
    }

    public Question sendQuestion() {
        int difficulty = 1;

        if(fragenAnzahl > 6 && fragenAnzahl <= 10) difficulty = 2;
        if(fragenAnzahl > 10 && fragenAnzahl <= 13) difficulty = 3;
        if(fragenAnzahl > 13) difficulty = 4;

        System.out.println("Fragenanzahl: " + fragenAnzahl);
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

    public static int getFragenAnzahl() {
        return fragenAnzahl;
    }

    public static void increaseFragenAnzahl() {
        fragenAnzahl++;
    }

    public static void setFragenAnzahl(int fragenAnzahl) {
        fragenAnzahl = fragenAnzahl;
    }
}
