package com.stackly.model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Doctor extends Person {
	
	private String specialization;
	
	private ArrayList<LocalTime> availableSlots;

	public Doctor(int id, String name, int age, String gender, String phone, String specialization) {
		super(id, name, age, gender, phone);
		this.specialization = specialization;
		this.availableSlots = new ArrayList<>();
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public ArrayList<LocalTime> getAvailableSlots() {
		return availableSlots;
	}
	//add slot
	public void addAvailableSlot(LocalTime time) {
        if (!availableSlots.contains(time)) {
	           availableSlots.add(time);
        }
    }
	//remove slot
	public void removeAvailableSlot(LocalTime time) {
        availableSlots.remove(time);
    }
	

}
