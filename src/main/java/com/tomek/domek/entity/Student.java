package com.tomek.domek.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PreRemove;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@SQLDelete(sql="Update Student set is_deleted = true where id=?")
@Where(clause="is_deleted=false")
public class Student  {
	
	// we need to make this method static to avoid error asociated with entites mapping for tables . In this way this method will be exectudem during compile time 
	
	private static Logger log =LoggerFactory.getLogger(Student.class);
	
	
	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String name;
	// 
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Passport_Id")
	private Passport passport;
	
	
	@ManyToMany(mappedBy="students")
//	@JsonIgnore
	private List<Course> courses = new ArrayList<>();

	@OneToMany(mappedBy="student")
	private List<Review> reviews;
	
	
	private boolean isDeleted = false;
	
	@PreRemove
	private void setDeleted() {
		this.isDeleted = true ;
	}
	
	@Embedded
	private Address address;
	
	
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	
	public List<Course> getCourses() {
		return courses;
	}
	public void addCourse(Course course) {
		this.courses.add(course);
		
	}
	public void removeCourse(Course course) {
		this.courses.remove(course);
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Student() {
	}

	public Student(String name) {
		this.name = name;
	}

	public Passport getPassport() {
		return passport;
	}

	public void setPassport(Passport passport) {
		this.passport = passport;
	}

	
	
	
	@Override
	public String toString() {
		return String.format("Student[%s]", name);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((courses == null) ? 0 : courses.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((passport == null) ? 0 : passport.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (courses == null) {
			if (other.courses != null)
				return false;
		} else if (!courses.equals(other.courses))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (passport == null) {
			if (other.passport != null)
				return false;
		} else if (!passport.equals(other.passport))
			return false;
		return true;
	}
	
	



	

}
