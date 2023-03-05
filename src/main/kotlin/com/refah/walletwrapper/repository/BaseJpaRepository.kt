package com.refah.walletwrapper.repository

import com.refah.walletwrapper.model.BaseModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseJpaRepository<TEntity : BaseModel, TId : Serializable> : JpaRepository<TEntity, TId> {
}