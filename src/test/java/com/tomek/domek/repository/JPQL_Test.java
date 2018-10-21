package com.tomek.domek.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tomek.domek.JpaH2SpringBootApplication;
import com.tomek.domek.entity.Course;
import com.tomek.domek.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaH2SpringBootApplication.class)
public class JPQL_Test {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepository repo;
	@Autowired
	EntityManager em;

	@Test
	public void jqpl_basic() {

		List resultList = em.createQuery("Select c from Course c ").getResultList();

		log.info("Select c from Course c ->{}", resultList);

	}
	@Test
	public void jqpl_typed() {

		TypedQuery<Course> query = em.createQuery("Select c from Course c ",Course.class);
		List<Course> resultList = query.getResultList();

		log.info("Select c from Course c  ->{}", resultList);

	}
	@Test
	public void jqpl_where() {

		TypedQuery<Course> query = em.createQuery("Select c from Course c where name like'%_Boot%' ",Course.class);
		List<Course> resultList = query.getResultList();

		log.info("Select c from Course c where name like'%_Boot%' ->{}", resultList);

	}
	
	@Test
	public void jqpl_CoursesWithoutStudents() {

		TypedQuery<Course> query = em.createQuery("Select c from Course  c where c.students is empty",Course.class);
		List<Course> resultList = query.getResultList();

		log.info("Select c from Course  c where c.students is empty ->{}", resultList);

	}
	@Test
	public void jqpl_CoursesWithoutReviews() {

		TypedQuery<Course> query = em.createQuery("Select c from Course  c where c.reviews is empty",Course.class);
		List<Course> resultList = query.getResultList();

		log.info("Select c from Course  c where c.students is empty ->{}", resultList);

	}
	@Test
	public void jqpl_CoursesWith_At_Least_2_Students() {

		TypedQuery<Course> query = em.createQuery("Select c from Course  c where size(c.students) >= 2",Course.class);
		List<Course> resultList = query.getResultList();

		log.info("Select c from Course  c where c.students >= 2 ->{}", resultList);

	}
	@Test
	public void jqpl_Select_Student_from_courses_student_name_contatin_pattern() {

		Query query = em.createQuery("Select c, s from Course c Left JOIN c.students s 	where s.name like '%a%'");
		List<Object[]> resultList = query.getResultList();;
//		where s.name like '%om%
		log.info("Result size ->{}", resultList.size());
		
		for (Object[] result : resultList) {
			log.info("Course {} and Student {}",result[0],result[1]);
			
		}
	}
	@Test
	public void jqpl_StudentWithPassoprtWithSpecificPattern() {

		TypedQuery<Student> query = em.createQuery("Select s from Student s where  s.passport.number  like '%1%'",Student.class);
//													Select c,s from Course c Join c.students s where s.name like '%tom%'
		List<Student> resultList = query.getResultList();

		log.info("Select c from Course c.students where name like’%tome% ->{}", resultList);
					//not working
	}
	@Test
	public void jqpl_course_ordered_by_number_of_students() {

		TypedQuery<Course> query = em.createQuery("Select c from Course  c order by size(c.students)",Course.class);
		List<Course> resultList = query.getResultList();
int size = resultList.size();
		log.info("Select c from Course  c where c.students >= 2 ->{}", size);

	}

	
	
}