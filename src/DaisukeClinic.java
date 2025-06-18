import controller.DoctorController;
import controller.PatientController;
import controller.AnsiColor;
import menu.AdminMenu;
import menu.DokterMenu;
import menu.PatientMenu;
import dataStructure.PatientBST; 
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
            System.out.println(AnsiColor.Lime + "+----+-----------------------+");
            System.out.println("| No |    DAISUKE CLINIC     |");
            System.out.println("+----+-----------------------+");
            System.out.println("| 1  | Login as Doctor       |");
            System.out.println("| 2  | Login as Patient      |");
            System.out.println("| 3  | Login as Admin        |");
            System.out.println("| 0  | Exit                  |");
            System.out.println("+----+-----------------------+" + AnsiColor.Reset);
            System.out.print("Enter Menu Option: ");
            int pilihan = input.nextInt();
            input.nextLine(); // consume newline

            switch (pilihan) {
                case 1:
                    System.out.println(AnsiColor.Red + "Login as Doctor..." + AnsiColor.Reset);
                    dokterMenu.showMenu();
                    break;
                case 2:
                    System.out.println(AnsiColor.Red + "Login as Patient..." + AnsiColor.Reset);
                    patientMenu.showMenu();
                    break;

                case 3:
                    System.out.println(AnsiColor.Red + "Login ad Admin..." + AnsiColor.Reset);
                    adminMenu.showMenu();
                    break;

                case 0:
                    System.out.println(AnsiColor.Lime + "Thank you for using Daisuke Clinic!" + AnsiColor.Reset);
                    running = false;
                    break;
                default:
                    System.out.println(AnsiColor.Red + "Invalid option! Please try again." + AnsiColor.Reset);
            }
        }

        input.close();
    }

}