package com.stackly.main;

import java.util.ArrayList;
import java.util.Scanner;

import com.stackly.exception.DuplicateStudentException;
import com.stackly.exception.InvalidStudentDataException;
import com.stackly.exception.StudentNotFoundException;
import com.stackly.model.Course;
import com.stackly.model.Student;
import com.stackly.model.StudentStatus;
import com.stackly.service.CourseService;
import com.stackly.service.StudentFileService;
import com.stackly.service.StudentService;

public class Main {
	static Scanner sc= new Scanner(System.in);
	static boolean running=true;
	 public static void main(String[] args) {
		 

	        System.out.printf("==================================================%n");
	        System.out.printf("          STUDENT MANAGEMENT SYSTEM%n");
	        System.out.printf("==================================================%n");
	        
	        CourseService courseService=new CourseService();
	        StudentService studentService=new StudentService();
	        StudentFileService fileService=new StudentFileService();
	        
	        System.out.printf("%nLoading student records...%n");

	        fileService.loadStudents(studentService);

	        System.out.printf("Application started successfully!!!!%n");
	        
	        Course course1 = new Course(101, "Java Full Stack", 6);
	        Course course2 = new Course(102, "Python Development", 4);
	        Course course3 = new Course(103, "Web Development", 3);
	        
	        courseService.addCourse(course1);
	        courseService.addCourse(course2);
	        courseService.addCourse(course3);
	        
	        while (running) {

	            displayMenu();

	            if (!sc.hasNextInt()) {
	                System.out.printf("%nInvalid input! Please enter a number.%n");
	                sc.nextLine();
	                continue;
	            }

	            int choice = sc.nextInt();

	            switch (choice) {
		            case 1:
		                System.out.printf("%nADD NEW COURSE%n");
	
		                System.out.printf("Enter Course ID: ");
		                int courseId = sc.nextInt();
		                sc.nextLine();
	
		                System.out.printf("Enter Course Name: ");
		                String courseName = sc.nextLine();
	
		                System.out.printf("Enter Course Duration (Months): ");
		                int duration = sc.nextInt();
	
		                Course course = new Course(courseId, courseName, duration);
	
		                courseService.addCourse(course);
		                break;
	
		            case 2:
		                System.out.printf("%nDISPLAY ALL COURSES%n");
	
		                courseService.displayAllCourses();
		                break;

		            case 3:
		                System.out.printf("%n========== REGISTER STUDENT ==========%n");

		                System.out.printf("Enter Student ID: ");
		                int studentId = sc.nextInt();
		                sc.nextLine();
		                System.out.printf("Enter Student Name: ");
		                String studentName = sc.nextLine();
		                System.out.printf("Enter Student Email: ");
		                String email = sc.nextLine();
		                System.out.printf("Enter Student Marks: ");
		                double marks = sc.nextDouble();
		                System.out.printf("Enter Student Attendance: ");
		                int attendance = sc.nextInt();
		                System.out.printf("%nAVAILABLE COURSES%n");
		                courseService.displayAllCourses();
		                System.out.printf("Select Course ID: ");
		                int selectedCourseId = sc.nextInt();

		                Course selectedCourse = courseService.searchCourseById(selectedCourseId);

		                if (selectedCourse == null) {
		                    System.out.printf("Registration Error: Invalid Course ID!%n");
		                    break;
		                }

		                Student student = new Student(studentId,studentName,email,marks,attendance,StudentStatus.ACTIVE,selectedCourse);

		                try {
		                    studentService.addStudent(student);

		                } catch (DuplicateStudentException |InvalidStudentDataException e) {
		                    System.out.printf("Registration Error: %s%n",
		                            e.getMessage());
		                }
		                break;
		                
		                
		            case 4:
		                System.out.printf("%nDISPLAY ALL STUDENTS%n");

		                studentService.displayAllStudents();

		                break;
		                
		            case 5:
		                System.out.printf("%nSEARCH STUDENT BY ID%n");

		                System.out.printf("Enter Student ID: ");
		                int searchId = sc.nextInt();

		                try {
		                    Student foundStudent =
		                            studentService.searchStudentById(searchId);

		                    System.out.printf("%nSTUDENT DETAILS%n");

		                    System.out.printf("Student ID   : %d%n", foundStudent.getId());
		                    System.out.printf("Student Name : %s%n", foundStudent.getName());
		                    System.out.printf("Email        : %s%n", foundStudent.getEmail());
		                    System.out.printf("Marks        : %.2f%n", foundStudent.getMarks());
		                    System.out.printf("Attendance   : %d%%%n", foundStudent.getAttendance());
		                    System.out.printf("Status       : %s%n", foundStudent.getStatus());
		                    System.out.printf("Course       : %s%n",
		                            foundStudent.getCourse().getCourseName());

		                } catch (StudentNotFoundException e) {

		                    System.out.printf("Search Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 6:
		                System.out.printf("%nSEARCH STUDENT BY NAME%n");

		                sc.nextLine();

		                System.out.printf("Enter Student Name: ");
		                String searchName = sc.nextLine();

		                try {
		                    ArrayList<Student> foundStudents =studentService.searchStudentByName(searchName);

		                    System.out.printf("%nSEARCH RESULTS%n");

		                    for (Student s : foundStudents) {
		                        System.out.printf("%nStudent ID   : %d%n", s.getId());
		                        System.out.printf("Student Name : %s%n", s.getName());
		                        System.out.printf("Email        : %s%n", s.getEmail());
		                        System.out.printf("Marks        : %.2f%n", s.getMarks());
		                        System.out.printf("Attendance   : %d%%%n", s.getAttendance());
		                        System.out.printf("Status       : %s%n", s.getStatus());
		                        System.out.printf("Course       : %s%n",
		                                s.getCourse().getCourseName());
		                    }

		                } catch (StudentNotFoundException e) {
		                    System.out.printf("Search Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 7:
		                System.out.printf("%n========== UPDATE STUDENT MARKS ==========%n");

		                System.out.printf("Enter Student ID: ");
		                int updateId = sc.nextInt();

		                System.out.printf("Enter New Marks: ");
		                double newMarks = sc.nextDouble();

		                try {
		                    studentService.updateStudentMarks(updateId, newMarks);

		                    

		                } catch (StudentNotFoundException |
		                         InvalidStudentDataException e) {

		                    System.out.printf("%nUpdate Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 8:
		                System.out.printf("%n========== UPDATE STUDENT ATTENDANCE ==========%n");

		                System.out.printf("Enter Student ID: ");
		                int attendanceId = sc.nextInt();

		                System.out.printf("Enter New Attendance: ");
		                int newAttendance = sc.nextInt();

		                try {
		                    studentService.updateStudentAttendance(
		                            attendanceId, newAttendance);

		                } catch (StudentNotFoundException |
		                         InvalidStudentDataException e) {

		                    System.out.printf("%nUpdate Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 9:
		                System.out.printf("%n========== CALCULATE STUDENT GRADE ==========%n");

		                System.out.printf("Enter Student ID: ");
		                int gradeId = sc.nextInt();

		                try {
		                    String grade = studentService.calculateGrade(gradeId);

		                    Student student1 = studentService.searchStudentById(gradeId);

		                    System.out.printf("%nSTUDENT GRADE DETAILS%n");
		                    System.out.printf("Student ID   : %d%n", student1.getId());
		                    System.out.printf("Student Name : %s%n", student1.getName());
		                    System.out.printf("Marks        : %.2f%n", student1.getMarks());
		                    System.out.printf("Grade        : %s%n", grade);

		                } catch (StudentNotFoundException e) {
		                    System.out.printf("Grade Calculation Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 10:
		                System.out.printf("%n========== FIND STUDENT TOPPER ==========%n");

		                try {
		                    ArrayList<Student> toppers =
		                            studentService.findStudentTopper();

		                    System.out.printf("%nTOPPER DETAILS%n");

		                    for (Student topper : toppers) {

		                        System.out.printf("%nStudent ID   : %d%n", topper.getId());

		                        System.out.printf("Student Name : %s%n", topper.getName());

		                        System.out.printf("Marks        : %.2f%n", topper.getMarks());

		                        System.out.printf("Course       : %s%n",
		                                topper.getCourse().getCourseName());
		                    }

		                } catch (StudentNotFoundException e) {

		                    System.out.printf("%nTopper Search Error: %s%n",
		                            e.getMessage());
		                }

		                break;
		                
		            case 11:
		                System.out.printf("%n========== REMOVE STUDENT ==========%n");

		                System.out.printf("Enter Student ID: ");
		                int removeId = sc.nextInt();

		                try {
		                    studentService.removeStudent(removeId);

		                } catch (StudentNotFoundException e) {

		                    System.out.printf("%nRemoval Error: %s%n", e.getMessage());
		                }

		                break;
		                
		            case 12:
		                System.out.printf("%n========== SAVE STUDENT RECORDS ==========%n");

		                fileService.saveStudents(studentService);

		                break;
		                
		            case 13:
		                System.out.printf("%nSaving student records before exit...%n");

		                fileService.saveStudents(studentService);

		                System.out.printf("%nThank you for using Student Management System!!!!%n");

		                running = false;
		                break;

	                default:
	                    System.out.printf("%nInvalid choice or functionality not implemented yet!%n");
	                    break;
	            }
	        }

	        sc.close();
	        
//	        System.out.printf("%nAVAILABLE COURSES%n");
//
//	        courseService.displayAllCourses();
	    }
	 
	 public static void displayMenu() {

		    System.out.printf("%n==================================================%n");
		    System.out.printf("          STUDENT MANAGEMENT SYSTEM%n");
		    System.out.printf("==================================================%n");

		    System.out.printf("1.  Add Course%n");
		    System.out.printf("2.  Display All Courses%n");
		    System.out.printf("3.  Register Student%n");
		    System.out.printf("4.  Display All Students%n");
		    System.out.printf("5.  Search Student By ID%n");
		    System.out.printf("6.  Search Student By Name%n");
		    System.out.printf("7.  Update Student Marks%n");
		    System.out.printf("8.  Update Student Attendance%n");
		    System.out.printf("9.  Calculate Student Grade%n");
		    System.out.printf("10. Find Student Topper%n");
		    System.out.printf("11. Remove Student%n");
		    System.out.printf("12. Save Student Records%n");
		    System.out.printf("13. Exit%n");

		    System.out.printf("==================================================%n");
		    System.out.printf("Enter your choice: ");
		}

}
