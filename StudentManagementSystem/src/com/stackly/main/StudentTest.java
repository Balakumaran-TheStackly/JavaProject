package com.stackly.main;

import com.stackly.model.Course;
import com.stackly.model.Student;
import com.stackly.model.StudentStatus;

public class StudentTest {
	public static void main(String[] args) {

        Course course = new Course(101, "Java Full Stack", 6);

        Student student = new Student(
                1001,
                "Kumar",
                "kumar@gmail.com",
                85.50,
                90,
                StudentStatus.ACTIVE,
                course
        );

        System.out.println("================================================");
        System.out.println("               STUDENT DETAILS");
        System.out.println("================================================");

        System.out.printf("%-15s : %d%n", "Student ID", student.getId());

        System.out.printf("%-15s : %s%n", "Student Name", student.getName());

        System.out.printf("%-15s : %s%n", "Email", student.getEmail());

        System.out.printf("%-15s : %.2f%n", "Marks", student.getMarks());

        System.out.printf("%-15s : %d%%%n", "Attendance", student.getAttendance());

        System.out.printf("%-15s : %s%n", "Status", student.getStatus());

        System.out.printf("%-15s : %s%n", "Course", student.getCourse().getCourseName());

        System.out.printf("%-15s : %d Months%n", "Duration", student.getCourse().getDuration());

        System.out.println("================================================");

    }

}
