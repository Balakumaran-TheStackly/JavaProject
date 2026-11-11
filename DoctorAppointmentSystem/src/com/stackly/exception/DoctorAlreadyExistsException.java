package com.stackly.exception;

public class DoctorAlreadyExistsException extends RuntimeException {
	public DoctorAlreadyExistsException(String msg) {
		super(msg);
	}
}
