package com.smshub.org.core.helpers.sms.impl;

import com.smshub.org.core.helpers.sms.interfaces.SMSProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Component
public class OrangeSMSProvider implements SMSProvider {
    @Value("${orange.sms.token.url}")
    private String tokenUrl;

    @Value("${orange.sms.send.url}")
    private String sendSmsUrl;

    @Value("${orange.sms.sender.number}")
    private String senderNumber;

    @Value("${orange.sms.client.id}")
    private String clientId;

    @Value("${orange.sms.client.secret}")
    private String clientSecret;

    @Value("${orange.sms.auth.header}")
    private String authHeader;

    private final RestTemplate restTemplate;

    public OrangeSMSProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void send(String receiver, String message) {
        try {
            String token = getToken();
            String url = String.format(sendSmsUrl, senderNumber);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Accept", "application/json");
            headers.setBearerAuth(token);

            String requestBody = buildSmsRequestBody(senderNumber, receiver, message);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    entity,
                    String.class
            );
        } catch (Exception e) {
            log.error("Failed to send SMS", e);
            throw new RuntimeException("Failed to send SMS: " + e.getMessage(), e);
        }
    }

    @Override
    public String getToken() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.set("Authorization", "Basic " + authHeader);

            HttpEntity<String> entity = new HttpEntity<>("grant_type=client_credentials", headers);

            ResponseEntity<Map> response = restTemplate.exchange(
                    tokenUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            if (response.getBody() != null && response.getBody().containsKey("access_token")) {
                return response.getBody().get("access_token").toString();
            }

            throw new RuntimeException("Failed to get access token");
        } catch (Exception e) {
            log.error("Failed to get access token", e);
            throw new RuntimeException("Failed to get access token: " + e.getMessage(), e);
        }
    }

    private String buildSmsRequestBody(String sender, String receiver, String message) {
        return String.format(
                "{\"outboundSMSMessageRequest\":{" +
                        "\"address\":[\"tel:+%s\"]," +
                        "\"senderAddress\":\"tel:+%s\"," +
                        "\"outboundSMSTextMessage\":{\"message\":\"%s\"}" +
                        "}}",
                receiver, sender, message
        );
    }
}