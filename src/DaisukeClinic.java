import controller.DoctorController;
import controller.PatientController;
import menu.AdminMenu;
import menu.DokterMenu;
import menu.PatientMenu;
import dataStructure.PatientBST; // Import PatientBST
import controller.AdminController;
import java.util.Scanner;

public class DaisukeClinic {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Buat instance PatientBST (hanya satu instance)
        PatientBST patientBST = new PatientBST();

        // Injeksi PatientBST ke PatientController
        PatientController sharedPatientController = new PatientController(patientBST);

        // Injeksi PatientController ke DoctorController
        DoctorController sharedDoctorController = new DoctorController(sharedPatientController);

        // Set DoctorController di PatientController
        sharedPatientController.setDoctorController(sharedDoctorController);

        // Injeksi DoctorController dan PatientController ke AdminController
        AdminController adminController = new AdminController(sharedDoctorController, sharedPatientController,
                patientBST);

        // Injeksi DoctorController, PatientController, dan AdminController ke AdminMenu
        AdminMenu adminMenu = new AdminMenu(sharedDoctorController, sharedPatientController, adminController,
                patientBST);

        // Injeksi DoctorController ke DokterMenu
        DokterMenu dokterMenu = new DokterMenu(sharedDoctorController);

        // Injeksi PatientController ke PatientMenu
        PatientMenu patientMenu = new PatientMenu(sharedPatientController);

        boolean running = true;

        while (running) {
            System.out.println("+----+-----------------------+");
            System.out.println("| No |    DAISUKE CLINIC     |");
            System.out.println("+----+-----------------------+");
            System.out.println("| 1  | Login as Doctor       |");
            System.out.println("| 2  | Login as Patient      |");
            System.out.println("| 3  | Login as Admin        |");
            System.out.println("| 0  | Exit                  |");
            System.out.println("+----+-----------------------+");
            System.out.print("Enter Menu Option: ");
            int pilihan = input.nextInt();
            input.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.println("Login as Doctor...");
                    dokterMenu.showMenu();
                    break;
                case 2:
                    System.out.println("Login as Patient...");
                    patientMenu.showMenu();
                    break;

                case 3:
                    System.out.println("Login ad Admin...");
                    adminMenu.showMenu();
                    break;

                case 0:
                    System.out.println("Thank you for using Daisuke Clinic!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }

        input.close();
    }

}