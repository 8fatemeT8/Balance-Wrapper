package com.refah.walletwrapper.model

import org.hibernate.envers.Audited
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Audited
@Table(name = "users")
class User : BaseModel() {

    var firstName: String? = null

    var lastName: String? = null

    var nationalCode: String? = null

    @Column(nullable = false)
    var mobileNumber: String? = null

    var email: String? = ""

    var registerTransactionId: String? = null

    var registerTransactionResponseCode: String? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var wallet: Wallet? = null

    @ManyToOne
    @JoinColumn(name = "excel_detail_id")
    var excelDetail: ExcelDetail? = null
}