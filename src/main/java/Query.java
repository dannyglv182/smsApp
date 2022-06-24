import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

/**
* Returns a PostgreSQL INSERT operation of type string
 * This is used to add a copy of the sms that was sent to the database.
 * The string returned here is used by JDBC to execute the query against the database.
 * @return insertOp
 */
public class Query {

    public static String insertMessage(String messageBody){
        // fields Zero-index = recipient's number, 1th-index = message body
        String insertOp = "INSERT INTO message (body) VALUES ('" + messageBody + "')";
        return insertOp;
    }

    public static String insertReceipt(int messageID, String reveiverNumber){
        String messageId = String.valueOf(messageID);
        String insertOp = "INSERT INTO receipt (message_id, receiver_number) " +
                          "VALUES ('" +
                           messageID + "'," +
                           "'" + reveiverNumber +
                           "')";
        return insertOp;
    }
}
