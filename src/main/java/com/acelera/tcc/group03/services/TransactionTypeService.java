package com.acelera.tcc.group03.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.TransactionType;
import com.acelera.tcc.group03.repositories.TransactionTypeRepository;

@Service
public class TransactionTypeService {
    private TransactionTypeRepository repository;
    
	public TransactionTypeService(TransactionTypeRepository repository) {
    	this.repository = repository;
    }
	
	public List<TransactionType> getAll() {
		return this.repository.findAll();
	}
	
	public Optional<TransactionType> getById(Long id) {
		return this.repository.findById(id);
	}
	
	public TransactionType create(TransactionType transactionType) {
		return this.repository.save(transactionType);
	}
	
	public TransactionType update(TransactionType transactionType) {
		return this.repository.save(transactionType);
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}