package com.stackly.exception;

public class PatientAlreadyExistsException extends RuntimeException {
	public PatientAlreadyExistsException(String msg) {
		super(msg);
	}
}
