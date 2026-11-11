package com.stackly.service;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;

import com.stackly.exception.DoctorAlreadyExistsException;
import com.stackly.exception.DoctorNotFoundException;
import com.stackly.model.Doctor;




public class DoctorService {

    
    private Map<Integer, Doctor> doctors = new HashMap<>();
    
    public void addDoctor(Doctor doctor){
    	if (doctor.getId()<=0) {
			throw new IllegalArgumentException("Doctor ID must br Greater than zero!");
		}
    	
    	if (doctor.getName()==null || doctor.getName().trim().isEmpty()) {
			throw new IllegalArgumentException("Doctor name can't be empty!!!");
		}
    	
    	if (doctor.getAge()<=0 || doctor.getAge()>120) {
    		throw new IllegalArgumentException("Invalid Doctor Age!!");
		}
    	
    	if (doctor.getSpecialization()==null || doctor.getSpecialization().trim().isEmpty()) {
    		throw new IllegalArgumentException("Specialization can't be empty!!!");
		}
    	
    	if (doctor.getPhone()==null || !doctor.getPhone().matches("[0-9]{10}")) {
    		throw new IllegalArgumentException("Phone number must contain exactly 10 digits!!!!!");
		}
    	
    	int id=doctor.getId();
    	
    	if (doctors.containsKey(id)) {
			throw new DoctorAlreadyExistsException("Doctor ID already exists!!!");
		}
    	
    	doctors.put(id, doctor);
    	System.out.printf("%nDoctor added successfully!!!%n");
    	
    }
    
    public Doctor getDoctorById(int id) {
    	if (!doctors.containsKey(id)) {
			throw new DoctorNotFoundException("Doctor not found with ID : "+id);
		}
    	return doctors.get(id);
    }
    
    public void viewAllDoctors() {
    	if (doctors.isEmpty()) {
			System.out.printf("%nNo Doctors registered!!%n");
			return;
		}
    	System.out.printf("%-10s %-15s %-8s %-10s %-15s %-20s%n",
                "ID", "NAME", "AGE", "GENDER","PHONE", "SPECIALIZATION");
    	System.out.println("--------------------------------------------------------------------------");
    	
    	for (Doctor d : doctors.values()) {
			System.out.printf("%-10d %-15s %-8d %-10s %-15s %-20s%n",
					d.getId(),d.getName(),d.getAge(),d.getGender(),d.getPhone(),d.getSpecialization());
		}
    }
    
    public void addAvailableSlot(int doctorId, LocalTime time) {
    	Doctor doctor=getDoctorById(doctorId);
    	
    	if (time==null) {
    		throw new IllegalArgumentException("Time cannot be null!");
		}
    	
    	if (doctor.getAvailableSlots().contains(time)) {
    		System.out.printf("This time slot already exists!%n");
            return;
		}
    	
    	doctor.addAvailableSlot(time);
    	System.out.printf("Available slot added successfully!!!%n");

    	
    	
    }
    
    public void viewDoctorSlots(int doctorId) {

        Doctor doctor = getDoctorById(doctorId);

        System.out.printf("%nAVAILABLE SLOTS FOR DR. %s%n", doctor.getName());

        if (doctor.getAvailableSlots().isEmpty()) {
            System.out.printf("No available slots found!%n");
            return;
        }

        System.out.printf("%-15s%n", "AVAILABLE TIME");
        System.out.printf("---------------------%n");

        for (LocalTime time : doctor.getAvailableSlots()) {
            System.out.printf("%-15s%n", time);
        }
        
    }
    
    public void removeAvailableSlot(int doctorId, LocalTime time) {

        Doctor doctor = getDoctorById(doctorId);

        if (time == null) {
            throw new IllegalArgumentException("Time cannot be null!");
        }

        if (!doctor.getAvailableSlots().contains(time)) {
            System.out.printf("Slot not found!%n");
            return;
        }

        doctor.removeAvailableSlot(time);

        System.out.printf("Available slot removed successfully!!!%n");
    }

}
