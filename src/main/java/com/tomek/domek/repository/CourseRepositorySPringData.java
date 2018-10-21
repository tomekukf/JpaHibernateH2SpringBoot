package com.tomek.domek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tomek.domek.entity.Course;

@RepositoryRestResource(path="courses")
// if we need to manipulate on data we need to add @Transacional annoation, for
// findById we dont need it beacuse we are not manipulting data
public interface CourseRepositorySPringData extends JpaRepository<Course, Long> {

	
	List<Course> findByName(String name);
	List<Course> findByNameOrderByIdDesc(String name );
	
	@Query("Select c from Course c where c.name =:name")
	List<Course> findCourseWithNameLike(@Param("name")String name);
	 


}
