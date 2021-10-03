// import com.twilio.twiml.MessagingResponse;
// import com.twilio.twiml.messaging.Body;
// import com.twilio.twiml.messaging.Message;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class helper {

    /*
    Parse a Spark req.body() String from a POST request.
    The req.body() String contains the form name followed by it's questions
    @param String the req.body String
    returns an array of Strings containing The form name and questions in the form.
    */
    public static String[] DtoParser(String body){
        System.out.println(body);
        String[] dto = body.replaceFirst("(&?Q[0-9]=)", "").split("(&?Q[0-9]=)");
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


    public static int sendQuestionnaire(String[] questionnaire){

        // Send first question to the contact's cell phone

        // If the contact responds, send the following question in the questionnaire

        // Repeat until every question has been sent.
        return 0;
    }
}
