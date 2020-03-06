package com.acelera.tcc.group03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acelera.tcc.group03.domains.TransactionType;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TransactionTypeRepository extends JpaRepository<TransactionType, Long> {
}