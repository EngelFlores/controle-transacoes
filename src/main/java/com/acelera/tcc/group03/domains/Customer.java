package com.acelera.tcc.group03.domains;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table (name = "customer")
public class Customer implements BaseEntity {
	@Id
	@GeneratedValue (generator = "increment")
	@Column (name = "id")
	private Long id;
	
    @OneToMany(mappedBy="customer")
    private List<CustomerAccount> customerAccounts;
	
	@Column (name = "name")
	private String name;
	
	@Column (name = "type")
	private CustomerType type;
	
	@Column (name = "tin")
	private String tin;
	
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
	
	public CustomerType getType() {
		return this.type;
	}
	
	public void setType(CustomerType type) {
		this.type = type;
	}
	
	public String getTin() {
		return this.tin;
	}
	
	public void setTin(String tin) {
		this.tin = tin;
	}
	
	@Override
    public String toString() {
        return "Customer ID:[" + this.getId() + "] Name: [" + this.getName() + "] Type: [" + this.getType() + "] TIN: [" + this.getTin() + "]";
    }
}