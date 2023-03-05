package com.refah.walletwrapper.model

import java.util.*
import javax.persistence.*

@MappedSuperclass
open class BaseModel {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    var id: Int? = null

    var createdDate: Date? = null


    @PrePersist
    fun setCreatedDate() {
        if (createdDate == null)
            createdDate = Date()
    }
}