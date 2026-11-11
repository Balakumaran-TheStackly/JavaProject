package com.stackly.main;

import com.stackly.model.Course;

public class CourseTest {
	public static void main(String[] args) {

        Course course = new Course(101, "Java Full Stack", 6);

        System.out.println("COURSE DETAILS");
        System.out.println("--------------------------------");

        System.out.printf("%-15s : %d%n",
                "Course ID", course.getCourseId());

        System.out.printf("%-15s : %s%n",
                "Course Name", course.getCourseName());

        System.out.printf("%-15s : %d Months%n",
                "Duration", course.getDuration());

    }

}
