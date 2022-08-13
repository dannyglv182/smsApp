// import com.twilio.twiml.MessagingResponse;
// import com.twilio.twiml.messaging.Body;
// import com.twilio.twiml.messaging.Message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class helper {

    /**
     * Reads a POST request to get recipient and message.
     * The req.body() String contains the recipient number followed by the message body
     * The message and phone number use Q1 and Q2 as ids in the html form and are
     * extracted here using the replaceFirst and split functions with regex.
     * returns an array containing The recipient number and the message body.
     * @param body - a spark request.body object
     * @return dto - an array containing the recipient phone number and message body
     */
    public static String[] DtoParser(String body){
        String[] dto = body.replaceFirst("(&?Q[0-9]=)", "").split("(&?Q[0-9]=)");
        dto[1] = dto[1].replace('+', ' ');

        // Handle errors in user input

        // Return the array
        return dto;
    }


    public static int sendQuestion(String question, String receiver){
        Twilio.init(cred.twilUsername,cred.twilPassword);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(receiver),    // The receiver +15551234567 format
                new com.twilio.type.PhoneNumber(cred.twilPhoneNUmber),    // The sender same format as receiver
                question)
                .create();
        return 0;
    }
}
