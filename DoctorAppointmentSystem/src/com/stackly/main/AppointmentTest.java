package com.stackly.main;

import java.time.LocalDate;
import java.time.LocalTime;

import com.stackly.model.Doctor;
import com.stackly.model.Patient;
import com.stackly.service.AppointmentService;
import com.stackly.service.DoctorService;
import com.stackly.service.PatientService;

public class AppointmentTest {
	public static void main(String[] args) {

        PatientService ps = new PatientService();
        DoctorService ds = new DoctorService();
        AppointmentService as = new AppointmentService();

        Patient p1 = new Patient(
                201, "Kumar", 25, "Male",
                "9876543210", "Fever"
        );

        ps.registerPatient(p1);
        
        Doctor d1 = new Doctor(
                101, "Ravi", 40, "Male",
                "9876543211", "Cardiology"
        );

        ds.addDoctor(d1);

        ds.addAvailableSlot(101, LocalTime.of(10, 0));
        ds.addAvailableSlot(101, LocalTime.of(11, 0));
        
        System.out.printf("%nBOOK APPOINTMENT%n");

        as.bookAppointment(
                1,
                201,
                101,
                LocalDate.of(2026, 11, 6),
                LocalTime.of(10, 0),
                ps,
                ds
        );
        System.out.printf("%nTRY DUPLICATE BOOKING%n");

        as.bookAppointment(
                2,
                201,
                101,
                LocalDate.of(2026, 11, 6),
                LocalTime.of(10, 0),
                ps,
                ds
        );
        System.out.printf("%nCANCEL APPOINTMENT%n");

        as.cancelAppointment(1);

        // Try cancelling again
        as.cancelAppointment(1);

        // Try invalid appointment ID
        as.cancelAppointment(999);
        
        Patient p2 = new Patient(
                202, "Arun", 30, "Male",
                "9876543212", "Headache"
        );

        ps.registerPatient(p2);
        
        System.out.printf("%nREBOOK CANCELLED SLOT%n");

        as.bookAppointment(
                2,
                202,
                101,
                LocalDate.of(2026, 11, 6),
                LocalTime.of(10, 0),
                ps,
                ds
        );
        
        System.out.printf("%nVIEW ALL APPOINTMENTS%n");

        as.viewAllAppointments();
        
        System.out.printf("%nVIEW DOCTOR SCHEDULE%n");

        as.viewDoctorSchedule(101, ds);
        
        System.out.printf("%nVIEW PATIENT HISTORY%n");

        as.viewPatientHistory(201, ps);

        as.viewPatientHistory(202, ps);
        
        System.out.printf("%nVIEW AVAILABLE SLOTS%n");

        as.viewAvailableSlots(
                101,
                LocalDate.of(2026, 11, 6),
                ds
        );
    }
}
