package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tomek.domek.entity.Employee;
import com.tomek.domek.entity.FullTimeEmployee;
import com.tomek.domek.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {
	@Autowired
	EntityManager em;

	Logger log = LoggerFactory.getLogger(EmployeeRepository.class);

	public void insertEmployee(Employee employee) {
		em.persist(employee);
	}

	public List<Employee> retriveAllEmployess() {
		return em.createQuery("Select e from Employee e", Employee.class).getResultList();
	}

	public List<PartTimeEmployee> retriveAllPartTimeEmployess() {
		return em.createQuery("Select e from PartTimeEmployee e", PartTimeEmployee.class).getResultList();
	}

	public List<FullTimeEmployee> retriveAllFullTimeEmployess() {
		return em.createQuery("Select e from FullTimeEmployee e", FullTimeEmployee.class).getResultList();
	}

}
