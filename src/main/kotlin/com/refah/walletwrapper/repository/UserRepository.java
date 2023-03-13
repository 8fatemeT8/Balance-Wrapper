package com.refah.walletwrapper.repository;

import com.refah.walletwrapper.model.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaRepository<User, Integer> {
}
