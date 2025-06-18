package menu;

import controller.DoctorController;
import controller.PatientController;
import java.util.Scanner;
import model.Doctor;
import model.Patient;
import controller.AdminController;
import controller.AnsiColor;
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
            System.out.println(AnsiColor.Blue + "+----+-------------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| No |         Admin Option          |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "+----+-------------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 1  | Add Doctor                    |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 2  | Remove Doctor                 |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 3  | Add Patient                   |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 4  | Remove Patient (with ID)      |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 5  | Show Doctor                   |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 6  | Display All Patient           |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 7  | Search Patient (by ID)        |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 8  | Search Patient (by Name)      |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 0  | Back to Main Menu             |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "+----+-------------------------------+" + AnsiColor.Reset);
            System.out.print(AnsiColor.Blue + "Choose: " + AnsiColor.Reset);

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input! Please enter a number." + AnsiColor.Reset);
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
                    System.out.println(AnsiColor.Blue + "Back to main menu." + AnsiColor.Reset);
                    break;
                default:
                    System.out.println(AnsiColor.Red + "Invalid choice, please try again." + AnsiColor.Reset);
            }
        }
    }

    private void addDoctor() {
        try {
            System.out.println(AnsiColor.FluorescentYellow + "+----------------------+--------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow +"|      Field           |        Input             |" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);

            System.out.print(AnsiColor.FluorescentYellow +"| Doctor ID            | " + AnsiColor.Reset);
            int id = Integer.parseInt(scanner.nextLine());
            if (adminController.getDoctorById(id) != null) {
                System.out.println("Doctor ID already exists.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+"  + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.FluorescentYellow +"| Doctor Name          | " + AnsiColor.Reset);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.FluorescentYellow + "| Doctor Specialization| " + AnsiColor.Reset);
            String specialization = scanner.nextLine().trim();
            if (specialization.isEmpty()) {
                System.out.println("Specialization cannot be empty.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }
            System.out.println(AnsiColor.FluorescentYellow + "+----------------------+--------------------------+"  + AnsiColor.Reset);

            adminController.addDoctor(id, name, specialization);
            System.out.println("Doctor successfully added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID must be a number.");
        }
    }

    private void removeDoctor() {
        try {
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+"  + AnsiColor.Reset);
            System.out.print(AnsiColor.FluorescentYellow +"| Doctor ID to remove  | " + AnsiColor.Reset);
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
            if (adminController.getDoctorById(id) == null) {
                System.out.println("Doctor not found.");
                return;
            }
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
            System.out.print("| Confirm deletion (y/n)| ");
            String confirm = scanner.nextLine();
            System.out.println(AnsiColor.FluorescentYellow + "+----------------------+--------------------------+"  + AnsiColor.Reset);
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
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow + "|      Field           |        Input             |" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);

            System.out.print(AnsiColor.FluorescentYellow +"| Patient ID           | " + AnsiColor.Reset);
            int id = Integer.parseInt(scanner.nextLine());
            if (patientController.getPatientById(id) != null) {
                System.out.println("Patient ID already exists.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.FluorescentYellow +"| Patient Name         | " + AnsiColor.Reset);
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.FluorescentYellow +"| Patient Age          | " + AnsiColor.Reset);
            int age = Integer.parseInt(scanner.nextLine());

            System.out.print(AnsiColor.FluorescentYellow +"| Patient Address      | " + AnsiColor.Reset);
            String address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println("Address cannot be empty.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }

            System.out.print(AnsiColor.FluorescentYellow +"| Patient Phone Number | " + AnsiColor.Reset);
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("Phone Number cannot be empty.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }
            System.out.println(AnsiColor.FluorescentYellow + "+----------------------+--------------------------+" + AnsiColor.Reset);

            adminController.addPatient(id, name, age, address, phoneNumber);
            System.out.println("Patient successfully added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. ID and Age must be numbers.");
        }
    }

    private void removePatient() {
        try {
            System.out.println(AnsiColor.FluorescentYellow + "+----------------------+--------------------------+" + AnsiColor.Reset);
            System.out.print( AnsiColor.FluorescentYellow +"| Patient ID to remove | ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
            if (patientController.getPatientById(id) == null) {
                System.out.println("Patient not found.");
                System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
                return;
            }
            System.out.print("| Confirm deletion (y/n)| ");
            String confirm = scanner.nextLine();
            System.out.println(AnsiColor.FluorescentYellow +"+----------------------+--------------------------+" + AnsiColor.Reset);
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
            System.out.println(AnsiColor.FluorescentYellow + "+----+----------------------+--------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow + "| ID | Name                 | Specialization           |" + AnsiColor.Reset);
            System.out.println(AnsiColor.FluorescentYellow + "+----+----------------------+--------------------------+" + AnsiColor.Reset);
            for (Doctor doctor : doctors) {
                System.out.printf("| %-2d | %-20s | %-24s |\n", doctor.getId(), doctor.getName(), doctor.getSpecialization());
            }
            System.out.println(AnsiColor.FluorescentYellow +"+----+----------------------+--------------------------+" + AnsiColor.Reset);
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
                System.out.println(AnsiColor.BrightOrange + "+----+----------------------+-----+--------------------------+----------------------+" + AnsiColor.Reset);
                System.out.println(AnsiColor.BrightOrange +"| ID | Name                 | Age | Address                  | Phone Number         |" + AnsiColor.Reset);
                System.out.println(AnsiColor.BrightOrange +"+----+----------------------+-----+--------------------------+----------------------+"+ AnsiColor.Reset);
                System.out.printf(AnsiColor.BrightOrange + "| %-2d | %-20s | %-3d | %-24s | %-20s |\n",
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getAddress(),
                        patient.getPhoneNumber());
                System.out.println(AnsiColor.BrightOrange + "+----+----------------------+-----+--------------------------+----------------------+"+ AnsiColor.Reset);
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
            System.out.println(AnsiColor.BrightOrange + "+----+----------------------+-----+--------------------------+----------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.BrightOrange +"| ID | Name                 | Age | Address                  | Phone Number         |" + AnsiColor.Reset);
            System.out.println(AnsiColor.BrightOrange + "+----+----------------------+-----+--------------------------+----------------------+" + AnsiColor.Reset);
            System.out.printf(AnsiColor.BrightOrange +"| %-2d | %-20s | %-3d | %-24s | %-20s |\n" 
                    + AnsiColor.Reset,
                    patient.getId(),
                    patient.getName(),
                    patient.getAge(),
                    patient.getAddress(),
                    patient.getPhoneNumber());
            System.out.println(AnsiColor.BrightOrange + "+----+----------------------+-----+--------------------------+----------------------+" + AnsiColor.Reset);
        } else {
            System.out.println("Patient with name " + name + " not found.");
        }
    }
}
