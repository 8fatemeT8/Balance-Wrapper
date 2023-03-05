package com.refah.walletwrapper

import com.cosium.spring.data.jpa.entity.graph.repository.support.EntityGraphJpaRepositoryFactoryBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@EnableJpaRepositories(
    basePackages = ["com.refah.walletwrapper.repository"],
    repositoryFactoryBeanClass = EntityGraphJpaRepositoryFactoryBean::class
)
class WalletWrapperApplication

fun main(args: Array<String>) {
    runApplication<WalletWrapperApplication>(*args)
}
