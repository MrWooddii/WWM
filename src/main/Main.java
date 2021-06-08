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

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        super.init();
        Database.getInstance().open();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        Database.getInstance().open();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
