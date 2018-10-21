package com.tomek.domek.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Cacheable
public class Review {
	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	private ReviewRating rate;

	private String description;

	@ManyToOne
	private Course course;
	
	@ManyToOne
	private Student student;

		
	
	public Student getStudents() {
		return student;
	}

	public void setStudents(Student students) {
		this.student = students;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	

	public ReviewRating getRate() {
		return rate;
	}

	public void setRate(ReviewRating rate) {
		this.rate = rate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Reviews degree [%s]", rate);
	}
	
	public Review(ReviewRating rate, String description) {
		this.rate = rate;
		this.description = description;
	}

	public Review() {
	}

}
