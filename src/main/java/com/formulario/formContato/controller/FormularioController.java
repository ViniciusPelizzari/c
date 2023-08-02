package com.formulario.formContato.controller;

import com.formulario.formContato.service.FormularioService;
import com.formulario.formContato.entities.Formulario;
import com.formulario.formContato.formularioDTO.FormularioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping("/enviarForm")
    public String enviarFormulario(@ModelAttribute FormularioDTO formularioDTO) {
        Formulario formulario = new Formulario();
        formulario.setName(formularioDTO.getName());
        formulario.setEmail(formularioDTO.getEmail());
        formulario.setPhoneNumber(formularioDTO.getPhoneNumber());
        formulario.setCompany(formularioDTO.getCompany());
        formulario.setTopic(formularioDTO.getTopic());
        formulario.setMessage(formularioDTO.getMessage());
        formulario.setAutomaticMessage(formularioDTO.getAutomaticMessage());
        formulario.setEmailMessage(formularioDTO.getEmailMessage());

        boolean sendAutomaticMessage = formulario.getAutomaticMessage() != null && formulario.getAutomaticMessage();

        boolean sendMessageEmail = formulario.getEmailMessage() != null && formulario.getEmailMessage();

        String autoTopicEmail = "";
        String autoMessageEmail = "";
        String autoMessageWhatsApp = "";

        if (sendAutomaticMessage) {
            if (sendMessageEmail) {
                formularioService.enviarEmail(formulario.getEmail(), autoTopicEmail, autoMessageEmail);
            } else {
                formularioService.enviarMensagemWhatsapp(formulario.getPhoneNumber(), autoMessageWhatsApp);
            }
        } else {
            formularioService.sendMessageCompany(formulario);
        }
        return "redirect:/sucesso";
    }

    @GetMapping("/sucesso")
    public String paginaSucesso() {
        return "sucesso";
    }
}

