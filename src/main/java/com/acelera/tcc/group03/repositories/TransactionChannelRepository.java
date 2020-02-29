package com.acelera.tcc.group03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acelera.tcc.group03.domains.TransactionChannel;

@Repository
public interface TransactionChannelRepository extends JpaRepository<TransactionChannel, Long> {
}