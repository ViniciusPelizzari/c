package com.formulario.formContato.controller;

import com.formulario.formContato.service.FormularioService;
import com.formulario.formContato.entities.Formulario;
import com.formulario.formContato.formularioDTO.FormularioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    /**
     * Exibe o formulário para cadastro de uma nova pessoa.
     *
     * @param model É utilizado para enviar atributos para a page.
     * @return A página do formulário.
     */
    @RequestMapping("/form")
    public String exibirFormulario(Model model) {
        model.addAttribute("formulario", new Formulario());
        return "formulario";
    }

    @PostMapping("/enviar-form")
    public String enviarFormulario(@ModelAttribute FormularioDTO formularioDTO) {
        try {
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

            String autoTopicEmail = "Confirmação de Recebimento - Formulário de contato";

            String autoMessageEmail = "Olá " + formulario.getName() + ",\n" +
                    "\n" +
                    "Agradecemos por entrar em contato conosco! Esta é uma confirmação de que recebemos o seu formulário de contato com sucesso.\n" +
                    "\n" +
                    "Assunto da Mensagem: " + formulario.getTopic() + ".\n" +
                    "\n" +
                    "Em breve, um dos nossos representantes entrará em contato para fornecer mais informações ou responder à sua solicitação relacionada ao assunto acima.\n" +
                    "\n" +
                    "Obrigado novamente por nos contatar e tenha um ótimo dia!\n" +
                    "\n" +
                    "Atenciosamente,\n" +
                    "Vinícius Pelizzari\n";

            String autoMessageWhatsApp = "Olá " + formulario.getName() + "! \uD83D\uDE04 \n" +
                    "\n" +
                    "Agradecemos por entrar em contato conosco! \uD83D\uDE4C \n" +
                    "\n" +
                    "Recebemos sua mensagem sobre o assunto: " + formulario.getTopic() + ".\n" +
                    "\n" +
                    "Nossa equipe de especialistas já está trabalhando para te ajudar da melhor maneira possível. \uD83E\uDD1D \n" +
                    "\n" +
                    "Em breve, você receberá uma resposta personalizada com todas as informações que precisa. Enquanto isso, fique à vontade para conferir nosso site: http://vinicius-ps.vercel.app \uD83C\uDF10 \n" +
                    "\n" +
                    "Se tiver mais alguma dúvida ou precisar de algo urgente, não hesite em nos ligar no número (45)99862-0300 ou responder a esta mensagem.\n" +
                    "\n" +
                    "Agradecemos novamente por escolher nossos serviços e estamos ansiosos para te atender!\n" +
                    "\n" +
                    "Atenciosamente,\n" +
                    "Vinícius Pelizzari\n";

            if (sendAutomaticMessage) {
                if (sendMessageEmail) {
                    formularioService.enviarEmail(formulario.getEmail(), autoTopicEmail, autoMessageEmail);
                    formularioService.sendMessageCompany(formulario);
                } else {
                    formularioService.enviarMensagemWhatsapp(formulario.getPhoneNumber(), autoMessageWhatsApp);
                    formularioService.sendMessageCompany(formulario);
                }
            } else {
                formularioService.sendMessageCompany(formulario);
            }
        } catch (Exception exception){
            return "redirect:/erro";
        }
        return "redirect:/sucesso";
    }

    /**
     * Exibe a página de sucesso.
     *
     * @return A página de sucesso.
     */
    @GetMapping("/sucesso")
    public String paginaSucesso() {
        return "sucesso";
    }

    /**
     * Exibe a página de erro.
     *
     * @return A página de erro.
     */
    @GetMapping("/erro")
    public String paginaErro() {
        return "erro";
    }

}

