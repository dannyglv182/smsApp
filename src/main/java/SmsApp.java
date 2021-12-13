import com.twilio.twiml.MessagingResponse;
import com.twilio.twiml.messaging.Body;
import com.twilio.twiml.messaging.Message;

import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.Request;
import static spark.Spark.*;
import spark.template.freemarker.FreeMarkerEngine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class SmsApp {
    public static void main(String[] args) {

        // Freemarker Configuration with path for ftl files. files must go in src/main/resources/template/freemarker
        // This configuration object has to be passed in to the freemarkerengine object
        Configuration configuration = new Configuration(new Version(2, 3, 23));
        configuration.setClassForTemplateLoading(FreeMarkerEngine.class, "/template/freemarker/");

        // JDBC Configuration
        String url = cred.url;
        String user = "postgres";
        String password = cred.password;
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("The connection was successful");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        Connection finalConnection = connection;
        final Connection fC= finalConnection;


        /********************************************************************************
        Home page - View form to create a new questionnaire here.
        ********************************************************************************/
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new FreeMarkerEngine(configuration).render(
                    new ModelAndView(model, "HomePage.ftl")
            );
        });


        /********************************************************************************
         View form to send a questionnaire here.
         ********************************************************************************/
        get("/sendform", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new FreeMarkerEngine(configuration).render(
                    new ModelAndView(model, "sendform.ftl")
            );
        });


        /********************************************************************************
        Endpoint to create a questionnaire.
        The controller should take the fields and push them the database as a new
        questionnaire.
        ********************************************************************************/
        post("/saveform", (req, res) ->{

            // Parse the POST request and create an array of strings
            // only containing the user response portion of the POST request
            String[] fields = helper.DtoParser(req.body());

            // Push the new form to the db
            String push = Query.CreateNewForm(fields);         // The mySQL query to create a new questionnaire
            Statement statement = fC.createStatement();
            statement.execute(push, Statement.RETURN_GENERATED_KEYS);     // INSERTS the new form and returns the new form key

            // Get the new form's primary key
            ResultSet rs = statement.getGeneratedKeys();
            int form_id = -1;
            if (rs.next()){
                form_id = rs.getInt(1);
            }

            // INSERT the questions into the db with the form's pk as their fk
            String[] to_push_questions = Query.CreateQuestions(fields, form_id);
            for (int i = 0; i < to_push_questions.length; i++) {
                statement.execute(to_push_questions[i]);
            }
            return req.body();
        });


        /********************************************************************************
        Endpoint to send out a message.
        ********************************************************************************/
        post("/sendmessage", (req, res) -> {
            int sms = 0;
            int questionId = 0;
            String toSend = "";
            String[] parameters = helper.DtoParser(req.body()); // Zero-index = recipient's number, 1th-index = message body
            sms = helper.sendQuestion(parameters[1], parameters[0]);

            // Statement stmt = fC.createStatement();
            // String queryOne = Query.getQuestionsSent(parameters[0]);  // Mysql query string to select the questions sent from a form (if any)
            // ResultSet rs = stmt.executeQuery(queryOne);

/*            while (rs.next()){      // Sets questionId to the id of the last question sent (if there was any) else questionId remains 0
                questionId= rs.getInt("question_id");
            }

            if (questionId == 0){   // The first question in the questionnaire has not been sent. Let's send it
                String queryTwo = Query.GetFirstQuestion(parameters[0]);
                rs = stmt.executeQuery(queryTwo);
                while (rs.next()){
                    toSend = rs.getString("text");
                    // sms = helper.sendQuestion(toSend, "");
                }
            }
            else {  // A question in the questionnaire has already been sent. Let's send the next question in ascending order by ID

            }*/
            return "Thank you";
        });


        /********************************************************************************
        Twilio webhook - Responds to incoming sms
        ********************************************************************************/
        post("/sms", (req, res) -> {
            String twilRequest = req.body();        // Contains the incoming text cell number and message body
            System.out.println(twilRequest);

            // Check if the the question sent to the user was not the last question in the
            // questionnaire. If it wasn't the last question then send the next question


            res.type("application/xml");
            Body body = new Body
                    .Builder("Thank you for responding.")
                    .build();
            Message sms = new Message
                    .Builder()
                    .body(body)
                    .build();
            MessagingResponse twiml = new MessagingResponse
                    .Builder()
                    .message(sms)
                    .build();
            return twiml.toXml();
        });
    }
}
