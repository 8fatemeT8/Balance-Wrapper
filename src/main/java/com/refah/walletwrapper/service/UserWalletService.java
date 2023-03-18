package com.refah.walletwrapper.service;

import com.refah.walletwrapper.model.entity.ExcelDetail;
import com.refah.walletwrapper.model.entity.Transaction;
import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.model.entity.Wallet;
import com.refah.walletwrapper.repository.ExcelDetailRepository;
import com.refah.walletwrapper.utils.ReadExcelData;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserWalletService {
    Logger logger = LoggerFactory.getLogger(UserWalletService.class);

    private final FileStorageService fileStorageService;
    private final ExcelDetailRepository excelDetailRepository;
    private final UserService userService;
    private final WalletService walletService;

    public UserWalletService(FileStorageService fileStorageService, ExcelDetailRepository excelDetailRepository,
                             UserService userService, WalletService walletService) {
        this.fileStorageService = fileStorageService;
        this.excelDetailRepository = excelDetailRepository;
        this.userService = userService;
        this.walletService = walletService;
    }


    @Timed
    public List<User> readExcelAndSave(MultipartFile file, Long tenantId, String accountNumberCode, String url, String accessToken) {
        logger.info("saving file to storage");
        fileStorageService.save(file);
        logger.info("read data from excel");
        Date createdDate = new Date();
        ArrayList<ArrayList<String>> data = ReadExcelData.importExcelData(file);
        data.remove(0);
        logger.info("create ExcelDetail in database");
        ExcelDetail excelDetail = initialExcelDetail(file, tenantId, accountNumberCode, url, createdDate, accessToken);
        logger.info("map data to user list");
        List<User> users = mapDataToUserList(data, excelDetail, createdDate);
        logger.info("send data to repository ");
        return userService.saveAll(users);
    }

    @Async
    @Timed
    public void registerAndSetBalance(List<User> users) {
        walletService.setBalance(userService.registerUsers(users));
    }

    private List<User> mapDataToUserList(ArrayList<ArrayList<String>> data, ExcelDetail excelDetail, Date createdDate) {
        return data.stream().map(
                it -> {
                    Wallet wallet = new Wallet();
                    wallet.getTransactions().add(new Transaction(it.get(4), wallet));
                    return new User(it.get(0), it.get(1), it.get(2), it.get(3),"", wallet, excelDetail, createdDate);
                }
        ).collect(Collectors.toList());
    }

    private ExcelDetail initialExcelDetail(MultipartFile file, Long tenantId, String accountNumberCode,
                                           String url, Date createdDate, String accessToken) {
        ExcelDetail excelData = new ExcelDetail(
                file.getOriginalFilename(),
                tenantId,
                accountNumberCode,
                url,
                createdDate,
                accessToken != null ? accessToken : "eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJJZCI6IjE1OWE0Y2FkLTA1OGEtNGI0ZC1hZmIyLWJiNzIxYzQxNjQ3OCIsIkZpcnN0TmFtZSI6IiIsIkxhc3ROYW1lIjoiIiwiUGhvbmVObyI6Ik15X0lyYW5jZWxsIiwiSW1hZ2UiOiIiLCJOYXRpb25hbENvZGUiOiIiLCJCaXJ0aERhdGUiOiIiLCJFbWFpbCI6Ik15TVROQElyYW5jZWxsLmNvbSIsIkNpdHkiOiIiLCJDcmVhdGVBdCI6IjIvNi8yMDIyIDExOjQ0OjAyIEFNIiwiaWJhbiI6IiIsIlN0YXR1cyI6IkFjdGl2ZSIsIkRldmljZUlkIjoiZjIwODhmMTMtY2ExZS00YjA5LWFkOTctZTVmMzUzNDU0N2M4IiwiVGVuYW50SWQiOiI2NzAzMDA1IiwiVGVybWluYWwiOiIxMDAwMDE5MyIsIkpvYkhvbGRlcklkIjoiMTE0NTIzMCIsInJvbGVzSWQiOiI4ZjVmMWY2ZC02NWFlLTQ4Y2ItOWM1MC03NTdkN2QwZTIwMTYiLCJyb2xlIjpbIjEyIiwiMTMiLCIxNCIsIjE2IiwiMTciLCIxOCIsIjIwIiwiMjEiLCIyMiIsIjIzIiwiMjciLCIyOCIsIjI5IiwiMzAiLCIzMSIsIjMyIiwiMzMiLCIzNCIsIjM1IiwiNTMiLCI1NCIsIjU1IiwiNTYiLCI1NyIsIjU4IiwiMTAwIiwiMTAxIiwiMTAyIiwiMTAzIiwiMTA0IiwiMTA1IiwiMTA2IiwiMTA3IiwiMTA4IiwiMTA5IiwiMTEwIiwiMTExIiwiMTE0IiwiMTE3IiwiMTE5IiwiMTIxIiwiMTIyIiwiMTIzIiwiMTI0IiwiMTI1IiwiMTI2IiwiMTI3IiwiMTI4IiwiMTI5IiwiMTMwIiwiMTMxIiwiMTMyIiwiMTMzIiwiMTM0IiwiMTM1IiwiMTM2IiwiMTM3IiwiMTM4IiwiMTM5IiwiMTQwIiwiMTQxIiwiMTQyIiwiMTQzIiwiMTQ0IiwiMTQ1IiwiMTQ2IiwiMTQ3IiwiMTQ4IiwiMTQ5IiwiMTUwIiwiMTUxIiwiMTUyIiwiMTUzIiwiMTU0IiwiMTU1IiwiMTU2IiwiMTU3IiwiMTU4IiwiMTU5IiwiMTYwIiwiMTYxIiwiMTYyIiwiMTYzIiwiMTY0IiwiMTY1IiwiMTY2IiwiMTY3IiwiMTY4IiwiMTY5IiwiMTcwIiwiMTcxIiwiMTcyIiwiMTczIiwiMTc0IiwiMTc1IiwiMTc2IiwiMTc3IiwiMTc4IiwiMTc5IiwiMTgwIiwiMTgxIiwiMTgyIiwiMTgzIiwiMTg0IiwiMTg1IiwiMTg2IiwiMTg3IiwiMTg4IiwiMTg5IiwiMTkwIiwiMTkxIiwiMTkyIiwiMTk0IiwiMTk1IiwiMTk2IiwiMTk3IiwiMTk4IiwiMTk5IiwiMjAwIiwiMjAxIiwiMjAyIiwiMjAzIiwiMjA0IiwiMjA1IiwiMjA2IiwiMjA3IiwiMjA4IiwiMjA5IiwiMjEwIiwiMjExIiwiMjEyIiwiMjEzIiwiMjE0IiwiMjE1IiwiMjE2IiwiMjE3IiwiMjE4Il0sIm5iZiI6MTY1NDUxNDE4NCwiZXhwIjo0ODEwMTg3Nzg0LCJpYXQiOjE2NTQ1MTQxODR9.C5JJFWniDmXpReHpjJDFLMqgLONZ5MPAC1UIl6d4vLbfe4MNNWoaa2vkAnni7ygPdwG2rQeZgBvEy6ltS83e3w");
        logger.info("save excelDetail : " + excelData);
        return excelDetailRepository.save(excelData);
    }
}
