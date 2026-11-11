package com.stackly.model;

public class Patient extends Person{
	
	private String medicalHistory;

	public Patient(int id, String name, int age, String gender, String phone,String medicalHistory) {
		super(id, name, age, gender, phone);
		// TODO Auto-generated constructor stub
		this.medicalHistory=medicalHistory;
	}

	public String getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	
	
}
