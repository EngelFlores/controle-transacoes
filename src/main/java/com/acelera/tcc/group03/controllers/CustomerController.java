package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.Customer;
import com.acelera.tcc.group03.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAll(){
        List<Customer> customers = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        service.create(customer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Customer> update(@RequestBody Customer customer){
        service.update(customer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteCostumer(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}