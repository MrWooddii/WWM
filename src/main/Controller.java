package main;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.model.Answer;
import main.model.Database;
import main.model.Question;

import java.io.IOException;

public class Controller {

    private int anzahl = 1;
    private Question question;
    @FXML
    private Label questionLabel;
    @FXML
    private Button AButton;
    @FXML
    private Button BButton;
    @FXML
    private Button CButton;
    @FXML
    private Button DButton;

    public void setQuizQuestion() {
        //TODO: Schwierigkeit setzen
        question = Database.getInstance().getRandomQuestion(1);
        questionLabel.setText(question.getQuestion());
        AButton.setText(question.getCorrectAnswer().getAnswer());
        BButton.setText(question.getFalseAnswer1().getAnswer());
        CButton.setText(question.getFalseAnswer2().getAnswer());
        DButton.setText(question.getFalseAnswer3().getAnswer());
        System.out.println("ich mache was");
    }

    //checkt, ob der gedrückte Knopf die korrekte Antwort enthält
    @FXML
    public void actionPerformed(ActionEvent e) {
        boolean correctAnswer = false;

        if(checkAnswer(e, AButton)) correctAnswer = true;
        else if(checkAnswer(e, BButton)) correctAnswer = true;
        else if(checkAnswer(e, CButton)) correctAnswer = true;
        else if(checkAnswer(e, DButton)) correctAnswer = true;

        if(correctAnswer) {
            setQuizQuestion();
            anzahl++;
            System.out.println(anzahl);
        } else {
            //TODO: Game over Screen
            System.out.println("Game over");
        }
    }

    private boolean checkAnswer(ActionEvent e, Button button) {
        if(e.getSource() == button) {
            if(button.getText().equals(question.getCorrectAnswer().getAnswer())) {
                System.out.println("yes");
                return true;
            }
        }
        return false;
    }

}

class GetQuestionTask extends Task {

    @Override
    public Question call() throws Exception {
        return Database.getInstance().getRandomQuestion(1);
    }
}
