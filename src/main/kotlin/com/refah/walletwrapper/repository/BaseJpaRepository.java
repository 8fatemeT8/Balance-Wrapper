package com.refah.walletwrapper.repository;

import com.refah.walletwrapper.model.entity.BaseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface BaseJpaRepository<TEntity extends BaseModel, TId extends Serializable> extends JpaRepository<TEntity, TId> {
}
