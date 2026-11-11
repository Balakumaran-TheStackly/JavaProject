package com.stackly.main;

import com.stackly.model.Course;
import com.stackly.model.Student;
import com.stackly.model.StudentStatus;
import com.stackly.service.StudentFileService;
import com.stackly.service.StudentService;

import java.util.ArrayList;

import com.stackly.exception.DuplicateStudentException;
import com.stackly.exception.InvalidStudentDataException;
import com.stackly.exception.StudentNotFoundException;

public class StudentServiceTest {

    public static void main(String[] args) {

        StudentService studentService = new StudentService();

        Course course = new Course(101, "Java Full Stack", 6);

        try {

            System.out.printf("%nADD STUDENTS%n");

            studentService.addStudent(
                    new Student(1001, "Kumar", "kumar@gmail.com",
                            85, 90, StudentStatus.ACTIVE, course));

            studentService.addStudent(
                    new Student(1002, "Priya", "priya@gmail.com",
                            92, 95, StudentStatus.ACTIVE, course));

            studentService.addStudent(
                    new Student(1003, "Arun", "arun@gmail.com",
                            75, 80, StudentStatus.ACTIVE, course));

            System.out.printf("%nTRY DUPLICATE STUDENT%n");

            studentService.addStudent(
                    new Student(1001, "Kumar", "kumar@gmail.com",
                            85, 90, StudentStatus.ACTIVE, course));

        } catch (DuplicateStudentException | InvalidStudentDataException e) {

            System.out.printf("Registration Error: %s%n", e.getMessage());
        }
        System.out.printf("%nTEST INVALID MARKS%n");

        try {

            studentService.addStudent(
                    new Student(1004, "Ravi", "ravi@gmail.com",
                            105, 85, StudentStatus.ACTIVE, course));

        } catch (InvalidStudentDataException | DuplicateStudentException e) {

            System.out.printf("Validation Error: %s%n", e.getMessage());
        }

        System.out.printf("%nTEST INVALID ATTENDANCE%n");

        try {

            studentService.addStudent(
                    new Student(1005, "Mani", "mani@gmail.com",
                            80, 110, StudentStatus.ACTIVE, course));

        } catch (InvalidStudentDataException | DuplicateStudentException e) {

            System.out.printf("Validation Error: %s%n", e.getMessage());
        }
        System.out.printf("%nDISPLAY ALL STUDENTS%n");

        studentService.displayAllStudents();
        
        System.out.printf("%nSEARCH STUDENT BY ID%n");

        try {

            Student student = studentService.searchStudentById(1002);

            System.out.printf("Student Found: %s | Email: %s | Marks: %.2f%n",
                    student.getName(),
                    student.getEmail(),
                    student.getMarks());

        } catch (StudentNotFoundException e) {

            System.out.printf("Search Error: %s%n", e.getMessage());
        }


        System.out.printf("%nSEARCH INVALID STUDENT ID%n");

        try {

            Student student = studentService.searchStudentById(999);

            System.out.printf("Student Found: %s%n", student.getName());

        } catch (StudentNotFoundException e) {

            System.out.printf("Search Error: %s%n", e.getMessage());
        }
        

		System.out.printf("%nSEARCH STUDENT BY NAME%n");
		
		try {
		
		    ArrayList<Student> result =
		            studentService.searchStudentByName("Kumar");
		
		    for (Student s : result) {
		
		        System.out.printf("Student Found: ID: %d | Name: %s | Email: %s%n",
		                s.getId(),
		                s.getName(),
		                s.getEmail());
		    }
		
		} catch (StudentNotFoundException e) {
		
		    System.out.printf("Search Error: %s%n", e.getMessage());
		}
		
		
		System.out.printf("%nSEARCH INVALID STUDENT NAME%n");
		
		try {
		
		    ArrayList<Student> result =
		            studentService.searchStudentByName("XYZ");
		
		    for (Student s : result) {
		
		        System.out.printf("Student Found: %s%n", s.getName());
		    }
		
		} catch (StudentNotFoundException e) {
		
		    System.out.printf("Search Error: %s%n", e.getMessage());
		}
		
		System.out.printf("%nUPDATE STUDENT MARKS%n");

		try {

		    studentService.updateStudentMarks(1001, 95);

		    Student student = studentService.searchStudentById(1001);

		    System.out.printf("Student Name : %s%n", student.getName());
		    System.out.printf("Updated Marks: %.2f%n", student.getMarks());

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}


		System.out.printf("%nUPDATE INVALID MARKS%n");

		try {

		    studentService.updateStudentMarks(1001, 110);

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}


		System.out.printf("%nUPDATE INVALID STUDENT ID%n");

		try {

		    studentService.updateStudentMarks(999, 80);

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}
		
		
		System.out.printf("%nUPDATE STUDENT ATTENDANCE%n");

		try {

		    studentService.updateStudentAttendance(1001, 98);

		    Student student = studentService.searchStudentById(1001);

		    System.out.printf("Student Name      : %s%n", student.getName());
		    System.out.printf("Updated Attendance: %d%%%n", student.getAttendance());

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}


		System.out.printf("%nUPDATE INVALID ATTENDANCE%n");

		try {

		    studentService.updateStudentAttendance(1001, 120);

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}


		System.out.printf("%nUPDATE INVALID STUDENT ID%n");

		try {

		    studentService.updateStudentAttendance(999, 85);

		} catch (StudentNotFoundException | InvalidStudentDataException e) {

		    System.out.printf("Update Error: %s%n", e.getMessage());
		}
		
		
		System.out.printf("%nCALCULATE STUDENT GRADE%n");

		try {

		    String grade = studentService.calculateGrade(1001);

		    Student student = studentService.searchStudentById(1001);

		    System.out.printf("Student Name : %s%n", student.getName());
		    System.out.printf("Student Marks: %.2f%n", student.getMarks());
		    System.out.printf("Student Grade: %s%n", grade);

		} catch (StudentNotFoundException e) {

		    System.out.printf("Grade Error: %s%n", e.getMessage());
		}


		System.out.printf("%nCALCULATE GRADE FOR INVALID STUDENT%n");

		try {

		    String grade = studentService.calculateGrade(999);

		    System.out.printf("Student Grade: %s%n", grade);

		} catch (StudentNotFoundException e) {

		    System.out.printf("Grade Error: %s%n", e.getMessage());
		}
		
		System.out.printf("%nREMOVE STUDENT%n");

		try {

		    studentService.removeStudent(1003);

		} catch (StudentNotFoundException e) {

		    System.out.printf("Remove Error: %s%n", e.getMessage());
		}


		System.out.printf("%nTRY REMOVE SAME STUDENT AGAIN%n");

		try {

		    studentService.removeStudent(1003);

		} catch (StudentNotFoundException e) {

		    System.out.printf("Remove Error: %s%n", e.getMessage());
		}


		System.out.printf("%nSTUDENTS AFTER REMOVAL%n");

		studentService.displayAllStudents();
		
		System.out.printf("%nTEST TOPPER WITH SAME MARKS%n");

		try {
		    studentService.updateStudentMarks(1002, 95);

		    ArrayList<Student> toppers = studentService.findStudentTopper();

		    for (Student s : toppers) {
		        System.out.printf(
		                "Student ID: %d | Name: %s | Marks: %.2f%n",
		                s.getId(),
		                s.getName(),
		                s.getMarks());
		    }

		} catch (StudentNotFoundException | InvalidStudentDataException e) {
		    System.out.printf("Error: %s%n", e.getMessage());
		}
		
		System.out.printf("%nGET ALL STUDENTS COUNT%n");

		ArrayList<Student> allStudents = studentService.getAllStudents();

		System.out.printf("Total Students: %d%n", allStudents.size());
		
		
		System.out.printf("%nSAVE STUDENT RECORDS%n");

		StudentFileService fileService = new StudentFileService();

		fileService.saveStudents(studentService);
		
		System.out.printf("%nLOAD STUDENT RECORDS%n");

		StudentService newStudentService = new StudentService();

		fileService.loadStudents(newStudentService);

		System.out.printf("%nDISPLAY LOADED STUDENTS%n");

		newStudentService.displayAllStudents();
    }
}