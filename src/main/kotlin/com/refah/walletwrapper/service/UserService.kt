package com.refah.walletwrapper.service

import com.refah.walletwrapper.model.User
import com.refah.walletwrapper.model.UserRegisterDto
import com.refah.walletwrapper.repository.UserRepository
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.text.SimpleDateFormat
import java.util.*


@Service
class UserService(
    private val userRepository: UserRepository,
    private val restTemplate: RestTemplate

) {
    fun saveAll(users: List<User>): List<User> = userRepository.saveAll(users)

    // TODO LOG
    fun registerUsers(users: List<User>): List<User> {
        val pattern = "YYYY-MM-DD HH:mm:ss"
        val simpleDateFormat = SimpleDateFormat(pattern)
        val date = simpleDateFormat.format(Date())
        users.forEach {
            prepareAndRegister(it, date)
        }
        return userRepository.saveAll(users)
    }

    private fun prepareAndRegister(user: User, date: String?) {
        val jsonBody =
            UserRegisterDto(user.mobileNumber!!, user.email!!, user.nationalCode!!, user.firstName!!, user.lastName!!)
        val header = HttpHeaders()
        val transactionId = UUID.randomUUID().toString()
        header.set("OS", "web")
        header.set("AID", "RefahMarket")
        header.set("TransactionDate", date)
        header.set("TransactionID", transactionId)
        header.set("Authorization", user.excelDetail!!.authorization)
        header.set("Content-Type", "application/json")
        val body = HttpEntity(jsonBody, header)
        user.registerTransactionId = transactionId
        val response = restTemplate.postForEntity(user.excelDetail?.url!!, body, String::class.java)
        user.registerTransactionResponseCode = response.body
    }
}