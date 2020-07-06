package com.acelera.tcc.group03.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@OneToMany (mappedBy = "customerAccount")
	@JsonIgnoreProperties("customerAccount")
	private List<TransactionAccount> transactionAccounts;
	
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