package com.refah.walletwrapper.controller

import com.refah.walletwrapper.service.UserWalletService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.transaction.Transactional

@RestController
@RequestMapping("api/web/user-wallet")
class UserWalletController(
    private val userWalletService: UserWalletService
) {

    @Transactional
    @PostMapping("/upload/user/excel")
    fun test(
        @RequestParam("file") file: MultipartFile, tenantId: Long, accountNumberCode: String, url: String
    ): ResponseEntity<*> {
        val users = userWalletService.readExcelAndSave(file, tenantId, accountNumberCode, url)
        userWalletService.registerAndSetBalance(users)
        return ResponseEntity.accepted().body("عملیات با موفقیت پایان یافت")
    }
}