package com.refah.walletwrapper.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Component
public class SmsProvider {

    @Value("${url.sms}")
    private String url;

    @Value("${token.sms}")
    private String token;
    private final RestTemplate restTemplate;


    public SmsProvider(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public boolean sendMessage(String mobileNumber, String content) {
        url = url + "msisdn=" + mobileNumber + "&netaddr=68532399&content=" + content + "&token=" + token;
        ResponseEntity<SmsResponse> response = restTemplate.getForEntity(url, SmsResponse.class);
        return Objects.requireNonNull(response.getBody()).errorCode == 0;
    }
}
