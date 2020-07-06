package com.acelera.tcc.group03.domains;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "transaction_account")
public class TransactionAccount implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@ManyToOne
	@JoinColumn (name = "id_transaction_channel")
	@JsonIgnoreProperties ("transactionAccounts")
	private TransactionChannel transactionChannel;
    
	@ManyToOne
	@JoinColumn (name = "id_transaction_type")
	@JsonIgnoreProperties ("transactionAccounts")
	private TransactionType transactionType;
    
	@ManyToOne
	@JoinColumn (name = "id_customer_account")
	@JsonIgnoreProperties ("transactionAccounts")
	private CustomerAccount customerAccount;
    
    @Column (name = "amount")
    private Double amount;
    
    @Column (name = "transaction_moment")
    private LocalDateTime transactionMoment;
    
	public Long getId() {
		return this.id;
	}
	
	public TransactionChannel getTransactionChannel() {
		return this.transactionChannel;
	}
	
	public void setTransactionChannel(TransactionChannel transactionChannel) {
		this.transactionChannel = transactionChannel;
	}
	
	public TransactionType getTransactionType() {
		return this.transactionType;
	}
	
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	
	public CustomerAccount getCustomerAccount() {
		return this.customerAccount;
	}

	public void setCustomerAccount(CustomerAccount customerAccount) {
		this.customerAccount = customerAccount;
	}
	
	public Double getAmount() {
		return this.amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public LocalDateTime getTransactionMoment() {
		return this.transactionMoment;
	}
	
	public void setTransactionMoment(LocalDateTime transactionMoment) {
		this.transactionMoment = transactionMoment;
	}
}