package com.acelera.tcc.group03.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "transaction_account")
public class TransactionAccount implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
    
    @Column (name = "id_transaction_channel")
	private Long idTransactionChannel;
    
    @Column (name = "id_transaction_type")
    private Long idTransactionType;
    
    @Column (name = "id_customer_account")
    private Long idCustomerAccount;
    
    @Column (name = "amount")
    private Double amount;
    
    @Column (name = "transaction_moment")
    private LocalDateTime transactionMoment;
	
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}
}