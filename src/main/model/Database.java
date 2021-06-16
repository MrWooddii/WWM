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
    public static final String COLUMN_USED = "used";

    public static final int INDEX_QUESTIONTEXT = 1;
    public static final int INDEX_CORRECT_ANSWER = 2;
    public static final int INDEX_FALSE_ANSWER1 = 3;
    public static final int INDEX_FALSE_ANSWER2 = 4;
    public static final int INDEX_FALSE_ANSWER3 = 5;

    //Schwierigkeit ist abhängig von der Nummer der Frage.
    //1 - 5 = 1
    //6 - 10 = 2
    //11 - 13 = 3
    //14 - 15 = 4

    public static final int INDEX_DIFFICULTY = 6;
    public static final int INDEX_USED = 7;

    private Connection conn;

    //Singleton Pattern (inkl. privater Konstruktor)
    private static Database instance = new Database();

    private Database() {

    }

    //Singleton - Pattern
    public static Database getInstance() {
        return instance;
    }

    public Question getRandomQuestion(int difficulty) {

        try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM " + TABLE_QUESTIONS + " WHERE " + COLUMN_DIFFICULTY + "=? AND used = 0 ORDER BY random() LIMIT 1")) {

            //set difficulty
            ps.setInt(1, difficulty);

            ResultSet result = ps.executeQuery();

            //TODO: get Rowid

            //RowId rowid = result.getRowId("");

            //int id = Integer.parseInt(String.valueOf(rowid));
            //System.out.println("Id: " + id);

            Question question = new Question();

            question.setQuestion(result.getString(COLUMN_QUESTIONTEXT));

            question.setCorrectAnswer(new Answer(result.getString(COLUMN_CORRECT_ANSWER), true));
            question.setFalseAnswer1(new Answer(result.getString(COLUMN_FALSE_ANSWER1), false));
            question.setFalseAnswer2(new Answer(result.getString(COLUMN_FALSE_ANSWER2), false));
            question.setFalseAnswer3(new Answer(result.getString(COLUMN_FALSE_ANSWER3), false));

            result.close();
            ps.close();
            //statement.close();

            //setQuestionUsed(id, 1);

            return question;

        } catch (SQLException e) {
            System.out.println("Could not connect to database ps: " + e.getMessage());
            return null;
        }
    }

    //Die Frage wird als benutzt markiert
    //used kann 0 oder 1 sein

    private void setQuestionUsed(int id, int used) {
        try (PreparedStatement ps = conn.prepareStatement("UPDATE " + TABLE_QUESTIONS + " SET " + COLUMN_USED + "= 1 WHERE ROWID =?")) {

            if(used < 0 || used > 1) {
                used = 1;
            }

            ps.setInt(1, id);
            System.out.println("successfully updated used");

            ps.close();

        } catch (SQLException e) {
            System.out.println("Could not connect to database setQuestion: " + e.getMessage());
        }
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to database (Driver Manager): " + e.getMessage());
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
