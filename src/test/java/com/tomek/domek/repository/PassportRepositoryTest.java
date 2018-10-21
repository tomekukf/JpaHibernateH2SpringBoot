package com.tomek.domek.repository;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tomek.domek.JpaH2SpringBootApplication;
import com.tomek.domek.entity.Passport;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaH2SpringBootApplication.class)
public class PassportRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PassportRepository repo;
	@Autowired
	EntityManager em;

	@Test
	public void testBiDirectionalRealtion() {
		Passport passport = repo.findById(40001L);
		
		log.info("student with passport {} is {}",passport.getNumber(),passport.getStudent().getName());
		
	}

}
