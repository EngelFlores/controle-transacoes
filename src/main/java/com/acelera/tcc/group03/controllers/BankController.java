package com.acelera.tcc.group03.controllers;

import java.util.List;
import java.util.Optional;

import com.acelera.tcc.group03.domains.Bank;
import com.acelera.tcc.group03.services.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankController {
    private BankService service;

    public BankController(BankService service) {
        this.service = service;
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Bank>> getBank(@PathVariable("id") Long id) {
        Optional<Bank> bank = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(bank);
    }

    @GetMapping
    public ResponseEntity<List<Bank>> getAll() {
        return ResponseEntity.ok(this.service.getAll());
    }

    @PostMapping
    public String create(@RequestBody Bank bank) {
        return this.service.create(bank).toString();
    }

    @PutMapping
    public String update(@RequestBody Bank bank) {
        return this.service.update(bank).toString();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}