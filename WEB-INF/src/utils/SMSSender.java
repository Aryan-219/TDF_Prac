package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSSender {
    static final String ACCOUNT_SID = "";
    static final String AUTH_TOKEN = "";

    public static void sendOTP(String phone, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
            new com.twilio.type.PhoneNumber("+91"+phone),
                new com.twilio.type.PhoneNumber(""),
                "Welcome to TDF! OTP: "+otp)
            .create();

        System.out.println(message.getBody());
    }
}
