package com.acelera.tcc.group03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acelera.tcc.group03.domains.TransactionAccount;

@Repository
public interface TransactionAccountRepository  extends JpaRepository<TransactionAccount, Long> {

}