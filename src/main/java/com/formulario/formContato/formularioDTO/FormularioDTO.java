package com.formulario.formContato.formularioDTO;

public class FormularioDTO {

    private String name;
    private String email;
    private String phoneNumber;
    private String company;
    private String topic;
    private String message;
    private Boolean automaticMessage;
    private Boolean emailMessage;

    public FormularioDTO(){

    }

    public FormularioDTO(String name, String email, String phoneNumber, String company, String topic, String message, Boolean automaticMessage, Boolean emailMessage) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.company = company;
        this.topic = topic;
        this.message = message;
        this.automaticMessage = automaticMessage;
        this.emailMessage = emailMessage;
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
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
