package com.acelera.tcc.group03.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "customer_account")
public class CustomerAccount implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "id_customer")
	private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "id_agency")
	private Agency agency;
    
	@Column (name = "account_balance")
	private Double accountBalance;
	
	public CustomerAccount() {
	}
	
	public CustomerAccount(Customer customer, Agency agency, Double accountBalance) {
		this.customer = customer;
		this.agency = agency;
		this.accountBalance = accountBalance;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Agency getAgency() {
		return this.agency;
	}
	
	public void setAgency(Agency agency) {
		this.agency = agency;
	}
	
	public Double getAccountBalance() {
		return accountBalance;
	}
	
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	@Override
    public String toString() {
        return "Customer Account ID: [" + this.getId() + "] Balance: [" + this.getAccountBalance() + "]";
    }
}