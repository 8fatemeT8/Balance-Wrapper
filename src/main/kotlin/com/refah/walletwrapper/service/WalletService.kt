package com.refah.walletwrapper.service

import com.refah.walletwrapper.model.BalanceDto
import com.refah.walletwrapper.model.ChildItemBalance
import com.refah.walletwrapper.model.ItemBalance
import com.refah.walletwrapper.model.User
import com.refah.walletwrapper.repository.WalletRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.UUID

@Service
class WalletService(
    private val restTemplate: RestTemplate,
    private val walletRepository: WalletRepository
) {
    // TODO LOG
    @Async
    fun setBalance(users: List<User>) {
        users.forEach {
            val jsonBody = BalanceDto(
                arrayListOf(
                    ItemBalance(
                        Source = ChildItemBalance(
                            it.excelDetail?.tenantId!!,
                            AccountNumberCode = it.excelDetail?.accountNumberCode
                        ),
                        Target = ChildItemBalance(
                            it.excelDetail?.tenantId!!,
                            MobileNumber = it.mobileNumber
                        ),
                        Amount = it.wallet?.balance?.toLong()!!
                    )
                )
            )
            val header = HttpHeaders()
            val transactionId = UUID.randomUUID().toString()
            header.set("OS", "web")
            header.set("AID", "RefahMarket")
            header.set("TransactionID", transactionId)
            header.set("Content-Type", "application/json")
            val body = HttpEntity(jsonBody, header)
            it.wallet!!.transactionId = transactionId
            val response = restTemplate.postForEntity(it.excelDetail?.url!!, body, String::class.java)
            it.wallet!!.transactionResponseCode = response.body
        }
        walletRepository.saveAll(users.map { it.wallet })
    }
}