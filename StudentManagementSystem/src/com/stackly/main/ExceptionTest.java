package com.stackly.main;

import com.stackly.exception.DuplicateStudentException;
import com.stackly.exception.InvalidStudentDataException;
import com.stackly.exception.StudentNotFoundException;

public class ExceptionTest {
	public static void main(String[] args) {

        // Test duplicate student exception

        try {

            throw new DuplicateStudentException(
                    "Student ID already exists!"
            );

        } catch (DuplicateStudentException e) {

            System.out.printf("Registration Error: %s%n", e.getMessage());

        }

        // Test student not found exception

        try {

            throw new StudentNotFoundException(
                    "Student not found with ID: 105"
            );

        } catch (StudentNotFoundException e) {

            System.out.printf("Search Error: %s%n", e.getMessage());

        }

        // Test invalid student data exception

        try {

            throw new InvalidStudentDataException(
                    "Marks must be between 0 and 100!"
            );

        } catch (InvalidStudentDataException e) {

            System.out.printf("Validation Error: %s%n", e.getMessage());

        }

    }
}
