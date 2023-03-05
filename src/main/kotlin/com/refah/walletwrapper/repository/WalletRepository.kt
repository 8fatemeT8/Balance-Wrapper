package com.refah.walletwrapper.repository

import com.refah.walletwrapper.model.Wallet
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository : BaseJpaRepository<Wallet, Int> {
}