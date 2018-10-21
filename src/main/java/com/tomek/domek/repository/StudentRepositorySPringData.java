package com.tomek.domek.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tomek.domek.entity.Student;

@RepositoryRestResource(path="students")
// if we need to manipulate on data we need to add @Transacional annoation, for
// findById we dont need it beacuse we are not manipulting data
public interface StudentRepositorySPringData extends JpaRepository<Student, Long> {

	
	List<Student> findByName(String name);
	List<Student> findByNameOrderByIdDesc(String name );
	
	@Query("Select s from Student s where s.name =:name")
	List<Student> findStudentWithNameLike(@Param("name")String name);
	 


}
