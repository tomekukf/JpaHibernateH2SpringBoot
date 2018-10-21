package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tomek.domek.entity.Course;
import com.tomek.domek.entity.Passport;
import com.tomek.domek.entity.Review;
import com.tomek.domek.entity.ReviewRating;
import com.tomek.domek.entity.Student;

@Repository
@Transactional
// if we need to manipulate on data we need to add @Transacional annoation, for
// findById we dont need it beacuse we are not manipulting data
public class CourseRepository {
	@Autowired
	EntityManager em;

	Logger log = LoggerFactory.getLogger(CourseRepository.class);

	public Course findById(Long id) {
		return em.find(Course.class, id);

	}

	public List<Course> findAll() {
		// TypedQuery<Person> query = em.createNamedQuery("find_all", Person.class);
		TypedQuery<Course> query = em.createNamedQuery("findAll", Course.class);
		return query.getResultList();

		// reateQuery("select * from course", new
		// BeanPropertyRowMapper<Course>(Course.class));
	}

	// difference between persit and megre is that when you want to add new things
	// to database you re usuing persist but when you want to update sth in database
	// you're using merge
	public Course saveOrUpdateCourse(Course course) {
		if (course.getId() == null) {
			em.persist(course);
		} else {
			em.merge(course);
		}

		return course;
	}

	public void delteById(Long id) {
		Course course = findById(id);
		em.remove(course);
	}

	public void playWithEntityManager() {
		log.info("Starting play.............................");

		Course course = new Course("WebServices");
		em.persist(course);
		em.flush();
		// em.detach(course);
		course.setName("WebServices - Updated");
		em.refresh(course);
		Course course1 = new Course("Angular JS");
		em.persist(course1);
		em.flush();

		em.detach(course1);
		// em.detach(course1);
		course1.setName("Angular JS - Updated");

		Course course3 = new Course("HTML in 50 steps");
		em.persist(course3);
		em.flush();
		course3.setName("HTML in 50 steps - Updated");

		// em.detach(course1) is stopimg tracking changes on single objects ( course1)
		// so in this example in database will be just Angular Js withous Updated
		// annotation
		// em.clear();
		// em.clear enabling us to stop tracking all chagess, to kill all process
		// concted with EntityManager
		// em.flush is pushing changes to database
		// before be can use detach or clear we need to push changes to database with
		// flush ??
	}

	public void addReviewsForCourseHardCOded() {
		//fidding the course
		Course course = findById(10001L);
		//adding to reviews
		Review review1= new Review(ReviewRating.FIVE, "some mistakess, but awesome");
		Review review2= new Review(ReviewRating.THREE, "Really usefull stuff ! ");
		//setting the relatioships
		course.addReviews(review1);
		review1.setCourse(course);
		course.getReviews().add(review2);
		review2.setCourse(course);
		//saving to database
		em.persist(review1);
		em.persist(review2);
	}
	public void addReviewForCourseGeneric(Long courseId,List<Review> reviews) {
		Course course = findById(courseId);
		log.info("course.getReviews() --> {}",course.getReviews());
		for (Review review : reviews) {
			course.addReviews(review);
			review.setCourse(course);
			em.persist(review);
			
		}
	}

	public void addStudentTOCourse() {
		Passport ps1 = new Passport("90283");
		em.persist(ps1);
		Student st = new Student("lonia");
		Course course = em.find(Course.class, 10002L);
		course.addStudents(st);
		st.addCourse(course);
		st.setPassport(ps1);
		
		em.persist(st);
		
	}

	public void printStudent() {
		Course course = em.find(Course.class, 10003L);
		log.info("***************************** {}",course);
		List<Student> student =  em.find(Course.class, 10001L).getStudents();
		log.info("***************************** {}",student);

		
	}

}
