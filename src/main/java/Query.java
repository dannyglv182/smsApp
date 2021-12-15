import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;


public class Query {

    public static String insertMessage(String messageBody){
        // fields Zero-index = recipient's number, 1th-index = message body
        String insertOp = "INSERT INTO message (body) VALUES ('" + messageBody + "')";
        return insertOp;
    }

    public static String StoreReceipt(){
        return "a";
    }

    /*
    Creates a MySQL syntax string for JDBC to insert a new form into the db
    @param String[] array containing the new form name and questions.
    */
    public static String CreateNewForm(String[] fields){

        /* Create the MySQL query for inserting the new form */
        String insertForm =  "INSERT INTO FORM (name) VALUES ('" + fields[0] + "')";
        return insertForm;
    }


    /*
    Creates an array of MySQL syntax Strings for JDBC to insert the questions
    belonging to a form
    @param String[] array containing the form name and questions.
    @param int the form's primary key
    */
    public static String[] CreateQuestions(String[] fields, int form_id){
        String id = String.valueOf(form_id);
        String[] questions = Arrays.copyOfRange(fields, 1, fields.length);       // Array of only the questions
        String[] sqlQueries = new String[questions.length];
        for (int i = 0; i < sqlQueries.length; i++){
            sqlQueries[i] =  "insert into question (form_id, text) values(" + id + "," + "'" + questions[i] + "'" + ")";
        }
        return sqlQueries;
    }

    /**************************************************************************
     * SELECT the questions from a questionnaire that have been sent to a contact
     * @param form_name
     *************************************************************************/
    public static String getQuestionsSent(String form_name){
        String getQuestionSent = "SELECT questionSent.question_id FROM questionSent, form WHERE form.id = questionSent.form_id AND form.name = " + "'" + form_name + "'";
        return getQuestionSent;
    }


    /**************************************************************************
     * SELECT the first question from a questionnaire
     * @param form_name
     *************************************************************************/
    public static String GetFirstQuestion(String form_name){
        String getQuestion = "SELECT min(question.id), question.text FROM form, question WHERE form.name = " + "'" + form_name + "'" + "AND form.id = question.form_id";
        return  getQuestion;
    }
}
