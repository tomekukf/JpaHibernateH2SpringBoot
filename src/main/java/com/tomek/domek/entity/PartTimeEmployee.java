package com.tomek.domek.entity;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public  class PartTimeEmployee extends Employee {
	
	
	
	private BigDecimal hourSalary;
	


	
	
	public PartTimeEmployee(String name,BigDecimal hourSalary) {
		super(name);
		this.hourSalary = hourSalary;
	}





	public BigDecimal getHourSalary() {
		return hourSalary;
	}





	public void setHourSalary(BigDecimal hourSalary) {
		this.hourSalary = hourSalary;
	}





	public PartTimeEmployee() {
	}


	
	
	
	





	@Override
	public String toString() {
		return "PartTimeEmployee [name=" +name+  ", hourSalary=" + hourSalary + "]";
	}
	
	

}
