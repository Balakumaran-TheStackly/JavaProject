package com.stackly.main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

import com.stackly.model.Doctor;
import com.stackly.model.Patient;
import com.stackly.service.AppointmentService;
import com.stackly.service.DoctorService;
import com.stackly.service.PatientService;

public class DoctorAppointmentApplication {
	static Scanner sc = new Scanner(System.in);

    static PatientService ps = new PatientService();
    static DoctorService ds = new DoctorService();
    static AppointmentService as = new AppointmentService();

    public static void main(String[] args) {

        System.out.println("==================================");
        System.out.println("   DOCTOR APPOINTMENT SYSTEM");
        System.out.println("==================================");

        System.out.println("Application started successfully!");
        
        boolean running=true;
        
        while (running) {
        	System.out.printf("%n========== MAIN MENU ==========%n");
            System.out.printf("1. Register Patient%n");
            System.out.printf("2. Add Doctor%n");
            System.out.printf("3. View All Patients%n");
            System.out.printf("4. View All Doctors%n");
            System.out.printf("5. Add Available Slot%n");
            System.out.printf("6. View Doctor Slots%n");
            System.out.printf("7. Remove Available Slot%n");
            System.out.printf("8. Book Appointment%n");
            System.out.printf("9. Cancel Appointment%n");
            System.out.printf("10. View Available Slots%n");
            System.out.printf("11. View All Appointments%n");
            System.out.printf("12. View Doctor Schedule%n");
            System.out.printf("13. View Patient History%n");
            System.out.printf("0. Exit%n");
            
            System.out.printf("%nEnter your choice: ");
            int choice=Integer.parseInt(sc.nextLine());
            
            switch (choice) {
			case 1:
				try {
			        System.out.printf("%n========== REGISTER PATIENT ==========%n");

			        System.out.printf("Enter Patient ID: ");
			        int id = Integer.parseInt(sc.nextLine());

			        System.out.printf("Enter Patient Name: ");
			        String name = sc.nextLine();

			        System.out.printf("Enter Age: ");
			        int age = Integer.parseInt(sc.nextLine());

			        System.out.printf("Enter Gender: ");
			        String gender = sc.nextLine();

			        System.out.printf("Enter Phone Number: ");
			        String phone = sc.nextLine();

			        System.out.printf("Enter Medical History: ");
			        String history = sc.nextLine();

			        Patient patient = new Patient(id, name, age, gender, phone, history);

			        ps.registerPatient(patient);

			    } catch (Exception e) {
			        System.out.printf("Registration Error: %s%n", e.getMessage());
			    }

                break;
			case 2:
				try {
			        System.out.printf("%n========== ADD DOCTOR ==========%n");

			        System.out.printf("Enter Doctor ID: ");
			        int id = Integer.parseInt(sc.nextLine());

			        System.out.printf("Enter Doctor Name: ");
			        String name = sc.nextLine();

			        System.out.printf("Enter Age: ");
			        int age = Integer.parseInt(sc.nextLine());

			        System.out.printf("Enter Gender: ");
			        String gender = sc.nextLine();

			        System.out.printf("Enter Phone Number: ");
			        String phone = sc.nextLine();

			        System.out.printf("Enter Specialization: ");
			        String specialization = sc.nextLine();

			        Doctor doctor = new Doctor(
			                id, name, age, gender, phone, specialization
			        );

			        ds.addDoctor(doctor);

			    } catch (Exception e) {
			        System.out.printf("Doctor Registration Error: %s%n",
			                e.getMessage());
			    }
                break;
			 case 3:
				 try {
				        System.out.printf("%n========== ALL REGISTERED PATIENTS ==========%n");
				        ps.viewAllPatients();

				    } catch (Exception e) {
				        System.out.printf("Error: %s%n", e.getMessage());
				    }
                 break;
             case 4:
            	 try {
            	        System.out.printf("%n========== ALL REGISTERED DOCTORS ==========%n");
            	        ds.viewAllDoctors();

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 5:
            	 try {
            	        System.out.printf("%n========== ADD AVAILABLE SLOT ==========%n");

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Available Time (HH:mm): ");
            	        String inputTime = sc.nextLine();

            	        LocalTime time = LocalTime.parse(inputTime);

            	        ds.addAvailableSlot(doctorId, time);

            	    } catch (Exception e) {
            	        System.out.printf("Slot Addition Error: %s%n", e.getMessage());
            	    }
                 break;
             case 6:
            	 try {
            	        System.out.printf("%n========== VIEW DOCTOR SLOTS ==========%n");

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        ds.viewDoctorSlots(doctorId);

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 7:
            	 try {
            	        System.out.printf("%n========== REMOVE AVAILABLE SLOT ==========%n");

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Time to Remove (HH:mm): ");
            	        LocalTime time = LocalTime.parse(sc.nextLine());

            	        ds.removeAvailableSlot(doctorId, time);

            	    } catch (Exception e) {
            	        System.out.printf("Slot Removal Error: %s%n", e.getMessage());
            	    }
                 break;
             case 8:
            	 try {
            	        System.out.printf("%n========== BOOK APPOINTMENT ==========%n");

            	        System.out.printf("Enter Appointment ID: ");
            	        int appointmentId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Patient ID: ");
            	        int patientId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Appointment Date (yyyy-MM-dd): ");
            	        LocalDate date = LocalDate.parse(sc.nextLine());

            	        System.out.printf("Enter Appointment Time (HH:mm): ");
            	        LocalTime time = LocalTime.parse(sc.nextLine());

            	        as.bookAppointment(
            	                appointmentId,
            	                patientId,
            	                doctorId,
            	                date,
            	                time,
            	                ps,
            	                ds
            	        );

            	    } catch (Exception e) {
            	        System.out.printf("Appointment Booking Error: %s%n",
            	                e.getMessage());
            	    }
                 break;
             case 9:
            	 try {
            	        System.out.printf("%n========== CANCEL APPOINTMENT ==========%n");

            	        System.out.printf("Enter Appointment ID: ");
            	        int appointmentId = Integer.parseInt(sc.nextLine());

            	        as.cancelAppointment(appointmentId);

            	    } catch (Exception e) {
            	        System.out.printf("Appointment Cancellation Error: %s%n",
            	                e.getMessage());
            	    }
                 break;
             case 10:
            	 try {
            	        System.out.printf("%n========== VIEW AVAILABLE SLOTS ==========%n");

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        System.out.printf("Enter Appointment Date (yyyy-MM-dd): ");
            	        LocalDate date = LocalDate.parse(sc.nextLine());

            	        as.viewAvailableSlots(doctorId, date, ds);

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 11:
            	 try {
            	        System.out.printf("%n========== VIEW ALL APPOINTMENTS ==========%n");

            	        as.viewAllAppointments();

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 12:
            	 try {
            	        System.out.printf("%n========== VIEW DOCTOR SCHEDULE ==========%n");

            	        System.out.printf("Enter Doctor ID: ");
            	        int doctorId = Integer.parseInt(sc.nextLine());

            	        as.viewDoctorSchedule(doctorId, ds);

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 13:
            	 try {
            	        System.out.printf("%n========== VIEW PATIENT HISTORY ==========%n");

            	        System.out.printf("Enter Patient ID: ");
            	        int patientId = Integer.parseInt(sc.nextLine());

            	        as.viewPatientHistory(patientId, ps);

            	    } catch (Exception e) {
            	        System.out.printf("Error: %s%n", e.getMessage());
            	    }
                 break;
             case 0:
                 running = false;
                 System.out.printf("%nThank you for using Doctor Appointment System!%n");
                 break;

             default:
                 System.out.printf("%nInvalid choice! Please try again.%n");
			}
		}
        sc.close();
    }
}
