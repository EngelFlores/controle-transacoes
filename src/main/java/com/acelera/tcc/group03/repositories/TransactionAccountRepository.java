package com.acelera.tcc.group03.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionAccount;

@Repository
public interface TransactionAccountRepository  extends JpaRepository<TransactionAccount, Long> {
	public List<TransactionAccount> findByCustomerAccountEqualsAndTransactionMomentGreaterThanEqual(CustomerAccount customerAccount,LocalDateTime data);
}