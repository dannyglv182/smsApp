import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class Query {

    /**
     * Returns a PostgreSQL INSERT operation in the form of a string.
     * The string returned here is used by JDBC to insert a message into the database.
     * @return insertOp
     */
    public static String insertMessage(String messageBody){
        // fields Zero-index = recipient's number, 1th-index = message body
        String insertOp = "INSERT INTO message (body) VALUES ('" + messageBody + "')";
        return insertOp;
    }

    /**
     * Returns a postGresql INSERT operation in the form of a string.
     * The string returned here is used by JDBC to insert a receipt containing
     * the message id and receiver number into the database.
     * @param messageID
     * @param reveiverNumber
     * @return insertOp
     */
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
