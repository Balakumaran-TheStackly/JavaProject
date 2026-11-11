package com.stackly.service;

import java.util.HashMap;
import java.util.Map;

import com.stackly.exception.PatientAlreadyExistsException;
import com.stackly.exception.PatientNotFoundException;
import com.stackly.model.Patient;



public class PatientService {
	
	private Map<Integer, Patient> patients=new HashMap<Integer, Patient>();
	
	public void registerPatient(Patient patient) {
		
		if (patient.getId()<=0) {
			throw new IllegalArgumentException("Patient ID must be greater than zero!!!");
		}
		
		if (patient.getName() == null || patient.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Patient name can't be Empty!!!");
		}
		
		if (patient.getAge()<=0 || patient.getAge()>120) {
			throw new IllegalArgumentException("Invalid patient age!!!");
		}
		
		if (patient.getGender() == null ||patient.getGender().trim().isEmpty()) {
	            throw new IllegalArgumentException("Gender cannot be empty!");
	    }

	       
	    if (patient.getPhone() == null ||!patient.getPhone().matches("[0-9]{10}")) {
	            throw new IllegalArgumentException("Phone number must contain exactly 10 digits!");
	    }

	    
	    if (patient.getMedicalHistory() == null || patient.getMedicalHistory().trim().isEmpty()) {
	            throw new IllegalArgumentException("Medical history cannot be empty!");
	    }
	    
	    
		int id=patient.getId();
		
		if (patients.containsKey(id)) {
			
			throw new PatientAlreadyExistsException("Patient ID already exists!!!!");
		}
		
		patients.put(id, patient);
		
		System.out.printf("%nPatient Register Successfully!!!%n");
	}
	
	public Patient getPatientById(int id) {
		if (!patients.containsKey(id)) {
			throw new PatientNotFoundException("Patient not Found with ID : "+id);
		}
		
		return patients.get(id);
		
	} 
	
	public void viewAllPatients(){
		if (patients.isEmpty()) {
			System.out.printf("%nNo patients registered!%n");
			return;
		}
		
		System.out.printf("%-10s %-15s %-8s %-10s %-15s %-20s%n",
					"ID", "NAME", "AGE", "GENDER","PHONE", "MEDICAL HISTORY");
		
		System.out.println("--------------------------------------------------------------------------");
		
		for (Patient p : patients.values()) {
			System.out.printf("%-10d %-15s %-8d %-10s %-15s %-20s%n",
	                p.getId(),p.getName(),p.getAge(),p.getGender(),p.getPhone(),p.getMedicalHistory());
		}
		
	}

}
