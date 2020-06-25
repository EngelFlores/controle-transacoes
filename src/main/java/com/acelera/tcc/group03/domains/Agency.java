package com.acelera.tcc.group03.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Override
    public String toString() {
        return "Agency ID: [" + this.getId() + "] Name: [" + this.getName() + "] Number: [" + this.getNumber() + "]";
    }
}