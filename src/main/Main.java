package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.model.Database;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));

        Parent root = loader.load();
        Controller controller = loader.getController();
        controller.setQuizQuestion();

        primaryStage.setTitle("Wer wird Millionär?");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


        /*
        while(game) {
            Parent root = loader.load();
            Controller controller = loader.getController();
            controller.setQuizQuestion();

            primaryStage.setTitle("Wer wird Millionär?");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
            game = false;

        }*/


    }

    @Override
    public void init() throws Exception {
        super.init();
        Database.getInstance().open();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getInstance().close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
