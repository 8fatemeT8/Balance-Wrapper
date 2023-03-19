package com.refah.walletwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refah.walletwrapper.model.dto.RegisterResponseDto;
import com.refah.walletwrapper.model.dto.UserRegisterDto;
import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {
    Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;
    private final EntityManager entityManager;

    public UserService(UserRepository userRepository, RestTemplate restTemplate, EntityManager entityManager) {
        this.userRepository = userRepository;
        this.restTemplate = restTemplate;
        this.entityManager = entityManager;
    }

    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }

    public Map<String, User> getUserByMobileNumber(List<String> mobiles) {
        return userRepository.findAllByMobileNumberIn(mobiles).stream().collect(Collectors.toMap(User::getMobileNumber, user -> user));
    }

    @Transactional
    public void registerUsers(List<User> users) {
        String pattern = "YYYY-MM-DD HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        logger.info("prepare data for send to api");
        users.forEach(user -> {
            try {
                prepareAndRegister(user, date);
            } catch (JsonProcessingException e) {
                logger.error(e.getMessage());
            }
        });
        userRepository.saveAll(users);
        entityManager.flush();
    }

    private void prepareAndRegister(User user, String date) throws JsonProcessingException {
        UserRegisterDto jsonBody = new UserRegisterDto(user.getMobileNumber(), user.getEmail(),
                user.getNationalCode(), user.getFirstName(), user.getLastName());
        logger.info("set header params");
        HttpHeaders header = new HttpHeaders();
        String transactionId = UUID.randomUUID().toString();
        logger.info("set transactionId : " + transactionId + " for user with " + user.getMobileNumber() + " mobileNumber");
        header.set("OS", "web");
        header.set("AID", "RefahMarket");
        header.set("TransactionDate", date);
        header.set("TransactionID", transactionId);
        header.set("Authorization", "Bearer " + user.getExcelDetail().getAuthKey());
        header.set("Content-Type", "application/json");
        HttpEntity body = new HttpEntity(jsonBody, header);
        user.setRegisterTransactionId(transactionId);
        logger.info("send user :" + user.getMobileNumber() + " to " + user.getExcelDetail().getBaseUrl() + "/api/v1/Customer/Register");
        ResponseEntity<String> response = restTemplate.postForEntity(user.getExcelDetail().getBaseUrl() + "/api/v1/Customer/Register", body, String.class);
        logger.info("/api/v1/Customer/Register response : " + response);
        RegisterResponseDto responseBody = new ObjectMapper().readValue(response.getBody(), RegisterResponseDto.class);
        if (responseBody.isSuccess()) {
            user.setRegistered(true);
            if (responseBody.getData() != null && responseBody.getData().getReturnValue() != null)
                user.setRegisterReturnValue(responseBody.getData().getReturnValue().toString());
        } else {
            user.setRegisterErrorCode(responseBody.getErrorCode());
            user.setErrorResponseValidation(responseBody.getValidationErrors());
        }
        user.setRegisterResponse(response.getBody());
    }
}
