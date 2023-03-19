package com.refah.walletwrapper.repository;

import com.refah.walletwrapper.model.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends BaseJpaRepository<User, Integer> {
    List<User> findAllByMobileNumberIn(List<String> mobiles);
}
