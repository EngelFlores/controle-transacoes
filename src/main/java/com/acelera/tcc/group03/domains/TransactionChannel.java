package com.acelera.tcc.group03.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "transaction_channel")
public class TransactionChannel implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column (name = "name")
	private String name;
	
	@OneToMany (mappedBy = "transactionChannel")
	@JsonIgnoreProperties("transactionChannel")
	private List<TransactionAccount> transactionAccounts;
	
	@Override
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
        return "TransactionChannel ID: [" + this.getId() + "] Name: [" + this.getName() + "]";
	}
}