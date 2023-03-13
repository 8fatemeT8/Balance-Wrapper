package com.refah.walletwrapper.repository;

import com.refah.walletwrapper.model.entity.ExcelDetail;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelDetailRepository extends BaseJpaRepository<ExcelDetail, Integer> {
}
