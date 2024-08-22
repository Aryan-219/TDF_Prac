package utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSSender {
    static final String ACCOUNT_SID = "AC9a944ee37dbe214bcff11881d473cee3";
    static final String AUTH_TOKEN = "532bd41a586858ce9935c0efbff0e0ca";

    public static void sendOTP(String phone, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
            new com.twilio.type.PhoneNumber("+91"+phone),
                new com.twilio.type.PhoneNumber("+14432054383"),
                "Welcome to TDF! OTP: "+otp)
            .create();

        System.out.println(message.getBody());
    }
}
