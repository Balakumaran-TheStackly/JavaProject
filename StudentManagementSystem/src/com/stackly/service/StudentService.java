package com.stackly.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.stackly.exception.DuplicateStudentException;
import com.stackly.exception.InvalidStudentDataException;
import com.stackly.exception.StudentNotFoundException;
import com.stackly.model.Student;

public class StudentService {
	private HashMap<Integer, Student> students=new HashMap<Integer, Student>();
	
	public void addStudent(Student student) throws DuplicateStudentException,InvalidStudentDataException {
		
		if (students.containsKey(student.getId())) {
			throw new DuplicateStudentException("Student ID already exists: "+student.getId());
		}
		
		if (student.getMarks() < 0 || student.getMarks() > 100) {
	        throw new InvalidStudentDataException( "Marks must be between 0 and 100!");
	    }

	    if (student.getAttendance() < 0 || student.getAttendance() > 100) {
	        throw new InvalidStudentDataException("Attendance must be between 0 and 100!");
	    }

	    students.put(student.getId(), student);

	    System.out.printf("Student registered successfully!!!!%n");
	}
	
	public void displayAllStudents() {
		if (students.isEmpty()) {
			System.out.printf("No students registered!%n");
	        return;
		}
		System.out.printf("================================================================================================================%n");
	    System.out.printf("                                             ALL STUDENTS%n");
	    System.out.printf("================================================================================================================%n");

	    System.out.printf("%-10s %-15s %-25s %-10s %-12s %-12s %-20s%n",
	            "ID", "NAME", "EMAIL", "MARKS", "ATTENDANCE", "STATUS", "COURSE");

	    System.out.printf("----------------------------------------------------------------------------------------------------------------%n");

	    for (Student s : students.values()) {

	        System.out.printf("%-10d %-15s %-25s %-10.2f %-12s %-12s %-20s%n",
	                s.getId(),
	                s.getName(),
	                s.getEmail(),
	                s.getMarks(),
	                s.getAttendance() + "%",
	                s.getStatus(),
	                s.getCourse().getCourseName());
	    }

	    System.out.printf("================================================================================================================%n");
	}
	
	public Student searchStudentById(int studentId) throws StudentNotFoundException {
		Student student=students.get(studentId);
		if (student==null) {
			throw new StudentNotFoundException("Student not found with ID: "+studentId);
		}
		
		return student;
	}
	
	public ArrayList<Student> searchStudentByName(String name) throws StudentNotFoundException{
		
		ArrayList<Student> result=new ArrayList<Student>();
		
		for (Student s : students.values()) {
			if (s.getName().toLowerCase().contains(name.toLowerCase())) {
				result.add(s);
			}
		}
		if (result.isEmpty()) {
			throw new StudentNotFoundException("Student not found with name : "+name);
		}
		return result;
	}
	
	public void updateStudentMarks(int studentId,double marks) throws StudentNotFoundException,InvalidStudentDataException{
		
		Student student=students.get(studentId);
		if (student==null) {
			throw new StudentNotFoundException("Student not found with ID: "+studentId);
		}
		
		if (marks<0 || marks >100) {
			throw new InvalidStudentDataException("Marks must be between 0 and 100!!!");
		}
		
		student.setMarks(marks);
		
		System.out.printf("Student marks updated successfully!!!!%n");
	}
	
	public void updateStudentAttendance(int studentId,int attendance) throws StudentNotFoundException,InvalidStudentDataException {
		
		Student student =students.get(studentId);
		
		if (student==null) {
			throw new StudentNotFoundException("Student not found with Id: "+studentId);
		}
		
		if (attendance < 0 || attendance > 100) {
			throw new InvalidStudentDataException("Attendance must be between 0 and 100!!!!!");
	    }
		
		student.setAttendance(attendance);

	    System.out.printf("Student attendance updated successfully!!!!%n");
	}
	
	public String calculateGrade(int studentId) throws StudentNotFoundException{
		
		Student student=students.get(studentId);
		
		if (student==null) {
			throw new StudentNotFoundException("Student not found with Id: "+studentId);
		}
		
		double marks=student.getMarks();
		
		if (marks >= 90) {
	        return "A+";
	    }
	    else if (marks >= 80) {
	        return "A";
	    }
	    else if (marks >= 70) {
	        return "B";
	    }
	    else if (marks >= 60) {
	        return "C";
	    }
	    else if (marks >= 50) {
	        return "D";
	    }
	    else {
	        return "F";
	    }
		
	}
	
	public void removeStudent(int studentId) throws StudentNotFoundException {
		
		if (!students.containsKey(studentId)) {
			throw new StudentNotFoundException("Student not found with ID: " + studentId);
		}
		students.remove(studentId);
		
		System.out.printf("Student removed successfully!!!!%n");
	}
	
	public ArrayList<Student> findStudentTopper() throws StudentNotFoundException{
		if (students.isEmpty()) {
			throw new StudentNotFoundException("No Student available to find topper!!!");
		}
		double highestMarks=-1;
		
		for (Student s : students.values()) {
			if (s.getMarks()>highestMarks) {
				highestMarks=s.getMarks();
			}
		}
		ArrayList<Student> toppers=new ArrayList<Student>();
		
		for (Student s : students.values()) {
			if (s.getMarks()==highestMarks) {
				toppers.add(s);
			}
		}
		return toppers;
	}
	
	public ArrayList<Student> getAllStudents() {

	    return new ArrayList<>(students.values());
	}

}
