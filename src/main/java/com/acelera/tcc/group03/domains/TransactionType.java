package com.acelera.tcc.group03.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "transaction_type")
public class TransactionType implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "action")
	private TransactionTypeAction action;
	
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