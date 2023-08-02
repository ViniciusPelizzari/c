package com.formulario.formContato.whatsapp;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class Example {
    public static final String ACCOUNT_SID = System.getenv("AC31ea49c8190acd3e196c2e7e5e33bdc5");
    public static final String AUTH_TOKEN = System.getenv("763d98fdf40a4981403aa64e6cc3c9a3");

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+15005550006"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "Hello there!")
                .create();

        System.out.println(message.getSid());
    }
}
