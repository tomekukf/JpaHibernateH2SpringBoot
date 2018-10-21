package com.tomek.domek.entity;

import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public  class FullTimeEmployee extends Employee {
	

	
	
	private BigDecimal salary;
	


	
	
	public FullTimeEmployee(String name, BigDecimal salary) {
		super(name);
		this.salary = salary;
	}





	public BigDecimal getSalary() {
		return salary;
	}





	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}





	public FullTimeEmployee() {
	}


	
	






	@Override
	public String toString() {
		return "FullTimeEmployee [name="  +name+ ", salary=" + salary + "]";
	}
	

}
