package com.stackly.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.stackly.exception.DuplicateStudentException;
import com.stackly.exception.InvalidStudentDataException;
import com.stackly.model.Course;
import com.stackly.model.Student;
import com.stackly.model.StudentStatus;

public class StudentFileService {

	 private final String fileName = "students.txt";
	 
	 public void saveStudents(StudentService studentService) {

		    ArrayList<Student> students = studentService.getAllStudents();

		    try (BufferedWriter writer =new BufferedWriter(new FileWriter(fileName))) {

		        for (Student s : students) {

		            writer.write(
		                    s.getId() + "," +
		                    s.getName() + "," +
		                    s.getEmail() + "," +
		                    s.getMarks() + "," +
		                    s.getAttendance() + "," +
		                    s.getStatus() + "," +
		                    s.getCourse().getCourseId() + "," +
		                    s.getCourse().getCourseName() + "," +
		                    s.getCourse().getDuration()
		            );

		            writer.newLine();
		        }

		        System.out.printf("Student records saved successfully!!!!%n");

		    } catch (IOException e) {

		        System.out.printf("File Save Error: %s%n", e.getMessage());
		    }
		}
	 
	 public void loadStudents(StudentService studentService) {

		    File file = new File(fileName);

		    if (!file.exists()) {
		        System.out.printf("Student data file not found!%n");
		        return;
		    }

		    try (BufferedReader reader =
		            new BufferedReader(new FileReader(fileName))) {

		        String line;

		        while ((line = reader.readLine()) != null) {

		            String[] data = line.split(",");

		            int id = Integer.parseInt(data[0]);
		            String name = data[1];
		            String email = data[2];
		            double marks = Double.parseDouble(data[3]);
		            int attendance = Integer.parseInt(data[4]);
		            StudentStatus status = StudentStatus.valueOf(data[5]);

		            int courseId = Integer.parseInt(data[6]);
		            String courseName = data[7];
		            int duration = Integer.parseInt(data[8]);

		            Course course = new Course(
		                    courseId, courseName, duration);

		            Student student = new Student(
		                    id, name, email, marks, attendance, status, course);

		            try {
		                studentService.addStudent(student);
		            } catch (DuplicateStudentException |
		                     InvalidStudentDataException e) {

		                System.out.printf("Student Load Error: %s%n",
		                        e.getMessage());
		            }
		        }

		        System.out.printf("Student records loaded successfully!!!!%n");

		    } catch (IOException e) {

		        System.out.printf("File Load Error: %s%n", e.getMessage());

		    } catch ( IllegalArgumentException e) {

		        System.out.printf("Invalid student data in file: %s%n",
		                e.getMessage());
		    }
		}
}
