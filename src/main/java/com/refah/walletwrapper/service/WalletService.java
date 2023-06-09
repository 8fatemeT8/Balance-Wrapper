package com.refah.walletwrapper.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refah.walletwrapper.model.dto.BalanceDto;
import com.refah.walletwrapper.model.dto.ChildItemBalance;
import com.refah.walletwrapper.model.dto.ItemBalance;
import com.refah.walletwrapper.model.dto.Root;
import com.refah.walletwrapper.model.entity.Transaction;
import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.repository.WalletRepository;
import com.refah.walletwrapper.utils.exception.ResponseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WalletService {
    Logger logger = LoggerFactory.getLogger(WalletService.class);

    private final RestTemplate restTemplate;
    private final WalletRepository walletRepository;
    private final EntityManager entityManager;
    private final UserService userService;

    public WalletService(RestTemplate restTemplate, WalletRepository walletRepository,
                         EntityManager entityManager, UserService userService) {
        this.restTemplate = restTemplate;
        this.walletRepository = walletRepository;
        this.entityManager = entityManager;
        this.userService = userService;
    }

    @Transactional
    @Async
    public void setBalance(List<User> users) {
        logger.info("prepare user data for send to transfer balance");
        users.forEach(
                user -> {
                    Optional<Transaction> transaction = getLastTransaction(user);
                    if (!transaction.isPresent()) return;
                    BalanceDto jsonBody = getRequestDto(user, transaction);
                    if (jsonBody == null) return;
                    HttpEntity body = getHttpEntity(user, transaction, jsonBody);
                    logger.info("send user :" + user.getMobileNumber() + " to " + user.getExcelDetail().getBaseUrl() + "/api/v1/Transaction/TransferBatch");
                    ResponseEntity<String> response = restTemplate.postForEntity(user.getExcelDetail().getBaseUrl() + "/api/v1/Transaction/TransferBatch", body, String.class);
                    logger.info("/api/v1/Transaction/TransferBatch response : " + response);
                    try {
                        Root root = new ObjectMapper().readValue(response.getBody(), Root.class);
                        if (root.isSuccess) {
                            logger.info("sending sms to user :" + user.getMobileNumber());
                            userService.sendSms(user, transaction.get());
                            logger.info("send messages");
                        }
                        if (root.isSuccess && root.data != null) {
                            transaction.get().setReceiptId(root.data.receiptID);
                            transaction.get().setFinished(true);
                            user.getWallet().setBalance(root.data.balances.stream().filter(it -> it.accountKind != null).filter(it -> it.accountKind == 1).findFirst().get().balance + "");
                        } else if (root.message == null) {
                            throw new ResponseException(400, (root.validationErrors).toString());
                        } else {
                            throw new ResponseException(400, root.message);
                        }
                    } catch (JsonProcessingException e) {
                        logger.error(e.getMessage());
                    } finally {
                        walletRepository.save(user.getWallet());
                        entityManager.flush();
                    }
                });
        walletRepository.saveAll(users.stream().map(User::getWallet).collect(Collectors.toList()));
    }

    private static Optional<Transaction> getLastTransaction(User user) {
        return user.getWallet().getTransactions().stream().filter(it -> !it.isFinished()).findFirst();
    }

    private static BalanceDto getRequestDto(User user, Optional<Transaction> transaction) {
        BalanceDto jsonBody;
        try {
            jsonBody = new BalanceDto(Collections.singletonList(
                    new ItemBalance(
                            new ChildItemBalance(user.getExcelDetail().getTenantId(), null, user.getExcelDetail().getAccountNumberCode()),
                            new ChildItemBalance(user.getExcelDetail().getTenantId(), user.getMobileNumber(), null),
                            new Long(transaction.get().getAmount())
                    )));

        } catch (NumberFormatException e) {
            return null;
        }
        return jsonBody;
    }

    private HttpEntity getHttpEntity(User user, Optional<Transaction> transaction, BalanceDto jsonBody) {
        logger.info("fill request headers");
        HttpHeaders header = new HttpHeaders();
        String transactionId = UUID.randomUUID().toString();
        logger.info("transfer balance for user :" + user.getMobileNumber() + " with transactionId:" + transactionId);
        header.set("OS", "web");
        header.set("AID", "RefahMarket");
        header.set("TransactionID", transactionId);
        header.set("Content-Type", "application/json");
        header.set("Authorization", "Bearer " + user.getExcelDetail().getAuthKey());
        HttpEntity body = new HttpEntity(jsonBody, header);
        transaction.get().setTransactionId(transactionId);
        return body;
    }
}
