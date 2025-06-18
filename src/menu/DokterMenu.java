
package menu;

import controller.AnsiColor;
import controller.DoctorController;
import java.util.Scanner;


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
            System.out.println(AnsiColor.Blue + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| No |       Doctor Menu         |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 1  | Login Doctor              |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 2  | Logout Doctor             |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 3  | View Logged-in Doctors    |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 4  | Process Next Appointment  |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue +"| 5  | Register Doctor           |"  + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "| 0  | Back to Main Menu         |" + AnsiColor.Reset);
            System.out.println(AnsiColor.Blue + "+----+---------------------------+" + AnsiColor.Reset);
            System.out.print(AnsiColor.Red + "Choose: " + AnsiColor.Reset);

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input! Please enter number." + AnsiColor.Reset);
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
                        System.out.println(AnsiColor.Red + "Specialization cannot be empty." + AnsiColor.Reset);
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
                    System.out.println(AnsiColor.Red + "Invalid choice, please try again." + AnsiColor.Reset);
            }
        }
    }
}
