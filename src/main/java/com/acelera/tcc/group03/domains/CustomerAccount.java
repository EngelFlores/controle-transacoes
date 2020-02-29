package com.acelera.tcc.group03.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "customer_account")
public class CustomerAccount implements BaseEntity {
	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "customer_id")
	private Customer customer;
    
    @ManyToOne
    @JoinColumn(name = "agency_id")
	private Agency agency;
    
	@Column (name = "account_balance")
	private Double accountBalance;
	
    public Long getId() {
		return this.id;
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