package com.refah.walletwrapper.repository;

import com.refah.walletwrapper.model.entity.Wallet;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends  BaseJpaRepository<Wallet, Integer>{
}
