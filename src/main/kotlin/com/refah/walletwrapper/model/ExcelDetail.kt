package com.refah.walletwrapper.model

import org.hibernate.envers.Audited
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Audited
@Table(name = "excel_details")
class ExcelDetail : BaseModel() {

    var excelName: String? = null

    var tenantId: Long? = null

    var accountNumberCode: String? = null

    @Column(nullable = false)
    var url: String? = null

    @Column(nullable = false)
    var authorization: String? = null

}