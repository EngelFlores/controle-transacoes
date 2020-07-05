package com.acelera.tcc.group03.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.acelera.tcc.group03.domains.TransactionChannel;
import com.acelera.tcc.group03.repositories.TransactionChannelRepository;

@Service
public class TransactionChannelService {
    private TransactionChannelRepository repository;

    public TransactionChannelService(TransactionChannelRepository repository){this.repository = repository;}

    public List<TransactionChannel> getAll (){return this.repository.findAll();}

    public Optional<TransactionChannel> getById(Long id){return this.repository.findById(id);}

    public TransactionChannel update(TransactionChannel transactionChannel){
        return this.repository.save(transactionChannel);
    }

    public TransactionChannel create (TransactionChannel transactionChannel){
        if(transactionChannel.getName() == null){
            throw new NullPointerException();
        }
        return this.repository.save(transactionChannel);
    }

    public void delete (Long id){this.repository.deleteById(id);}
}