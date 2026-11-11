package com.stackly.main;

import com.stackly.model.Course;
import com.stackly.service.CourseService;

public class CourseServiceTest {
	public static void main(String[] args) {

        CourseService courseService = new CourseService();

        // Add Courses

        System.out.println("\nADD COURSES");

        courseService.addCourse(
                new Course(101, "Java Full Stack", 6));

        courseService.addCourse(
                new Course(102, "Python Development", 4));

        courseService.addCourse(
                new Course(103, "Web Development", 3));

        // Try Duplicate Course

        System.out.println("\nTRY DUPLICATE COURSE");

        courseService.addCourse(
                new Course(101, "Java Full Stack", 6));

        // Display All Courses

        System.out.println("\nDISPLAY ALL COURSES");

        courseService.displayAllCourses();

        // Search Course

        System.out.println("\nSEARCH COURSE");

        Course course = courseService.searchCourseById(102);

        if (course != null) {

            System.out.printf("Course Found: %s | Duration: %d Months%n",
                    course.getCourseName(),
                    course.getDuration());

        } else {

            System.out.println("Course not found!");

        }

        // Search Invalid Course

        System.out.println("\nSEARCH INVALID COURSE");

        course = courseService.searchCourseById(999);

        if (course != null) {

            System.out.printf("Course Found: %s%n",
                    course.getCourseName());

        } else {

            System.out.println("Course not found with ID: 999");

        }

        // Remove Course

        System.out.println("\nREMOVE COURSE");

        courseService.removeCourseById(103);

        // Display After Removal

        System.out.println("\nCOURSES AFTER REMOVAL");

        courseService.displayAllCourses();

    }
}
