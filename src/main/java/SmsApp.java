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


        /**
         * Home page - View form to send a text message.
         */
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new FreeMarkerEngine(configuration).render(
                    new ModelAndView(model, "HomePage.ftl")
            );
        });


        /**
         * Endpoint to send out a message.
         * A text message is sent and a receipt is stored in the database.
         */
        post("/sendmessage", (req, res) -> {
            int sms = 0;
            int questionId = 0;
            String toSend = "";
            String[] parameters = helper.DtoParser(req.body()); // Zero-index = recipient's number, 1th-index = message body
            sms = helper.sendQuestion(parameters[1], parameters[0]);    // Sends the text

            // Stores the message sent in the database
            Statement stmt = fC.createStatement();
            String queryOne = Query.insertMessage(parameters[1]);  // A query string to insert the message into the database
            stmt.execute(queryOne, Statement.RETURN_GENERATED_KEYS);

            // Creates a receipt in the database
            ResultSet rs = stmt.getGeneratedKeys();
            int message_id = -1;
            if (rs.next()){
                message_id = rs.getInt(1);
            }
            String insertReceipt = Query.insertReceipt(message_id, parameters[0]);
            System.out.println(insertReceipt);
            stmt.execute(insertReceipt);

            return "Thank you";
        });


        /**
         * Twilio webhook - Responds to incoming sms
         */
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
