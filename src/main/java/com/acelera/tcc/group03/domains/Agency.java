package com.acelera.tcc.group03.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table (name = "agency")
public class Agency implements BaseEntity {

	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	private Long id;
    
    @Column (name = "name")
	private String name;
	
	@Column (name = "number")
	private String number;

	@ManyToOne()
	@JoinColumn(name = "bank_id")
	@JsonIgnoreProperties("agencies")
	private Bank bank;
	
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