package com.acelera.tcc.group03.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acelera.tcc.group03.domains.Agency;
import com.acelera.tcc.group03.services.AgencyService;

@RestController
@RequestMapping("/agency")
public class AgencyController {
    private AgencyService service;
    
    public AgencyController(AgencyService service) {
        this.service = service;
    }
    
    @GetMapping
    public ResponseEntity<List<Agency>> getAll() {
        List<Agency> agencies = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(agencies);
    }
    
    @GetMapping ("/{id}")
    public ResponseEntity<Optional<Agency>> getById(@PathVariable("id") Long id) {
        Optional<Agency> agency = this.service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(agency);
    }
    
    @PostMapping
    public ResponseEntity<Agency> create(@RequestBody Agency agency) {
    	this.service.create(agency);
        return ResponseEntity.status(HttpStatus.OK).body(agency);
    }
    
    @PutMapping
    public ResponseEntity<Agency> update(@RequestBody Agency agency) {
    	this.service.update(agency);
        return ResponseEntity.status(HttpStatus.OK).body(agency);
    }
    
    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    	this.service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}