package com.formulario.formContato.service;

import com.formulario.formContato.entities.Formulario;
import com.formulario.formContato.service.email.EmailService;
import com.formulario.formContato.service.twilio.TwilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormularioService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private TwilioService twilioService;

    public void enviarEmail(String destinatario, String assunto, String corpo) {
        emailService.sendEmail(destinatario, assunto, corpo);
    }

    public void enviarMensagemWhatsapp(String destinatario, String mensagem) {
        twilioService.sendWhatsAppMessage(destinatario, mensagem);
    }

    public void sendMessageCompany(Formulario formulario) {
        String companyEmail = "vinipelizzari2410@gmail.com";
        String assunto = "Novo formulário recebido";
        String corpo = "Novo formulário recebido com os seguintes dados:\n"
                + "Nome: " + formulario.getName() + "\n"
                + "E-mail: " + formulario.getEmail() + "\n"
                + "Telefone: " + formulario.getPhoneNumber() + "\n"
                + "Empresa: " + formulario.getCompany() + "\n"
                + "Assunto: " + formulario.getTopic() + "\n"
                + "Mensagem: " + formulario.getMessage() + "\n"
                + "Forma de envio: " + formulario.getEmailMessage() + " | se for TRUE - mensagem por e-mail, se for FALSE - mensagem por WhatsApp";
        enviarEmail(companyEmail, assunto, corpo);
    }
}
