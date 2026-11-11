package com.stackly.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import com.stackly.model.Appointment;
import com.stackly.model.Doctor;
import com.stackly.model.Patient;

import com.stackly.service.PatientService;
import com.stackly.service.DoctorService;;


public class AppointmentService {
	
	private Map<Integer, Appointment> appointments =new HashMap<Integer, Appointment>();
	
	
	public void bookAppointment(int appointmentId, int patientId, int doctorId,LocalDate date, LocalTime time, PatientService ps , DoctorService ds) {
		Patient patient=ps.getPatientById(patientId);
		Doctor doctor=ds.getDoctorById(doctorId);
		
		if (appointmentId<=0) {
			throw new IllegalArgumentException("Invalid appointment ID!!!");
		}
		if (date == null || date.isBefore(LocalDate.now())) {
	        throw new IllegalArgumentException("Appointment date must be today or a future date!");
	    }
		if (time == null) {
	        throw new IllegalArgumentException("Appointment time cannot be null!");
	    }
		
		if (!doctor.getAvailableSlots().contains(time)) {
	        throw new IllegalArgumentException("Doctor is not available at this time!");
	    }
		
//		//key
//		String slotKey = doctorId + "_" + date + "_" + time;
//		
//		if (appointments.containsKey(slotKey)&& appointments.get(slotKey).getStatus().equals("BOOKED")) {
//	        System.out.printf("This appointment slot is already booked!%n");
//	        return;
//	    }
		if (appointments.containsKey(appointmentId)) {
		    throw new IllegalArgumentException("Appointment ID already exists!");
		}
		for (Appointment a : appointments.values()) {

		    if (a.getDoctorId() == doctorId
		            && a.getAppointmentDate().equals(date)
		            && a.getAppointmentTime().equals(time)
		            && a.getStatus().equals("BOOKED")) {

		        System.out.printf("This appointment slot is already booked!%n");
		        return;
		    }
		}
		
		Appointment appointment=new Appointment(appointmentId, patientId, doctorId, date, time);
		
		appointments.put(appointmentId, appointment);
		System.out.printf("%nAppointment booked successfully!!!%n");
	    System.out.printf("Appointment ID : %d%n", appointmentId);
	    System.out.printf("Patient Name   : %s%n", patient.getName());
	    System.out.printf("Doctor Name    : %s%n", doctor.getName());
	    System.out.printf("Date           : %s%n", date);
	    System.out.printf("Time           : %s%n", time);
	    
	    
	}
	
	public void cancelAppointment(int appointmentId) {

	    for (Appointment appointment : appointments.values()) {

	        if (appointment.getAppointmentId() == appointmentId) {

	            if (appointment.getStatus().equals("CANCELLED")) {
	                System.out.printf("Appointment is already cancelled!%n");
	                return;
	            }

	            appointment.setStatus("CANCELLED");

	            System.out.printf("%nAppointment cancelled successfully!!!%n");
	            System.out.printf("Appointment ID : %d%n", appointmentId);

	            return;
	        }
	    }

	    System.out.printf("Appointment not found with ID: %d%n", appointmentId);
	}
	
	public void viewAllAppointments() {

	    if (appointments.isEmpty()) {
	        System.out.printf("%nNo appointments found!%n");
	        return;
	    }

	    System.out.printf("%nALL APPOINTMENTS%n");

	    System.out.printf("%-15s %-12s %-12s %-15s %-12s %-12s%n",
	            "APPOINTMENT ID", "PATIENT ID", "DOCTOR ID","DATE", "TIME", "STATUS");

	    System.out.printf("----------------------------------------------------------------------------%n");

	    for (Appointment a : appointments.values()) {

	        System.out.printf("%-15d %-12d %-12d %-15s %-12s %-12s%n",
	                a.getAppointmentId(),
	                a.getPatientId(),
	                a.getDoctorId(),
	                a.getAppointmentDate(),
	                a.getAppointmentTime(),
	                a.getStatus());
	    }
	}
	
	public void viewDoctorSchedule(int doctorId, DoctorService ds) {

	    
	    Doctor doctor = ds.getDoctorById(doctorId);

	    System.out.printf("%nDOCTOR SCHEDULE%n");
	    System.out.printf("Doctor ID   : %d%n", doctor.getId());
	    System.out.printf("Doctor Name : %s%n", doctor.getName());
	    System.out.printf("Specialization : %s%n", doctor.getSpecialization());

	    System.out.printf("%n%-15s %-12s %-15s %-12s %-12s%n",
	            "APPOINTMENT ID", "PATIENT ID", "DATE", "TIME", "STATUS");

	    System.out.printf("--------------------------------------------------------------------%n");

	    boolean found = false;

	    for (Appointment a : appointments.values()) {

	        if (a.getDoctorId() == doctorId) {

	            System.out.printf("%-15d %-12d %-15s %-12s %-12s%n",
	                    a.getAppointmentId(),
	                    a.getPatientId(),
	                    a.getAppointmentDate(),
	                    a.getAppointmentTime(),
	                    a.getStatus());

	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.printf("No appointments found for this doctor!%n");
	    }
	}
	
	public void viewPatientHistory(int patientId, PatientService ps) {

	    // Check whether patient exists
	    Patient patient = ps.getPatientById(patientId);

	    System.out.printf("%nPATIENT APPOINTMENT HISTORY%n");

	    System.out.printf("Patient ID   : %d%n", patient.getId());
	    System.out.printf("Patient Name : %s%n", patient.getName());

	    System.out.printf("%n%-15s %-12s %-15s %-12s %-12s%n",
	            "APPOINTMENT ID", "DOCTOR ID", "DATE", "TIME", "STATUS");

	    System.out.printf("--------------------------------------------------------------------%n");

	    boolean found = false;

	    for (Appointment a : appointments.values()) {

	        if (a.getPatientId() == patientId) {

	            System.out.printf("%-15d %-12d %-15s %-12s %-12s%n",
	                    a.getAppointmentId(),
	                    a.getDoctorId(),
	                    a.getAppointmentDate(),
	                    a.getAppointmentTime(),
	                    a.getStatus());

	            found = true;
	        }
	    }

	    if (!found) {
	        System.out.printf("No appointment history found!%n");
	    }
	}
	
	
	public void viewAvailableSlots(int doctorId, LocalDate date,
            DoctorService ds) {
		
		Doctor doctor = ds.getDoctorById(doctorId);
		
		if (date == null || date.isBefore(LocalDate.now())) {
			throw new IllegalArgumentException("Invalid appointment date!");
		}
		
		System.out.printf("%nAVAILABLE SLOTS FOR DR. %s%n", doctor.getName());
		System.out.printf("Date : %s%n", date);
		
		System.out.printf("%nAVAILABLE TIME%n");
		System.out.printf("---------------------%n");
		
		boolean found = false;
		
		for (LocalTime time : doctor.getAvailableSlots()) {
		
			boolean booked = false;
		
			for (Appointment a : appointments.values()) {
		
				if (a.getDoctorId() == doctorId&& a.getAppointmentDate().equals(date)&& a.getAppointmentTime().equals(time)&& a.getStatus().equals("BOOKED")) {
					booked = true;
					break;
				}
			}
		
			if (!booked) {
				System.out.printf("%s%n", time);
				found = true;
			}
		}
		
		if (!found) {
			System.out.printf("No available slots found!%n");
		}
}

}
