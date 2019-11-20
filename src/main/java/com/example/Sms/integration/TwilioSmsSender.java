package com.example.Sms.integration;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("twilio")
public class TwilioSmsSender implements smsSender{

    private static  final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private  final TwilioConfiguration twilioConfiguration;

    @Autowired
    public TwilioSmsSender(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public void sendSms(SmsRequest smsRequest)
    {
        if (isPhoneNumberValid(smsRequest.getPhoneNumber())) {
            //get the phone number to
            PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
//            System.out.println(smsRequest.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrailNumber());
            String message = smsRequest.getMessage();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send Sms {} " + smsRequest);
            //LOGGER.info("Phone Number:", + to);
//            LOGGER.info("Phone Number:",from);
        }
        else {
            throw new IllegalArgumentException("Phone Number [" + smsRequest.getPhoneNumber() + "] is not valid number");
        }

    }

    private boolean isPhoneNumberValid(String phoneNumber)
    {
        //TODO: Implement phone number validator

        return true;
    }
}
