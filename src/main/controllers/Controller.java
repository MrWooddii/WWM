package main.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import main.model.Answer;
import main.model.Game;
import main.model.Question;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

public class Controller {

    @FXML
    private Label questionText;
    @FXML
    private Button AButton;
    @FXML
    private Button BButton;
    @FXML
    private Button CButton;
    @FXML
    private Button DButton;
    @FXML
    private Button continueButton;

    private Answer correctAnswer;

    //Klick auf den continueButton setzt eine neue Frage
    @FXML
    public void setQuizQuestion() throws IOException {

        Button[] buttons = {AButton, BButton, CButton, DButton};

        setButtonColor("#00FFFF", false, buttons);

        for(Button button : buttons) {
            button.setDisable(false);
        }

        Question question = Game.getInstance().sendQuestion();

        questionText.setText(question.getQuestion());

        Answer[] answers = new Answer[4];
        answers[0] = question.getCorrectAnswer();
        answers[1] = question.getFalseAnswer1();
        answers[2] = question.getFalseAnswer2();
        answers[3] = question.getFalseAnswer3();

        Collections.shuffle(Arrays.asList(answers));

        for(int i = 0; i < buttons.length;i++) {
            buttons[i].setText(answers[i].getAnswer());
            if(answers[i].getCorrect()) {
                correctAnswer = answers[i];
            }
        }
        System.out.println("ich mache was");
    }

    //checkt, ob der gedrückte Knopf die korrekte Antwort enthält
    @FXML
    public void clickAnswer(ActionEvent e) {
        boolean correctAnswer = false;

        //erhalte den geklickten Button
        Button chosenAnswer = (Button) e.getSource();

        //Check, ob die gewählte Antwort korrekt ist
        correctAnswer = Game.checkAnswer(e, chosenAnswer);

        //wenn korrekt, erhöhe die korrekt beantworteten Fragen um 1
        if(correctAnswer) {
            Game.increaseFragenAnzahl();
            setButtonColor("green", true, chosenAnswer);
            continueButton.setVisible(true);
            //setQuizQuestion();
        } else {
            //TODO: Game over Screen
            setButtonColor("red", true, AButton, BButton, CButton, DButton);
            continueButton.setVisible(false);
            System.out.println("Game over");
        }
        AButton.setDisable(true);
        BButton.setDisable(true);
        CButton.setDisable(true);
        DButton.setDisable(true);
    }

    private void setButtonColor(String color, boolean answered, Button... buttons) {
        for(Button button : buttons) {
            if(answered) {
                if(button.getText().equals(correctAnswer.getAnswer())) {
                    button.setStyle("-fx-background-color: green");
                    System.out.println("green");
                } else {
                    button.setStyle("-fx-background-color: " + color);
                }
            } else {
                button.setStyle("-fx-background-color: " + color);
            }

        }

    }

}
/*
bisher nicht benötigt
class GetQuestionTask extends Task {

    @Override
    public Question call() throws Exception {
        return Database.getInstance().getRandomQuestion(1);
    }
}

 */
