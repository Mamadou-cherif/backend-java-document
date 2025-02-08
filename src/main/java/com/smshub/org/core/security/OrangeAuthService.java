package com.smshub.org.core.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

//@Service
//@Slf4j
public class OrangeAuthService {
    private final RestTemplate restTemplate;
    private final String clientId;
    private final String clientSecret;
    private String accessToken;
    private Instant tokenExpiration;

    public OrangeAuthService(RestTemplate restTemplate, String clientId, String clientSecret) {
        this.restTemplate = restTemplate;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public String getAccessToken() {
        if (accessToken == null || Instant.now().isAfter(tokenExpiration)) {
            refreshToken();
        }
        return accessToken;
    }

    private void refreshToken() {
        
    }
}
