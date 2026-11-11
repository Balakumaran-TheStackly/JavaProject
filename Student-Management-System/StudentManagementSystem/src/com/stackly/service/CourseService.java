package com.stackly.service;

import java.util.ArrayList;

import com.stackly.model.Course;

public class CourseService {
	private ArrayList<Course> courses=new ArrayList<>();
	
	public void addCourse(Course course) {
		for (Course c : courses) {
			if (c.getCourseId()== course.getCourseId()) {
				System.out.printf("Course ID already exists!!!!%n");
				return;
			}
		}
		courses.add(course);
		System.out.printf("Course added successfully!!!!%n");
	}
	
	public void displayAllCourses() {
		if (courses.isEmpty()) {
			System.out.printf("No courses available!!!%n");
			return;
			
		}
		System.out.printf("==============================================================%n");
        System.out.printf("                    AVAILABLE COURSES%n");
        System.out.printf("==============================================================%n");

        System.out.printf("%-12s %-25s %-15s%n",
                "COURSE ID", "COURSE NAME", "DURATION");

        System.out.printf("--------------------------------------------------------------%n");
        
        for (Course c : courses) {
			System.out.printf("%-12d %-25s %-15s%n",c.getCourseId(),c.getCourseName(),c.getDuration()+" Months");
		}
        System.out.printf("==============================================================%n");
	}
	
	public Course searchCourseById(int courseId) {
		for (Course c : courses) {
			if (c.getCourseId()==courseId) {
				return c;
			}
		}
		
		return null;
	}
	
	public boolean removeCourseById(int courseId) {
		for (int i = 0; i < courses.size(); i++) {
			if (courses.get(i).getCourseId()==courseId) {
				courses.remove(i);
				System.out.printf("Course removed successfully!!%n");
				return true;
			}
			
		}
		
		System.out.printf("Course not found!!!%n");
		return false;
	}

}
