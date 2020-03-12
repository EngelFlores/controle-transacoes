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
public class CustomerConstroller {
    private CustomerService service;

    public CustomerConstroller(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity getAll(){
        List<Customer> customers = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Customer customer){
        service.create(customer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity update(@RequestBody Customer customer){
        service.update(customer);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity getCustomer(@PathVariable("id") Long id) {
        Optional<Customer> customer = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity deleteCostumer(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
