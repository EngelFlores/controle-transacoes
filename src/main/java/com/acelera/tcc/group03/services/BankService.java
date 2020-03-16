package com.acelera.tcc.group03.services;

import java.util.List;
import java.util.Optional;

import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.repositories.BankRepository;
import org.springframework.stereotype.Service;

@Service
public class BankService {
    private BankRepository repository;

    public BankService(BankRepository repository) {
        this.repository = repository;
    }

    public List<Bank> getAll() {
        return this.repository.findAll();
    }

    public Optional<Bank> getById(Long id) {
        return repository.findById(id);
    }

    public Bank create(Bank bank) {
        return this.repository.save(bank);
    }

    public Bank update(Bank bank) {
        return this.repository.save(bank);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}