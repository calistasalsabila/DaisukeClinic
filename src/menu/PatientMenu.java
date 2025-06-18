package menu;

import controller.AnsiColor;
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
            System.out.println(AnsiColor.Green + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| No |      Patient Menu         |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| 1  | Register as Patient       |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| 2  | Create Appointment        |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| 3  | View Upcoming Appointments|" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| 4  | Show Diagnosis            |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "| 0  | Back to Main Menu         |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Green + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.print(AnsiColor.BrightBlue + "Choose: " + AnsiColor.Reset);

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input! Please enter a number." + AnsiColor.Reset);
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
                    System.out.println(AnsiColor.Red + "Invalid choice! Please try again." + AnsiColor.Reset);
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
            System.out.println(AnsiColor.Red + "Invalid ID! Please enter a valid number." + AnsiColor.Reset);
            return;
        }

        Patient patient = patientController.getPatientById(patientId);
        if (patient != null) {
            patientController.showDiagnosis(patient.getId());
        } else {
            System.out.println(AnsiColor.Red + "Patient not found!");
        }
    }
}