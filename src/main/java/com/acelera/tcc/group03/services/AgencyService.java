package com.acelera.tcc.group03.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.Agency;
import com.acelera.tcc.group03.repositories.AgencyRepository;

@Service
public class AgencyService {
    private AgencyRepository repository;
    
	public AgencyService(AgencyRepository repository) {
    	this.repository = repository;
    }
	
	public List<Agency> getAll() {
		return this.repository.findAll();
	}
	
	public Optional<Agency> getById(Long id) {
		return this.repository.findById(id);
	}
	
	public Agency create(Agency transactionType) {
		return this.repository.save(transactionType);
	}
	
	public Agency update(Agency transactionType) {
		return this.repository.save(transactionType);
	}
	
	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}