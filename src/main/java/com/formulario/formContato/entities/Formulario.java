package com.formulario.formContato.entities;

import com.formulario.formContato.validator.EmailValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import javax.persistence.Entity;

@Entity
public class Formulario {
    @NotBlank
    private String name;
    @NotBlank
    @Email(message = "Por favor, digite um endereço de e-mail válido.")
    private String email;

    @NotBlank
    @Size(min = 13, message = "Por favor, digite um número de telefone válido.")
    @Pattern(regexp = "^\\+\\d{13}$",
            message = "Por favor, digite um número de telefone válido no formato: +xxxxxxxxxxxxx")
    private String phoneNumber;
    private String company;
    private String topic;
    private String message;
    private Boolean automaticMessage; //if it is automatic message = true, else (custom message) = false;
    private Boolean emailMessage; //if it is email message = true, else (whatsapp message) = false;

    public Formulario(){

    }

    public Formulario(String name, String email, String phoneNumber, String company, String topic, String message) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.topic = topic;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (EmailValidator.validateEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Endereço de e-mail inválido.");
        }
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        String cleanedPhoneNumber = phoneNumber.replaceAll("[^\\d]", "");
        if (!cleanedPhoneNumber.startsWith("+")) {
            throw new IllegalArgumentException("O número de telefone deve começar com '+'.");
        }
        if (cleanedPhoneNumber.length() != 14) {
            throw new IllegalArgumentException("O número de telefone deve ter 14 dígitos, incluindo o '+'.");
        }
        this.phoneNumber = cleanedPhoneNumber;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getAutomaticMessage() {
        return automaticMessage;
    }

    public void setAutomaticMessage(Boolean automaticMessage) {
        this.automaticMessage = automaticMessage;
    }

    public Boolean getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(Boolean emailMessage) {
        this.emailMessage = emailMessage;
    }
}
