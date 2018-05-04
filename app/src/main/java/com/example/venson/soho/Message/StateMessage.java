package com.example.venson.soho.Message;

import java.util.Set;

public class StateMessage {
    private String type;
    private String userEmail;
    private Set<String> userEmails;
    public StateMessage(String type, String userEmail,Set<String> userEmails) {
        super();
        this.type = type;
        this.userEmail = userEmail;
        this.userEmails = userEmails;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getUserEmail() {
        return userEmail;
    }
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    public Set<String> getUserEmails() {
        return userEmails;
    }
    public void setUserEmails(Set<String> userEmails) {
        this.userEmails = userEmails;
    }



}


