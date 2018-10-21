package com.tomek.domek;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.tomek.domek.entity.Address;
import com.tomek.domek.repository.CourseRepository;
import com.tomek.domek.repository.EmployeeRepository;
import com.tomek.domek.repository.StudentRepository;

@SpringBootApplication
public class JpaH2SpringBootApplication implements CommandLineRunner {

	Logger log = LoggerFactory.getLogger(CourseRepository.class);

	@Autowired
	CourseRepository courseRepo;
	@Autowired
	StudentRepository studentRepo;
	@Autowired
	EmployeeRepository employeeRepo;
	@Autowired
	EntityManager em;


	public static void main(String[] args) {
		SpringApplication.run(JpaH2SpringBootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
//		studentRepo.delteById(20005L);
		
		courseRepo.addReviewsForCourseHardCOded();
		
		studentRepo.saveAddressforStudent(new Address("Mjaewskiego","11","Lukow"));
		studentRepo.delteById(20002L);
//		List<Review> listOfReviews = new ArrayList<>();
//		listOfReviews.add(new Review("3"," need some improvments"));
//		listOfReviews.add(new Review("2"," bad course ;("));
//		Course course = courseRepo.findById(10002L);
//		Long id = course.getId();
//		courseRepo.addReviewForCourseGeneric(id, listOfReviews);
		
//		studentRepo.addStudentWithReview();
		
		
		
//	employeeRepo.insertEmployee(new FullTimeEmployee("Marlena", new BigDecimal("10000")) );
//	employeeRepo.insertEmployee(new PartTimeEmployee("Tomek", new BigDecimal("50")) );
//	
//	log.info("Employed employees --->{}",employeeRepo.retriveAllEmployess());
//	log.info("Employed   PartTime employees --->{}",employeeRepo.retriveAllPartTimeEmployess());
//	log.info("Employed   FullTime employees --->{}",employeeRepo.retriveAllFullTimeEmployess());
	
	
	
	
	
		
//		courseRepo.addStudentTOCourse();
//	studentRepo.printCOurses();
//	courseRepo.printStudent();
	
		
//		Student tomek = em.find(Student.class	, 20005L);
//		log.info("student data is ---> {}",tomek);
//		log.info("student courses are  ---> {}",tomek.getCourses());
		
//		repo.saveStudentWithPassport();
	
//		Course course = courseRepo.findById(10001L);
//		log.info("Returning coursee with id 1001 {}", course);
//
//		courseRepo.saveOrUpdateCourse(new Course("Spring_Boot_Course2"));
//		
//		courseRepo.delteById(10002L);
//		courseRepo.findAll();
//		courseRepo.playWithEntityManager();

	}
}
