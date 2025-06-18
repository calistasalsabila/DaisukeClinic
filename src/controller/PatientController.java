package controller;

import dataStructure.LinkedList;
import dataStructure.PatientBST;
import dataStructure.ManualQueue;
import model.Patient;
import model.Doctor;
import model.Appointment;
import dataStructure.ManualHashMap;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PatientController {
    private LinkedList<Patient> patientsList;
    private ManualHashMap<String, ManualQueue<Appointment>> specializationQueues;
    private DoctorController doctorController;
    private Scanner scanner;
    private PatientBST patientBST;

    public PatientController(PatientBST patientBST) {
        this.patientBST = patientBST;
        this.patientsList = new LinkedList<>();
        this.specializationQueues = new ManualHashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void setDoctorController(DoctorController doctorController) {
        this.doctorController = doctorController;
    }

    public void addPatient(Patient patient) {
        patientsList.add(patient); // Menambahkan pasien ke LinkedList
        patientBST.insertPatient(patient); // Memasukkan pasien ke dalam BST
    }

    public void removePatientById(int id) {
        Patient patientToRemove = null;
        for (Patient patient : patientsList) {
            if (patient.getId() == id) {
                patientToRemove = patient;
                break;
            }
        }
        if (patientToRemove != null) {
            patientsList.remove(patientToRemove); // Menghapus pasien dari LinkedList
        }
        patientBST.deletePatient(id); // Menghapus pasien dari BST
    }

    public Patient findPatientByName(String name) {
        for (Patient patient : patientsList) {
            if (patient.getName().equals(name)) {
                return patient;
            }
        }
        return null;
    }

    public void displayAllPatients() {
        if (patientsList.isEmpty()) {
            System.out.println("No patients are registered.");
        } else {
            System.out.println("+----+----------------------+-----+--------------------------+-------------------+");
            System.out.println("| ID | Name                 | Age | Address                  | Phone             |");
            System.out.println("+----+----------------------+-----+--------------------------+-------------------+");
            for (Patient patient : patientsList) {
                System.out.printf("| %-2d | %-20s | %-3d | %-24s | %-17s |\n",
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getAddress(),
                        patient.getPhoneNumber());
            }
            System.out.println("+----+----------------------+-----+--------------------------+-------------------+");
        }
    }

    public Patient getPatientById(int id) {
        return patientBST.searchPatient(id); // Mengambil pasien dari BST
    }

    public void registerPatient() {
    System.out.println("+------------------- Patient Registration -------------------+");
    int id;
    while (true) {
        try {
            System.out.print("| Enter Patient ID          : ");
            id = Integer.parseInt(scanner.nextLine());
            if (findPatientById(id) != null) {
                System.out.println("| A patient with this ID already exists.                    ");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("| Invalid ID format. Please enter a valid integer.          ");
        }
    }

    String name;
    while (true) {
        System.out.print("| Enter Patient Name        : ");
        name = scanner.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("| Patient name cannot be empty.                             ");
        } else {
            break;
        }
    }

    int age;
    while (true) {
        try {
            System.out.print("| Enter Patient Age         : ");
            age = Integer.parseInt(scanner.nextLine());
            if (age <= 0) {
                System.out.println("| Age must be a positive number.                            ");
                continue;
            }
            break;
        } catch (NumberFormatException e) {
            System.out.println("| Invalid input. Age must be a number.                      ");
        }
    }

    String address;
    while (true) {
        System.out.print("| Enter Patient Address     : ");
        address = scanner.nextLine().trim();
        if (address.isEmpty()) {
            System.out.println("| Patient address cannot be empty.                          ");
        } else {
            break;
        }
    }

    String phone;
    while (true) {
        System.out.print("| Enter Patient Phone Number: ");
        phone = scanner.nextLine().trim();
        if (phone.isEmpty()) {
            System.out.println("| Phone number cannot be empty.                             ");
        } else {
            break;
        }
    }

    Patient newPatient = new Patient(id, name, age, address, phone);
    addPatient(newPatient);
    System.out.println("| Patient registered successfully!                          ");
    System.out.println("+-----------------------------------------------------------+");
}


    public Patient findPatientById(int id) {
        return patientBST.searchPatient(id); // Mencari pasien dari BST
    }

    public void createAppointment() {
        System.out.println("=== Create Appointment ===");

        // Validasi ID Pasien
        int patientId = validatePatientId();
        if (patientId == -1)
            return;

        // Validasi Kontroller Dokter
        if (doctorController == null) {
            System.out.println("Error: DoctorController has not been set.");
            return;
        }

        // Pilih Spesialisasi
        String chosenSpecialization = selectSpecialization();
        if (chosenSpecialization == null)
            return;

        // Pilih Dokter
        Doctor chosenDoctor = selectDoctor(chosenSpecialization);
        if (chosenDoctor == null)
            return;

        // Pilih Waktu Appointment
        LocalDateTime appointmentTime = selectAppointmentTime(chosenDoctor);
        if (appointmentTime == null)
            return;

        // Buat ID Appointment
        int appointmentId = generateUniqueAppointmentId(chosenSpecialization);

        // Buat Objek Appointment
        Appointment appointment = new Appointment(
                appointmentId,
                patientId,
                chosenDoctor.getId(),
                chosenSpecialization,
                appointmentTime);

        // Tambahkan ke Antrian
        addAppointmentToQueue(chosenSpecialization, appointment);

        System.out.println("\n========== APPOINTMENT RECEIPT ==========");
        System.out.println("Appointment ID     : " + appointmentId);
        System.out.println("Patient ID         : " + patientId);
        Patient patient = getPatientById(patientId);
        if (patient != null) {
            System.out.println("Patient Name       : " + patient.getName());
        }
        System.out.println("Doctor             : " + chosenDoctor.getName());
        System.out.println("Specialization     : " + chosenSpecialization);
        System.out.println("Appointment Time   : " + appointmentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        System.out.println("==========================================\n");
    }

    // Validasi ID Pasien
    private int validatePatientId() {
        while (true) {
            try {
                System.out.print("Enter patient ID: ");
                int patientId = Integer.parseInt(scanner.nextLine());
                Patient patient = findPatientById(patientId);

                if (patient == null) {
                    System.out.println("Patient not found. Please register first.");
                    return -1;
                }

                return patientId;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Patient ID must be a number.");
            }
        }
    }

    // Pilih Spesialisasi
    private String selectSpecialization() {
        LinkedList<String> specializations = doctorController.getAllSpecializations();

        if (specializations.isEmpty()) {
            System.out.println("No doctor specializations available.");
            return null;
        }

        System.out.println("Available Specializations:");
        for (int i = 0; i < specializations.size(); i++) {
            System.out.println((i + 1) + ". " + specializations.get(i));
        }

        while (true) {
            try {
                System.out.print("Choose specialization (enter number): ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > specializations.size()) {
                    System.out.println("Invalid specialization choice.");
                    continue;
                }

                return specializations.get(choice - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Pilih Dokter
    private Doctor selectDoctor(String specialization) {
        LinkedList<Doctor> doctors = doctorController.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println("No doctors available for " + specialization + " specialization.");
            return null;
        }

        // Display doctors as a table
        System.out.println("+----+----------------------+-------------------+");
        System.out.println("| No | Doctor Name          | Doctor ID         |");
        System.out.println("+----+----------------------+-------------------+");
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            System.out.printf("| %-2d | %-20s | %-17s |\n", (i + 1), doctor.getName(), doctor.getId());
        }
        System.out.println("+----+----------------------+-------------------+");

        while (true) {
            try {
                System.out.print("Choose doctor (enter number): ");
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > doctors.size()) {
                    System.out.println("Invalid doctor choice.");
                    continue;
                }

                return doctors.get(choice - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    // Pilih Waktu Appointment
    private LocalDateTime selectAppointmentTime(Doctor doctor) {
        while (true) {
            try {
                System.out.print("Enter appointment date and time (format: yyyy-MM-dd HH:mm): ");
                String timeString = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime appointmentTime = LocalDateTime.parse(timeString, formatter);

                // Validasi waktu masa depan
                if (appointmentTime.isBefore(LocalDateTime.now())) {
                    System.out.println("Appointment time must be in the future.");
                    continue;
                }

                // Validasi ketersediaan dokter
                if (!doctorController.isDoctorAvailable(doctor.getId(), appointmentTime)) {
                    System.out.println("Doctor is not available at the chosen time.");
                    continue;
                }

                return appointmentTime;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date and time format. Use yyyy-MM-dd HH:mm.");
            }
        }
    }

    // Generate ID Appointment Unik
    private int generateUniqueAppointmentId(String specialization) {
        ManualQueue<Appointment> queue = specializationQueues.get(specialization);

        if (queue == null) {
            // Buat queue baru jika belum ada
            queue = new ManualQueue<>(100);
            specializationQueues.put(specialization, queue);
        }

        // ID berdasarkan ukuran queue untuk spesialisasi ini
        return queue.size() + 1;
    } ///////


    // Metode untuk menambahkan appointment ke antrian spesialisasi
    public void addAppointmentToQueue(String specialization, Appointment appointment) {
        if (!specializationQueues.containsKey(specialization)) {
            specializationQueues.put(specialization, new ManualQueue<>(10)); // Inisialisasi ManualQueue dengan
                                                                             // kapasitas 10
        }
        ManualQueue<Appointment> queue = specializationQueues.get(specialization);

        queue.enqueue(appointment);
        System.out.println("Appointment successfully created and added to the " + specialization + " queue");
    }

    public LinkedList<Patient> getPatientsList() {
        return patientsList;
    }

    public ManualHashMap<String, ManualQueue<Appointment>> getSpecializationQueues() {
        return specializationQueues;
    }

    public void addDiagnosis(int patientId, String diagnosis) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }
        patient.setDiagnosis(diagnosis);
    }

    public void addPlan(int patientId, String plan) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }
        patient.setPlan(plan);
    }

    public void addPrescriptions(int patientId, String prescriptions) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }
        patient.setPrescriptions(prescriptions);
    }

    public Appointment processNextAppointment(String specialization) {
        if (specializationQueues.containsKey(specialization)) {
            ManualQueue<Appointment> queue = specializationQueues.get(specialization);
            Appointment nextAppointment = queue.dequeue(); // Menggunakan dequeue dari ManualQueue
            if (nextAppointment != null) {
                // Print appointment as a table
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");
                System.out.println("| Appointment ID  | Patient ID | Doctor ID  | Specialization      | Appointment Time    |");
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");
                System.out.printf("| %-15d | %-10d | %-10d | %-19s | %-19s |\n",
                        nextAppointment.getId(),
                        nextAppointment.getPatientId(),
                        nextAppointment.getDoctorId(),
                        nextAppointment.getSpecialization(),
                        nextAppointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");
                return nextAppointment;
            } else {
                System.out.println("No appointments in the queue for specialization " + specialization);
                return null;
            }
        } else {
            System.out.println("No queue for specialization " + specialization);
            return null;
        }
    }

    public void viewUpcomingAppointments(String specialization) {
        if (specializationQueues.containsKey(specialization)) {
            ManualQueue<Appointment> queue = specializationQueues.get(specialization);
            if (!queue.isEmpty()) {
                System.out.println("Upcoming Appointments for Specialization " + specialization + ":");
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");
                System.out.println("| Appointment ID  | Patient ID | Doctor ID  | Specialization      | Appointment Time    |");
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");

                ManualQueue<Appointment> tempQueue = new ManualQueue<>(queue.size());
                while (!queue.isEmpty()) {
                    Appointment appointment = queue.dequeue();
                    System.out.printf("| %-15d | %-10d | %-10d | %-19s | %-19s |\n",
                            appointment.getId(),
                            appointment.getPatientId(),
                            appointment.getDoctorId(),
                            appointment.getSpecialization(),
                            appointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    tempQueue.enqueue(appointment);
                }
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+");

                // Restore original queue
                while (!tempQueue.isEmpty()) {
                    queue.enqueue(tempQueue.dequeue());
                }
            } else {
                System.out.println("No upcoming appointments for specialization " + specialization);
            }
        } else {
            System.out.println("No queue for specialization " + specialization);
        }
    }

    public void showDiagnosis(int patientId) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println("Patient with ID " + patientId + " not found.");
            return;
        }
        String diagnosis = patient.getDiagnosis();
        String plan = patient.getPlan();
        String prescriptions = patient.getPrescriptions();

        System.out.println("\n========== DIAGNOSIS RECEIPT ==========");
        System.out.println("Patient ID      : " + patientId);
        System.out.println("Patient Name    : " + patient.getName());
        if (diagnosis == null || diagnosis.isEmpty()) {
            System.out.println("Diagnosis       : No diagnosis found.");
        } else {
            System.out.println("Diagnosis       : " + diagnosis);
        }
        if (plan == null || plan.isEmpty()) {
            System.out.println("Plan            : No plan found.");
        } else {
            System.out.println("Plan            : " + plan);
        }
        if (prescriptions == null || prescriptions.isEmpty()) {
            System.out.println("Prescriptions   : No prescriptions found.");
        } else {
            System.out.println("Prescriptions   : " + prescriptions);
        }
        System.out.println("========================================\n");
    }

    public void inOrderDisplay() {
        patientBST.inOrderDisplay();
    }

public boolean isSpecializationAvailable(String specialization) {
    return specializationQueues.containsKey(specialization);
}

}