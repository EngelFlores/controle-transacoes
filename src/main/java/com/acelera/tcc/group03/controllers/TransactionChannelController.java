package com.acelera.tcc.group03.controllers;

import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.services.TransactionChannelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction-channel")
public class TransactionChannelController {
    private TransactionChannelService service;

    public TransactionChannelController (TransactionChannelService service){this.service = service;}

    @GetMapping
    public ResponseEntity<List<TransactionChannel>> getAll() {return ResponseEntity.ok(this.service.getAll());}

    @RequestMapping("/{id}")
    @GetMapping
    public ResponseEntity getTransactionChannel(@PathVariable("id") Long id){
        TransactionChannel transactionChannel = service.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionChannel);
    }

    @PutMapping
    public ResponseEntity update(@RequestBody TransactionChannel transactionChannel){
        service.update(transactionChannel);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @PostMapping
    public ResponseEntity<TransactionChannel> create (@RequestBody TransactionChannel transactionChannel){
        this.service.create(transactionChannel);
        return ResponseEntity.status(HttpStatus.OK).body(transactionChannel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete (@PathVariable("id") Long id){
        this.service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}