package com.tomek.domek.repository;

import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.tomek.domek.JpaH2SpringBootApplication;
import com.tomek.domek.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JpaH2SpringBootApplication.class)
public class StudentRepositorySpringDataTest {

	Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CourseRepositorySPringData repo;
	@Autowired
	EntityManager em;

	@Test
	public void findById() {
		// it wont return course object back, it will return an optional when it wont
		// find any objects
		Optional<Course> course = repo.findById(10001L);
		assertTrue(course.isPresent());

	}

	@Test
	public void SaveMethodTest() {

		Course course = new Course("Lambda course");
		repo.save(course);
		course.setName("Labda courses- updated ");
		repo.save(course);

	}

	@Test
	public void findAllAndSortResults() {

		Sort sort = new Sort(Sort.Direction.DESC, "name");
		List<Course> var = repo.findAll(sort);
		log.info("Srted reults {}", var);
	}
	
	@Test
	public void paginationTest() {

		
		PageRequest pageReguest = PageRequest.of(0, 3);
		
		Page<Course> results = repo.findAll(pageReguest);
		Sort sort = new Sort(Sort.Direction.DESC, "name");
		log.info("First page  without sorting ->>{}",results.getContent());
		List<Course> list = results.stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
		
		log.info("First page ->>{}",list);
		
		Pageable seondPage = results.nextPageable();
		
		Page<Course> result2 = repo.findAll(seondPage);
		log.info("First page ->>{}",result2.getContent());

	}

}
