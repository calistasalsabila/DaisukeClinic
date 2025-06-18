
package menu;

import controller.DoctorController;
import java.util.Scanner;
import model.Doctor;

public class DokterMenu {
    private DoctorController doctorController;
    private Scanner scanner;

// Ubah constructor untuk menerima DoctorController
    public DokterMenu(DoctorController doctorController) {
        this.doctorController = doctorController; // Gunakan controller yang diberikan
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int choice = -1;

        while (choice != 0) {
            System.out.println("+----+---------------------------+");
            System.out.println("| No |       Doctor Menu         |");
            System.out.println("+----+---------------------------+");
            System.out.println("| 1  | Login Doctor              |");
            System.out.println("| 2  | Logout Doctor             |");
            System.out.println("| 3  | View Logged-in Doctors    |");
            System.out.println("| 4  | Process Next Appointment  |");
            System.out.println("| 5  | Register Doctor           |");
            System.out.println("| 0  | Back to Main Menu         |");
            System.out.println("+----+---------------------------+");
            System.out.print("Choose: ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter number.");
                continue;
            }

            switch (choice) {
                case 1:
                    doctorController.loginDoctor();
                    break;
                    
                case 2:
                    doctorController.logoutDoctor();
                    break;
                    
                case 3:
                    doctorController.displayLoggedInDoctors();
                    break;
                case 4:
                    System.out.print("Enter specialization: ");
                    String specialization = scanner.nextLine().trim();
                    if(specialization.isEmpty()){
                        System.out.println("Specialization cannot be empty.");
                    }
                    doctorController.processAndDiagnoseNextAppointment(specialization);
                    break;
                case 5:
                    doctorController.registerDoctor();
                    break;
                
                case 0:
                    System.out.println("Back to main menu.");
                    break;
                    
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}
