package menu;

import controller.DoctorController;
import controller.PatientController;
import java.util.Scanner;
import model.Doctor;
import model.Patient;
import controller.AdminController;
import dataStructure.LinkedList;
import dataStructure.PatientBST;

public class AdminMenu {

    private DoctorController doctorController;
    private PatientController patientController;
    private AdminController adminController;
    private PatientBST patientBST;
    private Scanner scanner;

    public AdminMenu(DoctorController doctorController, PatientController patientController,
            AdminController adminController, PatientBST patientBST) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.adminController = adminController;
        this.patientBST = patientBST;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("+----+-------------------------------+");
            System.out.println("| No |         Admin Option          |");
            System.out.println("+----+-------------------------------+");
            System.out.println("| 1  | Add Doctor                    |");
            System.out.println("| 2  | Remove Doctor                 |");
            System.out.println("| 3  | Add Patient                   |");
            System.out.println("| 4  | Remove Patient (with ID)      |");
            System.out.println("| 5  | Show Doctor                   |");
            System.out.println("| 6  | Display All Patient           |");
            System.out.println("| 7  | Search Patient (by ID)        |");
            System.out.println("| 8  | Search Patient (by Name)      |");
            System.out.println("| 0  | Back to Main Menu             |");
            System.out.println("+----+-------------------------------+");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    removeDoctor();
                    break;
                case 3:
                    addPatient();
                    break;
                case 4:
                    removePatient();
                    break;
                case 5:
                    showDoctor();
                    break;
                case 6:
                    displayAllPatient();
                    break;
                case 7:
                    searchPatientById();
                    break;
                case 8:
                    searchPatientByName();
                    break;
                case 0:
                    System.out.println("Back to main menu.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    private void addDoctor() {
        try {
            System.out.println("+----------------------+--------------------------+");
            System.out.println("|      Field           |        Input             |");
            System.out.println("+----------------------+--------------------------+");

            System.out.print("| Doctor ID            | ");
            int id = Integer.parseInt(scanner.nextLine());
            if (adminController.getDoctorById(id) != null) {
                System.out.println("Doctor ID already exists.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }

            System.out.print("| Doctor Name          | ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }

            System.out.print("| Doctor Specialization| ");
            String specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                System.out.println("Specialization cannot be empty.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }
            System.out.println("+----------------------+--------------------------+");

            adminController.addDoctor(id, name, specialization);
            System.out.println("Doctor successfully added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID must be a number.");
        }
    }

    private void removeDoctor() {
        try {
            System.out.println("+----------------------+--------------------------+");
            System.out.print("| Doctor ID to remove  | ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("+----------------------+--------------------------+");
            if (adminController.getDoctorById(id) == null) {
                System.out.println("Doctor not found.");
                return;
            }
            System.out.println("+----------------------+--------------------------+");
            System.out.print("| Confirm deletion (y/n)| ");
            String confirm = scanner.nextLine();
            System.out.println("+----------------------+--------------------------+");
            if (confirm.equalsIgnoreCase("y")) {
                adminController.removeDoctor(id);
                System.out.println("Doctor successfully removed.");
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID must be a number.");
        }
    }

    private void addPatient() {
        try {
            System.out.println("+----------------------+--------------------------+");
            System.out.println("|      Field           |        Input             |");
            System.out.println("+----------------------+--------------------------+");

            System.out.print("| Patient ID           | ");
            int id = Integer.parseInt(scanner.nextLine());
            if (patientController.getPatientById(id) != null) {
                System.out.println("Patient ID already exists.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }

            System.out.print("| Patient Name         | ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }

            System.out.print("| Patient Age          | ");
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print("| Patient Address      | ");
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Address cannot be empty.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }

            System.out.print("| Patient Phone Number | ");
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("Phone Number cannot be empty.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }
            System.out.println("+----------------------+--------------------------+");

            adminController.addPatient(id, name, age, address, phoneNumber);
            System.out.println("Patient successfully added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID and Age must be numbers.");
        }
    }

    private void removePatient() {
        try {
            System.out.println("+----------------------+--------------------------+");
            System.out.print("| Patient ID to remove | ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println("+----------------------+--------------------------+");
            if (patientController.getPatientById(id) == null) {
                System.out.println("Patient not found.");
                System.out.println("+----------------------+--------------------------+");
                return;
            }
            System.out.print("| Confirm deletion (y/n)| ");
            String confirm = scanner.nextLine();
            System.out.println("+----------------------+--------------------------+");
            if (confirm.equalsIgnoreCase("y")) {
                adminController.removePatient(id);
                System.out.println("Patient successfully removed.");
            } else {
                System.out.println("Operation cancelled.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID must be a number.");
        }
    }

    private void showDoctor() {
        LinkedList<Doctor> doctors = adminController.showDoctor();
        if (doctors.isEmpty()) {
            System.out.println("No doctors registered.");
        } else {
            System.out.println("+----+----------------------+--------------------------+");
            System.out.println("| ID | Name                 | Specialization           |");
            System.out.println("+----+----------------------+--------------------------+");
            for (Doctor doctor : doctors) {
                System.out.printf("| %-2d | %-20s | %-24s |\n", doctor.getId(), doctor.getName(), doctor.getSpecialization());
            }
            System.out.println("+----+----------------------+--------------------------+");
        }
    }

    private void displayAllPatient() {
        System.out.println("List of Patients (In-Order by ID)");
        adminController.displayAllPatientsInOrder();
    }

    private void searchPatientById() {
        try {
            System.out.print("Enter Patient ID to search: ");
            int id = Integer.parseInt(scanner.nextLine());
            Patient patient = patientController.getPatientById(id);
            if (patient != null) {
                System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
                System.out.println("| ID | Name                 | Age | Address                  | Phone Number         |");
                System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
                System.out.printf("| %-2d | %-20s | %-3d | %-24s | %-20s |\n",
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getAddress(),
                        patient.getPhoneNumber());
                System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
            } else {
                System.out.println("Patient with ID " + id + " not found.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID must be a number.");
        }
    }

    private void searchPatientByName() {
        System.out.print("Enter Patient Name to search: ");
        String name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Name cannot be empty.");
            return;
        }
        Patient patient = patientController.findPatientByName(name);
        if (patient != null) {
            System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
            System.out.println("| ID | Name                 | Age | Address                  | Phone Number         |");
            System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
            System.out.printf("| %-2d | %-20s | %-3d | %-24s | %-20s |\n",
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getAddress(),
                    patient.getPhoneNumber());
            System.out.println("+----+----------------------+-----+--------------------------+----------------------+");
        } else {
            System.out.println("Patient with name " + name + " not found.");
        }
    }
}
