package main;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import main.model.Answer;
import main.model.Database;
import main.model.Question;

import java.util.Arrays;
import java.util.Collections;

public class Controller {

    private int fragenAnzahl = 1;
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

        int difficulty = 1;

        if(fragenAnzahl > 6 && fragenAnzahl <= 10) difficulty = 2;
        if(fragenAnzahl > 10 && fragenAnzahl <= 13) difficulty = 3;
        if(fragenAnzahl > 13) difficulty = 4;

        System.out.println("Fragenanzahl: " + fragenAnzahl);
        System.out.println("Schwierigkeit: " + difficulty);

        question = Database.getInstance().getRandomQuestion(difficulty);
        questionLabel.setText(question.getQuestion());

        Answer[] answers = new Answer[4];
        answers[0] = question.getCorrectAnswer();
        answers[1] = question.getFalseAnswer1();
        answers[2] = question.getFalseAnswer2();
        answers[3] = question.getFalseAnswer3();

        Collections.shuffle(Arrays.asList(answers));

        AButton.setText(answers[0].getAnswer());
        BButton.setText(answers[1].getAnswer());
        CButton.setText(answers[2].getAnswer());
        DButton.setText(answers[3].getAnswer());
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
            fragenAnzahl++;
            setQuizQuestion();
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
