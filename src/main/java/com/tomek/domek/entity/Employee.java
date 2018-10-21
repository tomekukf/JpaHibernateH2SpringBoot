package com.tomek.domek.entity;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@DiscriminatorColumn(name="Employee_Type")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class Employee {
	
	@GeneratedValue
	@Id
	private Long id;
	
	
	protected String name;
	


	public Employee() {
	}


	public Employee(String name) {
		super();
		this.name = name;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + "]";
	}
	

}
