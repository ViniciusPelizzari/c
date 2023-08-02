package com.formulario.formContato.service.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TwilioService {

    @Value("${twilio.account.sid}")
    private String ACCOUNT_SID;

    @Value("${twilio.auth.token}")
    private String AUTH_TOKEN;

    @Value("${twilio.sender.phone.number}")
    private String SENDER_PHONE_NUMBER;

    public TwilioService() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    public void sendWhatsAppMessage(String phoneNumber, String message) {
        try {
            Message twilioMessage = Message.creator(
                    new PhoneNumber("whatsapp:" + phoneNumber),
                    new PhoneNumber(SENDER_PHONE_NUMBER),
                    message
            ).create();

            System.out.println("Mensagem enviada com sucesso. SID da mensagem: " + twilioMessage.getSid());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

