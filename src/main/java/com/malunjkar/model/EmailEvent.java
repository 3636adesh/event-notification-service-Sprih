package com.malunjkar.model;

public class EmailEvent {
    private String recipient;
    private String message;
    private String callbackUrl;

    public EmailEvent() {
    }

    public EmailEvent(String recipient, String message, String callbackUrl) {
        this.recipient = recipient;
        this.message = message;
        this.callbackUrl = callbackUrl;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    @Override
    public String toString() {
        return "EmailEvent{" +
                "recipient='" + recipient + '\'' +
                ", message='" + message + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                '}';
    }
}
