package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tomek.domek.JpaH2SpringBootApplication;
import com.tomek.domek.entity.Address;
import com.tomek.domek.entity.Course;
import com.tomek.domek.entity.Passport;
import com.tomek.domek.entity.Review;
import com.tomek.domek.entity.ReviewRating;
import com.tomek.domek.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaH2SpringBootApplication.class)
public class StudentRepositoryTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repo;
	@Autowired
	EntityManager em;

	@Test
	@Transactional
	public void retriveStudentAndPassport() {
		Student student = repo.findById(20001L);
		log.info("Student is {}", student);
		log.info("Studdent passport is -> {}", student.getPassport());

	}

	@Test
	@Transactional
	public void retriveStudentAndAddAddress() {
		Student student = em.find(Student.class, 20001L);
		student.setAddress(new Address("majewskiego", "11", "Lukow"));
		em.flush();
		log.info("Student is {}", student.getAddress());
		log.info("Studdent passport is -> {}", student.getPassport());

	}

	@Test
	public void updateStudentAndPasspotTest() {
		repo.operation();
	}

	@Test
	@Transactional
	// @DirtiesContext -- to have same data after operation
	public void testSingleTransaction() {
		// Operation 1 - retrive student
		Student student = em.find(Student.class, 20001L);
		// Operation 2 - retrive students passport
		Passport passport = student.getPassport();
		// Operation 3 -Update Passport
		passport.setNumber("67584");
		// Operation 4 - Update Student
		student.setName("Tomek - Updated");
		em.flush();
	}

	@Test
	@Transactional
	public void retriveStudentAndHisCoursesTest() {

		Student marlena = em.find(Student.class, 20002L);
		log.info("Print student details {}", marlena);
		List<Course> list = marlena.getCourses();
		log.info("Print student courses {}", list);
	}

	@Test
	@Transactional
	public void addStudentWithReview() {

		Passport passport = new Passport("14793");
		em.persist(passport);

		Student student = new Student("Maja");
		em.persist(student);

		student.setPassport(passport);

		Course course = em.find(Course.class, 10002L);
		em.persist(course);

		student.addCourse(course);
		course.addStudents(student);

		Review newRev = new Review(ReviewRating.ONE, "Really good programming skills but you need buy new microphone");
		em.persist(newRev);

		newRev.setStudents(student);

	}

}
