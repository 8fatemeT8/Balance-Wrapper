package com.refah.walletwrapper.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class SmsProvider {
    Logger logger = LoggerFactory.getLogger(SmsProvider.class);

    @Value("${url.sms}")
    private String url;

    @Value("${token.sms}")
    private String token;

    @Value("${mack.sms}")
    private String mackNumber;

    private final RestTemplate restTemplate;


    public SmsProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMessage(String mobileNumber, String content) {
        String requestUrl = url + "msisdn=" + mobileNumber + "&netaddr=" + mackNumber + "&token=" + token + "&content=" + content;
        ResponseEntity<SmsResponse> response = restTemplate.getForEntity(requestUrl, SmsResponse.class);
        if (Objects.requireNonNull(response.getBody()).errorCode != 0)
            logger.error("failed to send sms root cause : " + response.getBody().errorMessage);
    }
}
