package com.refah.walletwrapper.repository

import com.refah.walletwrapper.model.ExcelDetail
import org.springframework.stereotype.Repository

@Repository
interface ExcelDetailRepository : BaseJpaRepository<ExcelDetail, Int> {
}