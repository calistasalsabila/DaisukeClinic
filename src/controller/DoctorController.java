package controller;

import dataStructure.LinkedList;
import model.Doctor;
import model.Patient;
import model.Appointment;

import java.time.LocalDateTime;
import java.util.Scanner;

public class DoctorController {
    private LinkedList<Doctor> doctorsList;
    private LinkedList<Doctor> loggedInDoctors;
    private Scanner scanner;
    private PatientController patientController;
    private static final int APPOINTMENT_DURATION = 30;

    public DoctorController(PatientController patientController) {
        this.doctorsList = new LinkedList<>();
        this.loggedInDoctors = new LinkedList<>();
        this.scanner = new Scanner(System.in);
        this.patientController = patientController;
    }

    public void displayDoctorsTable() {
        if (doctorsList.isEmpty()) {
            System.out.println("No doctors registered.");
            return;
        }
        System.out.println("+-----------+----------------------+----------------------+");
        System.out.println("| Doctor ID | Name                 | Specialization       |");
        System.out.println("+-----------+----------------------+----------------------+");
        for (Doctor doctor : doctorsList) {
            System.out.printf("| %-9d | %-20s | %-20s |%n",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpecialization());
        }
        System.out.println("+-----------+----------------------+----------------------+");
    }

    public void registerDoctor() {
        System.out.println("+------------------ Doctor Registration ------------------+");
        try {
            System.out.print("| Enter Doctor ID         : ");
            int id = Integer.parseInt(scanner.nextLine());

            // Check for existing ID
            for (Doctor doctor : doctorsList) {
                if (doctor.getId() == id) {
                    System.out.println("| A doctor with this ID already exists.                   ");
                    System.out.println("+---------------------------------------------------------+");
                    return;
                }
            }

            System.out.print("| Enter Doctor Name       : ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("| Doctor name cannot be empty.                            ");
                System.out.println("+---------------------------------------------------------+");
                return;
            }

            System.out.print("| Enter Specialization    : ");
            String specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                System.out.println("| Specialization cannot be empty.                         ");
                System.out.println("+---------------------------------------------------------+");
                return;
            }

            Doctor newDoctor = new Doctor(id, name, specialization);
            doctorsList.add(newDoctor);
            System.out.println("| Doctor registered successfully!                         ");
            System.out.println("+---------------------------------------------------------+");

        } catch (NumberFormatException e) {
            System.out.println("| Invalid ID format. Please enter a valid integer.        |");
            System.out.println("+---------------------------------------------------------+");
        } catch (Exception e) {
            System.out.println("| An error occurred during registration: " + e.getMessage());
            System.out.println("+---------------------------------------------------------+");
        }
    }

    public void loginDoctor() {
        System.out.print("Enter Doctor ID to Login: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Doctor doctor : doctorsList) {
                if (doctor.getId() == id) {
                    doctor.setLoginTimeNow();
                    loggedInDoctors.add(doctor);
                    System.out.println("+-----------+----------------------+----------------------+");
                    System.out.printf("| %-9d | %-20s | %-20s |%n",
                            doctor.getId(),
                            doctor.getName(),
                            doctor.getSpecialization());
                    System.out.println("+-----------+----------------------+----------------------+");
                    System.out.println("Doctor logged in at " + doctor.getLoginTime());
                    return;
                }
            }
            System.out.println("Doctor with this ID not found.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format. Please enter a valid integer.");
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
        }
    }

    public void logoutDoctor() {
        if (loggedInDoctors.isEmpty()) {
            System.out.println("No doctors currently logged in.");
            return;
        }

        System.out.println("+-----------+----------------------+----------------------+");
        System.out.println("| Doctor ID | Name                 | Specialization       |");
        System.out.println("+-----------+----------------------+----------------------+");
        for (Doctor doctor : loggedInDoctors) {
            System.out.printf("| %-9d | %-20s | %-20s |%n",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpecialization());
        }
        System.out.println("+-----------+----------------------+----------------------+");

        System.out.print("Enter Doctor ID to Logout: ");
        String input = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            return;
        }

        Doctor doctorToRemove = null;
        for (Doctor doctor : loggedInDoctors) {
            if (doctor.getId() == id) {
                doctorToRemove = doctor;
                break;
            }
        }

        if (doctorToRemove != null) {
            loggedInDoctors.remove(doctorToRemove);
            System.out.println("Doctor " + doctorToRemove.getName() + " has logged out.");
        } else {
            System.out.println("Doctor ID not found in active sessions.");
        }
    }

    public void displayLoggedInDoctors() {
        if (loggedInDoctors.isEmpty()) {
            System.out.println("No doctors currently logged in.");
            return;
        }

        System.out.println("+-----------+----------------------+----------------------+--------------------------+");
        System.out.println("| Doctor ID | Name                 | Specialization       | Login Time               |");
        System.out.println("+-----------+----------------------+----------------------+--------------------------+");
        for (Doctor doctor : loggedInDoctors) {
            // Format login time to exclude excessive fraction of seconds
            String formattedLoginTime = "";
            if (doctor.getLoginTime() != null) {
                formattedLoginTime = doctor.getLoginTime()
                        .withNano(0) // remove nanoseconds
                        .toString()
                        .replace('T', ' '); // optional: make it more readable
            }
            System.out.printf("| %-9d | %-20s | %-20s | %-24s |%n",
                    doctor.getId(),
                    doctor.getName(),
                    doctor.getSpecialization(),
                    formattedLoginTime);
        }
        System.out.println("+-----------+----------------------+----------------------+--------------------------+");
    }

    public void processAndDiagnoseNextAppointment(String specialization) {
        if (loggedInDoctors.isEmpty()) {
            System.out.println("No doctors are currently logged in. Please log in first.");
            return;
        }

        // Check if the specialization is available
        if (!patientController.isSpecializationAvailable(specialization)) {
            System.out.println("Specialization '" + specialization + "' not found.");
            return;
        }

        Appointment nextAppointment = null;
        try {
            nextAppointment = patientController.processNextAppointment(specialization);
            if (nextAppointment == null)
                return;
        } catch (Exception e) {
            System.out.println("An error occurred while processing the next appointment: " + e.getMessage());
            return;
        }

        Doctor appointmentDoctor = null;
        for (Doctor doctor : loggedInDoctors) {
            if (doctor.getId() == nextAppointment.getDoctorId()) {
                appointmentDoctor = doctor;
                break;
            }
        }

        if (appointmentDoctor == null) {
            System.out.println("Doctor handling this appointment is not currently logged in.");
            return;
        }

        Patient patient = null;
        try {
            patient = patientController.getPatientById(nextAppointment.getPatientId());
            if (patient == null) {
                System.out.println("Patient not found.");
                return;
            }
        } catch (Exception e) {
            System.out.println("An error occurred while retrieving the patient: " + e.getMessage());
            return;
        }

        System.out.println("+------------------- Appointment Processing -------------------+");
        System.out.printf("| %-15s | %-20s | %-20s |%n", "Patient Name", "Doctor Name", "Specialization");
        System.out.println("+-----------------+----------------------+----------------------+");
        System.out.printf("| %-15s | %-20s | %-20s |%n",
                patient.getName(),
                appointmentDoctor.getName(),
                appointmentDoctor.getSpecialization());
        System.out.println("+-----------------+----------------------+----------------------+");

        System.out.print("Enter diagnosis for patient " + patient.getName() + ": ");
        String diagnosis = scanner.nextLine().trim();

        if (diagnosis.isEmpty()) {
            System.out.println("Diagnosis cannot be empty.");
            return;
        }

        try {
            patientController.addDiagnosis(patient.getId(), diagnosis);
            System.out.println("Diagnosis successfully recorded for patient " + patient.getName());
        } catch (Exception e) {
            System.out.println("An error occurred while adding the diagnosis: " + e.getMessage());
        }

        System.out.print("Enter plan for patient " + patient.getName() + ": ");
        String plan = scanner.nextLine().trim();

        if (plan.isEmpty()) {
            System.out.println("Plan cannot be empty.");
            return;
        }
        try {
            patientController.addPlan(patient.getId(), plan);
            System.out.println("Plan successfully added for patient " + patient.getName());
        } catch (Exception e) {
            System.out.println("An error occurred while adding the plan: " + e.getMessage());
        }

        System.out.print("Enter prescriptions for patient " + patient.getName() + ": ");
        String prescriptions = scanner.nextLine().trim();

        if (prescriptions.isEmpty()) {
            System.out.println("Prescriptions cannot be empty.");
            return;
        }

        try {
            patientController.addPrescriptions(patient.getId(), prescriptions);
            System.out.println("Prescriptions successfully added for patient " + patient.getName());
        } catch (Exception e) {
            System.out.println("An error occurred while adding the prescriptions: " + e.getMessage());
        }
    }

    public LinkedList<Doctor> getDoctorsList() {
        return doctorsList;
    }

    public void addDoctor(Doctor doctor) {
        doctorsList.add(doctor);
    }

    public LinkedList<String> getAllSpecializations() {
        LinkedList<String> specializations = new LinkedList<>();
        for (Doctor doctor : doctorsList) {
            if (doctor != null && !specializations.contains(doctor.getSpecialization())) {
                specializations.add(doctor.getSpecialization());
            }
        }
        return specializations;
    }

    public LinkedList<Doctor> getDoctorsBySpecialization(String specialization) {
        LinkedList<Doctor> doctors = new LinkedList<>();
        if (specialization == null || specialization.trim().isEmpty()) {
            System.out.println("Invalid specialization input.");
            return doctors;
        }
        for (Doctor doctor : doctorsList) {
            if (doctor.getSpecialization().equalsIgnoreCase(specialization)) {
                doctors.add(doctor);
            }
        }
        return doctors;
    }

    public boolean isDoctorAvailable(int doctorId, LocalDateTime appointmentTime) {
        Doctor doctor = null;
        for (Doctor d : doctorsList) {
            if (d.getId() == doctorId) {
                doctor = d;
                break;
            }
        }

        System.out.println("+----------------------------- Doctor Availability -----------------------------+");
        if (doctor == null) {
            System.out.printf("| %-70s |\n", "Doctor with ID " + doctorId + " not found.");
            System.out.println("+-------------------------------------------------------------------------------+");
            return false;
        }

        // Check if the doctor is logged in
        if (!loggedInDoctors.contains(doctor)) {
            System.out.printf(" %-70s \n", "Doctor " + doctor.getName() + " is not currently logged in.");
            System.out.println("+-------------------------------------------------------------------------------+");
            return false;
        }

        // Check for appointment conflicts
        for (Appointment existingAppointment : doctor.getAppointments()) {
            LocalDateTime existingStart = existingAppointment.getAppointmentTime();
            LocalDateTime existingEnd = existingStart.plusMinutes(APPOINTMENT_DURATION);
            LocalDateTime newStart = appointmentTime;
            LocalDateTime newEnd = appointmentTime.plusMinutes(APPOINTMENT_DURATION);

            // Check for overlap
            if (newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart)) {
                System.out.printf(" %-70s \n", "Doctor " + doctor.getName() + " already has an appointment at that time.");
                System.out.println("+-------------------------------------------------------------------------------+");
                return false; // Doctor already has an appointment at this time
            }
        }

        System.out.printf(" %-70s \n", "Doctor " + doctor.getName() + " is available at the chosen time.");
        System.out.println("+-------------------------------------------------------------------------------+");
        return true; // Available
    }
}