package com.acelera.tcc.group03.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "transaction_channel")
public class TransactionChannel implements BaseEntity {
	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Column (name = "name")
	private String name;
	
	public Long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TransactionChannel(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
        return "TransactionChannel ID: [" + this.getId() + "] Name: [" + this.getName() + "]";
	}
}