package com.refah.walletwrapper.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
        restTemplate.getForEntity(url, String.class);
        return true;
    }
}
