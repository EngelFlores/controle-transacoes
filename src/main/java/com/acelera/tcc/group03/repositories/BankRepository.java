package com.acelera.tcc.group03.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acelera.tcc.group03.domains.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>  {
}