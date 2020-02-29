package com.acelera.tcc.group03.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "agency")
public class Agency implements BaseEntity {
	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	private Long id;
	
    @ManyToOne
    @JoinColumn(name = "bank_id")
	private Bank bank;
	
    @OneToMany(mappedBy="agency")
    private List<CustomerAccount> customerAccounts;
    
	@Column (name = "name")
	private String name;
	
	@Column (name = "number")
	private String number;
	
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
		return this.bank;
	}
	
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
	@Override
    public String toString() {
        return "Agency ID: [" + this.getId() + "] Name: [" + this.getName() + "] Type: [" + this.getNumber() + "]";
    }
}