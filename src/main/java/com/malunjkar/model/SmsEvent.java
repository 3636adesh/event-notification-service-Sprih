package com.malunjkar.model;

public class SmsEvent {
    private String phoneNumber;
    private String message;
    private String callbackUrl;

    public SmsEvent() {
    }

    public SmsEvent(String phoneNumber, String message, String callbackUrl) {
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.callbackUrl = callbackUrl;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
        return "SmsEvent{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                '}';
    }
}
