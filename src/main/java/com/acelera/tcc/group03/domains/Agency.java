package com.acelera.tcc.group03.domains;

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
@Table (name = "agency")
public class Agency implements BaseEntity {
	@Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  @Column(name = "id", updatable = false, nullable = false)
	private Long id;
    
  @Column (name = "name")
	private String name;
	
	@Column (name = "number")
	private String number;
	
	@ManyToOne
	@JoinColumn (name = "id_bank")
	@JsonIgnoreProperties ("agencies")
	private Bank bank;

	public Agency(String name, String number, Bank bank) {
		this.name = name;
		this.number = number;
		this.bank = bank;
	}

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
	
	public String getNumber() {
		return this.number;
	}
	
	public void setNumber(String number) {
		this.number = number;
	}
	
	public Bank getBank() {
		return bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	@Override
    public String toString() {
        return "Agency ID: [" + this.getId() + "] Name: [" + this.getName() + "] Number: [" + this.getNumber() + "]";
    }
}