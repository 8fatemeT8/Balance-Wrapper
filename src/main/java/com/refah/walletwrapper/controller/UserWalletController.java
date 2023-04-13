package com.refah.walletwrapper.controller;


import com.refah.walletwrapper.model.entity.User;
import com.refah.walletwrapper.service.UserService;
import com.refah.walletwrapper.service.UserWalletService;
import com.refah.walletwrapper.utils.report.ReportGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/web/user-wallet")
public class UserWalletController {
    Logger logger = LoggerFactory.getLogger(UserWalletController.class);

    private final UserWalletService userWalletService;
    private final ReportGenerator reportGenerator;
    private final UserService userService;

    public UserWalletController(UserWalletService userWalletService, ReportGenerator reportGenerator, UserService userService) {
        this.userWalletService = userWalletService;
        this.reportGenerator = reportGenerator;
        this.userService = userService;
    }

    @PostMapping("/upload/user/excel")
    public ResponseEntity<?> readData(@RequestParam("file") MultipartFile file, @RequestParam String accountNumberCode, @RequestParam String companyName, Long tenantId, String baseUrl, String accessToken) {
        logger.info("api params -> { tenantId:" + tenantId + ", accountNumberCode :" + accountNumberCode + ", baseUrl:" + baseUrl + " ,companyName: " + companyName + "}");
        List<User> users = userWalletService.readExcelAndSave(file, tenantId == null ? 6703005 : tenantId, accountNumberCode, companyName, baseUrl == null ? "http://172.16.1.150:8010" : baseUrl, accessToken);
        logger.info("save all data on database");
        userWalletService.registerAndSetBalance(users);
        logger.info("complete api");
        return ResponseEntity.accepted().body("عملیات با موفقیت پایان یافت");
    }

    @GetMapping("/report")
    public ResponseEntity<?> getReport(String companyName, String accountNumberCode, HttpServletResponse response) {
        if (companyName == null && accountNumberCode == null)
            return ResponseEntity.badRequest().body("لطفا یکی از مثادیر شماره حساب یا نام شرکت را وارد نمایید");
        ArrayList<User> users = new ArrayList<>(companyName != null ? userService.getUserByCompanyName(companyName) :
                userService.getUserByAccountNumber(accountNumberCode));
        reportGenerator.getCsv(response, users, companyName == null ? accountNumberCode : companyName);
        return ResponseEntity.ok().build();
    }
}
