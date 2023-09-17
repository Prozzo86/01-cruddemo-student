package com.LearnSpring.cruddemo;

import com.LearnSpring.cruddemo.dao.StudentDAO;
import com.LearnSpring.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	// ovdje radim update aplikacije
	//                                         ovdje zapravo injektiram StudentDAO
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		//lambda expression
		return runner -> {
			// createStudent(studentDAO);
			
			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);
			
			// queryForStudents(studentDAO);

			// queryForStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all students");
		int numRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDAO) {
		// retrieve student based on id: primary key
		int studentId = 4;
		System.out.println("Getting student with id: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change first name
		System.out.println("Updating student...");
		myStudent.setFirstName("Petra");

		// update the student
		studentDAO.update(myStudent);

		// dispaly the updated student
		System.out.println("Updated student: " + myStudent);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("Peric");

		// display list of students
		for(Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display list of students
		for (Student tempStudent: theStudents){
			System.out.println(tempStudent);
		}
	}

	private void readStudent(StudentDAO studentDAO) {
		// create a student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Marko", "Maric", "m.maric@hotmail.com");

		// save the student
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// display the id of the saved student
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// retrieve student based on the id: primary key
		System.out.println("Retrieving student with id: " + theId);
		Student myStudent = studentDAO.findById(theId);

		// display student
		System.out.println("Found the student: " + myStudent);
	}

	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 student objects...");

		Student tempStudent1 = new Student("Pero", "Peric", "pero.peric@hotmail.com");
		Student tempStudent2 = new Student("Ivan", "Horvat", "ihorvat78@gmail.com");
		Student tempStudent3 = new Student("Ivana", "Orsic", "ivanicaaa@hotmail.com");

		// save the student objects
		System.out.println("Saving the students...");

		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}

	private void createStudent(StudentDAO studentDAO) {

		// kreiram student objekt
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Darijan", "Bogdanovic", "bogdanovicdarijan@hotmail.com");

		// spremam student objekt
		System.out.println("Saving new student...");
		studentDAO.save(tempStudent);

		// ispisujem id spremljenog studenta
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}
}
