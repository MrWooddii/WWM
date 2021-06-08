package main.model;

import java.sql.*;

public class Database {

    public static final String DB_NAME = "questions.db";

    //relativer Pfad
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    //absoluter Pfad
    //public static final String CONNECTION_STRING = "jdbc:sqlite:E:\\Java\\Projekte\\WWM\\" + DB_NAME;

    public static final String TABLE_QUESTIONS = "questions";
    public static final String COLUMN_QUESTIONTEXT = "questionText";
    public static final String COLUMN_CORRECT_ANSWER = "correctAnswer";
    public static final String COLUMN_FALSE_ANSWER1 = "falseAnswer1";
    public static final String COLUMN_FALSE_ANSWER2 = "falseAnswer2";
    public static final String COLUMN_FALSE_ANSWER3 = "falseAnswer3";
    public static final String COLUMN_DIFFICULTY = "difficulty";

    public static final int INDEX_QUESTIONTEXT = 1;
    public static final int INDEX_CORRECT_ANSWER = 2;
    public static final int INDEX_FALSE_ANSWER1 = 3;
    public static final int INDEX_FALSE_ANSWER2 = 4;
    public static final int INDEX_FALSE_ANSWER3 = 5;

    //Schwierigkeit ist abhängig von der Nummer der Frage.
    //1 - 5 = 1
    //6 - 9 = 2
    //10 - 13 = 3
    //14 - 15 = 4

    public static final int INDEX_DIFFICULTY = 6;

    private Connection conn;

    //Singleton Pattern (inkl. privater Konstruktor)
    private static Database instance = new Database();

    private Database() {

    }

    //Singleton - Pattern
    public static Database getInstance() {
        return instance;
    }



    public void getRandomQuestion(int difficulty) {

        try (Statement statement = conn.createStatement();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions WHERE " + COLUMN_DIFFICULTY + "=? ORDER BY random() LIMIT 1")) {

            //set difficulty
            ps.setString(1, String.valueOf(difficulty));

            ResultSet result = ps.executeQuery();

            Question question = new Question();

            question.setQuestion(result.getString(INDEX_QUESTIONTEXT));
            question.setCorrectAnswer(result.getString(INDEX_CORRECT_ANSWER));
            question.setFalseAnswer1(result.getString(INDEX_FALSE_ANSWER1));
            question.setFalseAnswer2(result.getString(INDEX_FALSE_ANSWER2));
            question.setFalseAnswer3(result.getString(INDEX_FALSE_ANSWER3));

            System.out.println(question);

            result.close();

        } catch (SQLException e) {
            System.out.println("Could not connect to database ps: " + e.getMessage());
        }
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if(conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Could not close connection: " + e.getMessage());
        }
    }
}
