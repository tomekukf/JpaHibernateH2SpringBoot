package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tomek.domek.entity.Passport;

@Repository
@Transactional
// if we need to manipulate on data we need to add @Transacional annoation, for
// findById we dont need it beacuse we are not manipulting data
public class PassportRepository {
	@Autowired
	EntityManager em;

	Logger log = LoggerFactory.getLogger(PassportRepository.class);

	public Passport findById(Long id) {
		return em.find(Passport.class, id);

	}

	public List<Passport> findAll() {
		// TypedQuery<Person> query = em.createNamedQuery("find_all", Person.class);
		TypedQuery<Passport> query = em.createNamedQuery("findAll", Passport.class);
		return query.getResultList();

		// reateQuery("select * from passport", new
		// BeanPropertyRowMapper<Passport>(Passport.class));
	}

	// difference between persit and megre is that when you want to add new things
	// to database you re usuing persist but when you want to update sth in database
	// you're using merge
	public Passport saveOrUpdatePassport(Passport passport) {
		if (passport.getId() == null) {
			em.persist(passport);
		} else {
			em.merge(passport);
		}

		return passport;
	}

	public void delteById(Long id) {
		Passport passport = findById(id);
		em.remove(passport);
	}



	

}
