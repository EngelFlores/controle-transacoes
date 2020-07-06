package com.acelera.tcc.group03.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "transaction_type")
public class TransactionType implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "action", nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionTypeAction action;
	
	@OneToMany (mappedBy = "transactionType")
	@JsonIgnoreProperties("transactionType")
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
	
	public TransactionTypeAction getAction() {
		return this.action;
	}
	
	public void setAction(TransactionTypeAction action) {
		this.action = action;
	}
	
	@Override
    public String toString() {
        return "TransactionType ID: [" + this.getId() + "] Name: [" + this.getName() + "] Type: [" + this.getAction() + "]";
    }
}