package com.tomek.domek.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@NamedQuery(name = "findAll", query = "Select c from Course c")
@Cacheable
public class Course  {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@UpdateTimestamp
	private LocalDateTime updatedTime;

	@CreationTimestamp
	private LocalDateTime createdTime;

	@OneToMany(mappedBy = "course")
	@JsonIgnore
	private List<Review> reviews = new ArrayList<>();

	/*
	 * @JoinTable(name = "USER_ROLES", joinColumns = {
	 * 
	 * @JoinColumn(name = "USER_EMAIL", referencedColumnName = "email") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "ROLE_NAME", referencedColumnName = "role") })
	 */

	@ManyToMany(fetch = FetchType.EAGER)

	@JoinTable(name = "STUDENT_COURSE", joinColumns = { @JoinColumn(name = "COURSE_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "STUDENT_ID") })
//	@JsonIgnore
	private List<Student> students = new ArrayList<>();

	public void addStudents(Student student) {
		this.students.add(student);
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void addReviews(Review reviews) {
		this.reviews.add(reviews);
	}

	public void removeReviews(Review reviews) {
		this.reviews.remove(reviews);
	}

	public Course() {
	}

	public Course(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return String.format("Course[%s]", name);
	}

}
