package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tomek.domek.entity.Address;
import com.tomek.domek.entity.Course;
import com.tomek.domek.entity.Passport;
import com.tomek.domek.entity.Review;
import com.tomek.domek.entity.ReviewRating;
import com.tomek.domek.entity.Student;

@Repository
@Transactional
// if we need to manipulate on data we need to add @Transacional annoation, for
// findById we dont need it beacuse we are not manipulting data
public class StudentRepository {
	@Autowired
	EntityManager em;

	Logger log = LoggerFactory.getLogger(StudentRepository.class);

	public Student findById(Long id) {
		return em.find(Student.class, id);

	}

	public List<Student> findAll() {
		// TypedQuery<Person> query = em.createNamedQuery("find_all", Person.class);
		TypedQuery<Student> query = em.createNamedQuery("findAll", Student.class);
		return query.getResultList();

		// reateQuery("select * from student", new
		// BeanPropertyRowMapper<Student>(Student.class));
	}

	// difference between persit and megre is that when you want to add new things
	// to database you re usuing persist but when you want to update sth in database
	// you're using merge
	public Student saveOrUpdateStudent(Student student) {
		if (student.getId() == null) {
			em.persist(student);
		} else {
			em.merge(student);
		}

		return student;
	}

	public void delteById(Long id) {
		Student student = findById(id);
		em.remove(student);
	}

	public void saveStudentWithPassport() {
		Passport passport = new Passport("54123");
		em.persist(passport);
		Student student = new Student("Milena");
		student.setPassport(passport);

		em.persist(student);

	}
	public void saveAddressforStudent(Address address) {
		Student student = findById(20001L);
		student.setAddress(address);
		em.flush();
//		em.persist(passport);
//		student.setPassport(passport);

		em.persist(student);

	}
	public void operation() {
		// Operation 1 - retrive student
		Student student = em.find(Student.class, 20001L);
		// Operation 2 - retrive students passport
		Passport passport = student.getPassport();
		// Operation 3 -Update Passport
		passport.setNumber("67584");
		// Operation 4 - Update Student
		student.setName("Tomek - Updated");

	}

	public void printCOurses() {
		Student student= findById(20005L);
	log.info("===================={}",student);
	log.info("===================={}",student.getCourses());
	}

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
		
		Review newRev = new Review(ReviewRating.FOUR,"Really good programming skills but you need buy new microphone");
		em.persist(newRev);
		
		newRev.setStudents(student);	
		newRev.setCourse(course);
	}

}
