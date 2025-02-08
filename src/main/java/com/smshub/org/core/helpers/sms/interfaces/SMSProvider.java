package com.smshub.org.core.helpers.sms.interfaces;

public interface SMSProvider {
    void send(String receiver, String message);
    String getToken();
}
