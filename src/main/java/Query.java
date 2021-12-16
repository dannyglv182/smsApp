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
