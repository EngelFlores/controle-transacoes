package com.acelera.tcc.group03.repositories;

import com.acelera.tcc.group03.domains.Bank;

public class BankDAO extends DAO<Bank> {
    @Override
    protected String getSqlString() {
        return "SELECT b FROM Bank b";
    }
    
    @Override
    protected Class<Bank> getClassName() {
        return Bank.class;
    }
}