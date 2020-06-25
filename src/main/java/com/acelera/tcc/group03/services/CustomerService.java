package com.acelera.tcc.group03.services;

import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> getAll() {
        return this.repository.findAll();
    }

    public Customer create(Customer customer) {
        if(customer.getName() == null){
            throw new NullPointerException();
        }
        return repository.save(customer);
    }

    public Customer update(Customer customer) {
        return repository.save(customer);
    }

    public Optional<Customer> getById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
