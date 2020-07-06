package com.acelera.tcc.group03.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.CustomerAccount;
import com.acelera.tcc.group03.domains.TransactionAccount;
import com.acelera.tcc.group03.repositories.TransactionAccountRepository;

@Service
public class TransactionAccountService {
    private TransactionAccountRepository repository;
    
    public TransactionAccountService(TransactionAccountRepository repository) {
        this.repository = repository;
    }
	
	public List<TransactionAccount> getAll() {
		return this.repository.findAll();
	}
	
	public List<TransactionAccount> getForStatement(CustomerAccount customerAccount, Long amount) {
		return this.repository.findByCustomerAccountEqualsAndTransactionMomentGreaterThanEqual(customerAccount,LocalDateTime.now().minusDays(amount));
	}
	
	public Optional<TransactionAccount> getById(Long id) {
		return this.repository.findById(id);
	}
	
	public TransactionAccount create(TransactionAccount transactionAccount) {
		return this.repository.save(transactionAccount);
	}
	
	public TransactionAccount update(TransactionAccount transactionAccount) {
		return this.repository.save(transactionAccount);
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}