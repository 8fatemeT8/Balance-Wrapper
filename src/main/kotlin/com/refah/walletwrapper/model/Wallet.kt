package com.refah.walletwrapper.model

import org.hibernate.envers.Audited
import javax.persistence.Entity
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Audited
@Table(name = "wallets")
class Wallet : BaseModel() {

    var balance: String? = null

    var transactionId: String? = null

    var transactionResponseCode: String? = null

    @OneToOne
    var user: User? = null
}