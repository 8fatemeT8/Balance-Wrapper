package com.refah.walletwrapper.controller;


import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.service.UserWalletService;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("api/web/user-wallet")
public class UserWalletController {
    Logger logger = LoggerFactory.getLogger(UserWalletController.class);

    private final UserWalletService userWalletService;

    public UserWalletController(UserWalletService userWalletService) {
        this.userWalletService = userWalletService;
    }

    @Timed
    @Transactional
    @PostMapping("/upload/user/excel")
    public ResponseEntity<?> readData(@RequestParam("file") MultipartFile file, Long tenantId, String accountNumberCode, String baseUrl, String accessToken) {
        logger.info("api params -> { tenantId:" + tenantId + ", accountNumberCode :" + accountNumberCode + ", baseUrl:" + baseUrl + "}");
        List<User> users = userWalletService.readExcelAndSave(file, tenantId, accountNumberCode, baseUrl, accessToken);
        logger.info("save all data on database");
        userWalletService.registerAndSetBalance(users);
        logger.info("complete api");
        return ResponseEntity.accepted().body("عملیات با موفقیت پایان یافت");
    }
}
