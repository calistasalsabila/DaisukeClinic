package menu;

import controller.PatientController;
import model.Patient;

import java.util.Scanner;

public class PatientMenu {

    private PatientController patientController;
    private Scanner scanner;

    public PatientMenu(PatientController patientController) {
        this.patientController = patientController;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("+----+---------------------------+");
            System.out.println("| No |      Patient Menu         |");
            System.out.println("+----+---------------------------+");
            System.out.println("| 1  | Register as Patient       |");
            System.out.println("| 2  | Create Appointment        |");
            System.out.println("| 3  | View Upcoming Appointments|");
            System.out.println("| 4  | Show Diagnosis            |");
            System.out.println("| 0  | Back to Main Menu         |");
            System.out.println("+----+---------------------------+");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    createAppointment();
                    break;
                case 3:
                    viewUpcomingAppointments();
                    break;
                case 4:
                    showDiagnosis();
                    break;
                case 0:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void registerPatient() {
        patientController.registerPatient();
    }

    private void createAppointment() {
        patientController.createAppointment();
    }

    private void viewUpcomingAppointments() {
        System.out.print("Enter Specialization: ");
        String specialization = scanner.nextLine();
        patientController.viewUpcomingAppointments(specialization);
    }

    private void showDiagnosis() {
        System.out.print("Enter Patient ID: ");
        int patientId;
        try {
            patientId = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID! Please enter a valid number.");
            return;
        }

        Patient patient = patientController.getPatientById(patientId);
        if (patient != null) {
            patientController.showDiagnosis(patient.getId());
        } else {
            System.out.println("Patient not found!");
        }
    }
}