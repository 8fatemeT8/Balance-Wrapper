package com.refah.walletwrapper.service

import com.refah.walletwrapper.model.ExcelDetail
import com.refah.walletwrapper.model.User
import com.refah.walletwrapper.model.Wallet
import com.refah.walletwrapper.repository.ExcelDetailRepository
import com.refah.walletwrapper.utils.ReadExcelData
import io.micrometer.core.annotation.Timed
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.util.Date

@Service
class UserWalletService(
    private val filesStorageService: FilesStorageService,
    private val excelDetailRepository: ExcelDetailRepository,
    private val userService: UserService,
    private val walletService: WalletService
) {

    @Async
    @Timed
    fun readExcelAndSave(file: MultipartFile, tenantId: Long, accountNumberCode: String, url: String): List<User> {
        filesStorageService.save(file)
        val createdDate = Date()
        val data = ReadExcelData.importExcelData(file)
        val excelDetail = initialExcelDetail(file, tenantId, accountNumberCode, url, createdDate)
        val users = mapDataToUserList(data, excelDetail, createdDate)
        return userService.saveAll(users)
    }

    @Async
    @Timed
    fun registerAndSetBalance(users: List<User>) {
        walletService.setBalance(userService.registerUsers(users))
    }

    private fun mapDataToUserList(data: ArrayList<ArrayList<String>>, excelDetail: ExcelDetail, createdDate: Date) =
        data.map {
            User().apply {
                firstName = it[0]
                lastName = it[1]
                nationalCode = it[2]
                mobileNumber = it[3]
                email = it[5]
                this.createdDate = createdDate
                wallet = Wallet().apply {
                    balance = it[4]
                }
                this.excelDetail = excelDetail
            }
        }

    private fun initialExcelDetail(
        file: MultipartFile, tenantId: Long, accountNumberCode: String, url: String, createdDate: Date
    ): ExcelDetail {
        val excelData = ExcelDetail().apply {
            excelName = file.originalFilename
            this.tenantId = tenantId
            this.accountNumberCode = accountNumberCode
            this.url = url
            this.createdDate = createdDate
            this.authorization = "???????"
        }
        return excelDetailRepository.save(excelData)
    }
}