package com.tomek.domek.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import javax.persistence.EntityManager;
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
public class CourseRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repo;
	@Autowired
	EntityManager em;

	@Test
	public void testCourseName() {

		Course course = repo.findById(10001L);
		log.info("Course name is {}", course.getName());
		assertEquals("JpaIn50Steps", course.getName());

	}

	@Test
	public void testSaveCourse() {

		Course course = repo.findById(1L);
		log.info("Course name is {}", course.getName());
		assertEquals("Spring_Boot_Course2", course.getName());

	}

	@Test
	@DirtiesContext
	public void testDelteCourse() {

		repo.delteById(10001L);
		assertNull(repo.findById(1000L));
	}

	/*
	 * public Course saveOrUpdateCourse(Course course) { if(course.getId() == null)
	 * { em.persist(course); } else { em.merge(course); } return course; }
	 */

	@Test
	// @DirtiesContext
	public void testSaveOrUpateMethod() {

		Course course = repo.findById(10001L);
		assertEquals("JpaIn50Steps", course.getName());

		course.setName("JpaIn50Steps - Updated");
		repo.saveOrUpdateCourse(course);

		Course course1 = repo.findById(10001L);
		assertEquals("JpaIn50Steps - Updated", course1.getName());

	}

	@Test
	public void testfindAllMethod() {

		List<Course> list = repo.findAll();
		assertEquals(3l, list.size());
	}

	@Test
	public void testPLatWithEntityManager() {
		repo.playWithEntityManager(); 
	}
	
	
	@Test
	@Transactional
	public void addStudentToCourseTest() {
		repo.addStudentTOCourse();
		Student tomek = em.find(Student.class	, 20001L);
		log.info("student data is ---> {}",tomek);
		log.info("student courses are  ---> {}",tomek.getCourses());
		
		
		
	}
}
