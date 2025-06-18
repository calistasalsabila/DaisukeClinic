package controller;

import dataStructure.LinkedList;
import model.Doctor;
import model.Patient;
import model.Appointment;

import java.time.LocalDateTime;
import java.util.Scanner;
import controller.AnsiColor;

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
            System.out.println(AnsiColor.Yellow + "No doctors registered." + AnsiColor.Reset);
            return;
        }
        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+");
        System.out.println("| Doctor Id | Name                 | Specialization       |");
        System.out.println("+-----------+----------------------+----------------------+" + AnsiColor.Reset);
        for (Doctor doctor : doctorsList) {
            System.out.printf(AnsiColor.Cyan + "| %-9d | %-20s | %-20s |%n" + AnsiColor.Reset,
                capitalizeFirstLetter(doctor.getId() + ""),
                capitalizeFirstLetter(doctor.getName()),
                capitalizeFirstLetter(doctor.getSpecialization()));
        }
        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);
    }

    public void registerDoctor() {
        System.out.println(AnsiColor.Cyan + "+------------------ Doctor Registration ------------------+" + AnsiColor.Reset);
        try {
            System.out.print(AnsiColor.Yellow + "| Enter doctor id         : " + AnsiColor.Reset);
            int id = Integer.parseInt(scanner.nextLine());

            // Check for existing ID
            for (Doctor doctor : doctorsList) {
                if (doctor.getId() == id) {
                    System.out.println(AnsiColor.Red + "| A doctor with this id already exists.                   " + AnsiColor.Reset);
                    System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);
                    return;
                }
            }

            System.out.print(AnsiColor.Yellow + "| Enter doctor name       : " + AnsiColor.Reset);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(AnsiColor.Red + "| Doctor name cannot be empty.                            " + AnsiColor.Reset);
                System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.Yellow + "| Enter specialization    : " + AnsiColor.Reset);
            String specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                System.out.println(AnsiColor.Red + "| Specialization cannot be empty.                         " + AnsiColor.Reset);
                System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);
                return;
            }

            Doctor newDoctor = new Doctor(id, capitalizeFirstLetter(name), capitalizeFirstLetter(specialization));
            doctorsList.add(newDoctor);
            System.out.println(AnsiColor.Green + "| Doctor registered successfully!                         " + AnsiColor.Reset);
            System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);

        } catch (NumberFormatException e) {
            System.out.println(AnsiColor.Red + "| Invalid id format. Please enter a valid integer.        |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "| An error occurred during registration: " + e.getMessage() + AnsiColor.Reset);
            System.out.println(AnsiColor.Cyan + "+---------------------------------------------------------+" + AnsiColor.Reset);
        }
    }

    // Helper method to capitalize the first letter only
    private String capitalizeFirstLetter(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public void loginDoctor() {
        System.out.print(AnsiColor.Yellow + "Enter doctor id to login: " + AnsiColor.Reset);
        try {
            int id = Integer.parseInt(scanner.nextLine());
            for (Doctor doctor : doctorsList) {
                if (doctor.getId() == id) {
                    doctor.setLoginTimeNow();
                    loggedInDoctors.add(doctor);
                    System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);
                    System.out.printf(AnsiColor.Cyan + "| %-9d | %-20s | %-20s |%n" + AnsiColor.Reset,
                            doctor.getId(),
                            capitalizeFirstLetter(doctor.getName()),
                            capitalizeFirstLetter(doctor.getSpecialization()));
                    System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);
                    System.out.println(AnsiColor.Green + "Doctor logged in at " + doctor.getLoginTime() + AnsiColor.Reset);
                    return;
                }
            }
            System.out.println(AnsiColor.Red + "Doctor with this id not found." + AnsiColor.Reset);
        } catch (NumberFormatException e) {
            System.out.println(AnsiColor.Red + "Invalid id format. Please enter a valid integer." + AnsiColor.Reset);
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred during login: " + e.getMessage() + AnsiColor.Reset);
        }
    }

    public void logoutDoctor() {
        if (loggedInDoctors.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctors currently logged in." + AnsiColor.Reset);
            return;
        }

        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);
        System.out.println(AnsiColor.Cyan + "| Doctor Id | Name                 | Specialization       |" + AnsiColor.Reset);
        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);
        for (Doctor doctor : loggedInDoctors) {
            System.out.printf(AnsiColor.Cyan + "| %-9d | %-20s | %-20s |%n" + AnsiColor.Reset,
                    doctor.getId(),
                    capitalizeFirstLetter(doctor.getName()),
                    capitalizeFirstLetter(doctor.getSpecialization()));
        }
        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+" + AnsiColor.Reset);

        System.out.print(AnsiColor.Yellow + "Enter doctor id to logout: " + AnsiColor.Reset);
        String input = scanner.nextLine();
        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println(AnsiColor.Red + "Invalid id format." + AnsiColor.Reset);
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
            System.out.println(AnsiColor.Green + "Doctor " + capitalizeFirstLetter(doctorToRemove.getName()) + " has logged out." + AnsiColor.Reset);
        } else {
            System.out.println(AnsiColor.Red + "Doctor id not found in active sessions." + AnsiColor.Reset);
        }
    }

    public void displayLoggedInDoctors() {
        if (loggedInDoctors.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctors currently logged in." + AnsiColor.Reset);
            return;
        }

        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+--------------------------+");
        System.out.println("| Doctor Id | Name                 | Specialization       | Login Time               |");
        System.out.println("+-----------+----------------------+----------------------+--------------------------+" + AnsiColor.Reset);
        for (Doctor doctor : loggedInDoctors) {
            String formattedLoginTime = "";
            if (doctor.getLoginTime() != null) {
                formattedLoginTime = doctor.getLoginTime()
                        .withNano(0)
                        .toString()
                        .replace('T', ' ');
            }
            System.out.printf(
                AnsiColor.Cyan + "| %-9d | %-20s | %-20s | %-24s |%n" + AnsiColor.Reset,
                doctor.getId(),
                capitalizeFirstLetter(doctor.getName()),
                capitalizeFirstLetter(doctor.getSpecialization()),
                formattedLoginTime
            );
        }
        System.out.println(AnsiColor.Cyan + "+-----------+----------------------+----------------------+--------------------------+" + AnsiColor.Reset);
    }

    public void processAndDiagnoseNextAppointment(String specialization) {
        if (loggedInDoctors.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctors are currently logged in. Please log in first." + AnsiColor.Reset);
            return;
        }

        // Check if the specialization is available
        if (!patientController.isSpecializationAvailable(specialization)) {
            System.out.println(AnsiColor.Red + "Specialization '" + capitalizeFirstLetter(specialization) + "' not found." + AnsiColor.Reset);
            return;
        }

        Appointment nextAppointment = null;
        try {
            nextAppointment = patientController.processNextAppointment(specialization);
            if (nextAppointment == null)
                return;
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred while processing the next appointment: " + e.getMessage() + AnsiColor.Reset);
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
            System.out.println(AnsiColor.Red + "Doctor handling this appointment is not currently logged in." + AnsiColor.Reset);
            return;
        }

        Patient patient = null;
        try {
            patient = patientController.getPatientById(nextAppointment.getPatientId());
            if (patient == null) {
                System.out.println(AnsiColor.Red + "Patient not found." + AnsiColor.Reset);
                return;
            }
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred while retrieving the patient: " + e.getMessage() + AnsiColor.Reset);
            return;
        }

        System.out.println(AnsiColor.Cyan + "+------------------- Appointment Processing -------------------+" + AnsiColor.Reset);
        System.out.printf(AnsiColor.Cyan + "| %-15s | %-20s | %-20s |%n" + AnsiColor.Reset, "Patient Name", "Doctor Name", "Specialization");
        System.out.println(AnsiColor.Cyan + "+-----------------+----------------------+----------------------+" + AnsiColor.Reset);
        System.out.printf(AnsiColor.Cyan + "| %-15s | %-20s | %-20s |%n" + AnsiColor.Reset,
                capitalizeFirstLetter(patient.getName()),
                capitalizeFirstLetter(appointmentDoctor.getName()),
                capitalizeFirstLetter(appointmentDoctor.getSpecialization()));
        System.out.println(AnsiColor.Cyan + "+-----------------+----------------------+----------------------+" + AnsiColor.Reset);

        System.out.print(AnsiColor.Yellow + "Enter diagnosis for patient " + capitalizeFirstLetter(patient.getName()) + ": " + AnsiColor.Reset);
        String diagnosis = scanner.nextLine().trim();

        if (diagnosis.isEmpty()) {
            System.out.println(AnsiColor.Red + "Diagnosis cannot be empty." + AnsiColor.Reset);
            return;
        }

        try {
            patientController.addDiagnosis(patient.getId(), capitalizeFirstLetter(diagnosis));
            System.out.println(AnsiColor.Green + "Diagnosis successfully recorded for patient " + capitalizeFirstLetter(patient.getName()) + AnsiColor.Reset);
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred while adding the diagnosis: " + e.getMessage() + AnsiColor.Reset);
        }

        System.out.print(AnsiColor.Yellow + "Enter plan for patient " + capitalizeFirstLetter(patient.getName()) + ": " + AnsiColor.Reset);
        String plan = scanner.nextLine().trim();

        if (plan.isEmpty()) {
            System.out.println(AnsiColor.Red + "Plan cannot be empty." + AnsiColor.Reset);
            return;
        }
        try {
            patientController.addPlan(patient.getId(), capitalizeFirstLetter(plan));
            System.out.println(AnsiColor.Green + "Plan successfully added for patient " + capitalizeFirstLetter(patient.getName()) + AnsiColor.Reset);
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred while adding the plan: " + e.getMessage() + AnsiColor.Reset);
        }

        System.out.print(AnsiColor.Yellow + "Enter prescriptions for patient " + capitalizeFirstLetter(patient.getName()) + ": " + AnsiColor.Reset);
        String prescriptions = scanner.nextLine().trim();

        if (prescriptions.isEmpty()) {
            System.out.println(AnsiColor.Red + "Prescriptions cannot be empty." + AnsiColor.Reset);
            return;
        }

        try {
            patientController.addPrescriptions(patient.getId(), capitalizeFirstLetter(prescriptions));
            System.out.println(AnsiColor.Green + "Prescriptions successfully added for patient " + capitalizeFirstLetter(patient.getName()) + AnsiColor.Reset);
        } catch (Exception e) {
            System.out.println(AnsiColor.Red + "An error occurred while adding the prescriptions: " + e.getMessage() + AnsiColor.Reset);
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
            System.out.println(AnsiColor.Red + "Invalid specialization input." + AnsiColor.Reset);
            return doctors;
        }
        for (Doctor doctor : doctorsList) {
            if (capitalizeFirstLetter(doctor.getSpecialization()).equalsIgnoreCase(capitalizeFirstLetter(specialization))) {
                doctors.add(doctor);
            }
        }
        if (doctors.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctors found with specialization: " + capitalizeFirstLetter(specialization) + AnsiColor.Reset);
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

        System.out.println(AnsiColor.Cyan + "+----------------------------- Doctor Availability -----------------------------+" + AnsiColor.Reset);
        if (doctor == null) {
            System.out.printf(AnsiColor.Red + "| %-70s |\n" + AnsiColor.Reset, "Doctor with id " + doctorId + " not found.");
            System.out.println(AnsiColor.Cyan + "+-------------------------------------------------------------------------------+" + AnsiColor.Reset);
            return false;
        }

        // Check if the doctor is logged in
        if (!loggedInDoctors.contains(doctor)) {
            System.out.printf(AnsiColor.Red + "| %-70s |\n" + AnsiColor.Reset, "Doctor " + doctor.getName() + " is not currently logged in.");
            System.out.println(AnsiColor.Cyan + "+-------------------------------------------------------------------------------+" + AnsiColor.Reset);
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
                System.out.printf(AnsiColor.Red + "| %-70s |\n" + AnsiColor.Reset, "Doctor " + doctor.getName() + " already has an appointment at that time.");
                System.out.println(AnsiColor.Cyan + "+-------------------------------------------------------------------------------+" + AnsiColor.Reset);
                return false; // Doctor already has an appointment at this time
            }
        }

        System.out.printf(AnsiColor.Green + "| %-70s |\n" + AnsiColor.Reset, "Doctor " + doctor.getName() + " is available at the chosen time.");
        System.out.println(AnsiColor.Cyan + "+-------------------------------------------------------------------------------+" + AnsiColor.Reset);
        return true; // Available
    }
}