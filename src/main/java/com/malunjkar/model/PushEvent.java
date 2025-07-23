package com.malunjkar.model;

public class PushEvent {
    private String deviceId;
    private String message;
    private String callbackUrl;

    public PushEvent() {
    }

    public PushEvent(String deviceId, String message, String callbackUrl) {
        this.deviceId = deviceId;
        this.message = message;
        this.callbackUrl = callbackUrl;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
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
        return "PushEvent{" +
                "deviceId='" + deviceId + '\'' +
                ", message='" + message + '\'' +
                ", callbackUrl='" + callbackUrl + '\'' +
                '}';
    }
}
