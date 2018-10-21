package com.tomek.domek.repository;

import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.tomek.domek.JpaH2SpringBootApplication;
import com.tomek.domek.entity.Course;
import com.tomek.domek.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaH2SpringBootApplication.class)
public class NativeQueries_test {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	EntityManager em;

	@Test
	public void nativeQueriesTest() {

		Query query = em.createNativeQuery("Select * from course", Course.class);

		List resultList = query.getResultList();

		log.info("Select * from courses -> {}", resultList);
	}

	@Test
	@DirtiesContext
	@Transactional
	public void nativeQueriesDleteByIdTest() {

		// Query query = em.createQuery("delete from Student where id=20005");

		studentRepo.delteById(20005L);
		// int resultList = query.getResultList().size();
		assertNull(studentRepo.findById(20005L));
		log.info("Select * from courses -> {}");
	}

	@Test
	public void nativeQueriesWithParametersTest() {

		Query query = em.createNativeQuery("Select * from course where where id=?", Course.class);
		query.setParameter(1, 10001L);

		List resultList = query.getResultList();

		log.info("Select * from courses -> {}", resultList);
	}
	@Test
	public void nativeQueriesSelectAllStudent() {

		Query query = em.createNativeQuery("Select * from Student where is_deleted=0 and  id=?", Student.class);
		query.setParameter(1, 20004L);

		List resultList = query.getResultList();

		log.info("Select * from courses -> {}", resultList);
	}
	
	@Test
	public void nativeQueriesWithNAMEDParametersTest() {

		Query query = em.createNativeQuery("Select * from course where name like :name", Course.class);
		query.setParameter("name", "%_Boot%");

		List<Course> resultList = query.getResultList();

		log.info("Select * from courses -> {}", resultList);
	}

	@Test
	@Transactional
	public void nativeQueriesUpdateMassRowsTest() {

		Query query = em.createNativeQuery("Update COURSE set updated_time=sysdate()");

		int numOfRowsUpdated = query.executeUpdate();

		log.info("Number of Updated Rows is  {}", numOfRowsUpdated);
	}

}
