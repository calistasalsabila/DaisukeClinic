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
import controller.AnsiColor;

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
            System.out.println(AnsiColor.Yellow + "No patients are registered." + AnsiColor.Reset);
        } else {
            System.out.println(AnsiColor.Yellow + "+----+----------------------+-----+--------------------------+-------------------+");
            System.out.println("| Id | Name                 | Age | Address                  | Phone             |");
            System.out.println("+----+----------------------+-----+--------------------------+-------------------+" + AnsiColor.Reset);
            for (Patient patient : patientsList) {
                System.out.printf(AnsiColor.Yellow + "| %-2d | %-20s | %-3d | %-24s | %-17s |\n" + AnsiColor.Reset,
                        patient.getId(),
                        patient.getName(),
                        patient.getAge(),
                        patient.getAddress(),
                        patient.getPhoneNumber());
            }
            System.out.println(AnsiColor.Yellow + "+----+----------------------+-----+--------------------------+-------------------+" + AnsiColor.Reset);
        }
    }

    public Patient getPatientById(int id) {
        return patientBST.searchPatient(id); // Mengambil pasien dari BST
    }

    public void registerPatient() {
        System.out.println(AnsiColor.Yellow + "+------------------- Patient Registration -------------------+" + AnsiColor.Reset);
        int id;
        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "| Enter Patient ID          : " + AnsiColor.Reset);
                id = Integer.parseInt(scanner.nextLine());
                if (findPatientById(id) != null) {
                    System.out.println(AnsiColor.Red + "| A patient with this ID already exists.                    " + AnsiColor.Reset);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "| Invalid ID format. Please enter a valid integer.          " + AnsiColor.Reset);
            }
        }

        String name;
        while (true) {
            System.out.print(AnsiColor.Blue + "| Enter Patient Name        : " + AnsiColor.Reset);
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println(AnsiColor.Red + "| Patient name cannot be empty.                             " + AnsiColor.Reset);
            } else {
                break;
            }
        }

        int age;
        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "| Enter Patient Age         : " + AnsiColor.Reset);
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0) {
                    System.out.println(AnsiColor.Red + "| Age must be a positive number.                            " + AnsiColor.Reset);
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "| Invalid input. Age must be a number.                      " + AnsiColor.Reset);
            }
        }

        String address;
        while (true) {
            System.out.print(AnsiColor.Blue + "| Enter Patient Address     : " + AnsiColor.Reset);
            address = scanner.nextLine().trim();
            if (address.isEmpty()) {
                System.out.println(AnsiColor.Red + "| Patient address cannot be empty.                          " + AnsiColor.Reset);
            } else {
                break;
            }
        }

        String phone;
        while (true) {
            System.out.print(AnsiColor.Blue + "| Enter Patient Phone Number: " + AnsiColor.Reset);
            phone = scanner.nextLine().trim();
            if (phone.isEmpty()) {
                System.out.println(AnsiColor.Red + "| Phone number cannot be empty.                             " + AnsiColor.Reset);
            } else {
                break;
            }
        }

        Patient newPatient = new Patient(id, name, age, address, phone);
        addPatient(newPatient);
        System.out.println(AnsiColor.Green + "| Patient registered successfully!                          " + AnsiColor.Reset);
        System.out.println(AnsiColor.Yellow + "+-----------------------------------------------------------+" + AnsiColor.Reset);
    }

    public Patient findPatientById(int id) {
        return patientBST.searchPatient(id); // Mencari pasien dari BST
    }

    public void createAppointment() {
        System.out.println(AnsiColor.Yellow + "=== Create Appointment ===" + AnsiColor.Reset);

        // Validasi ID Pasien
        int patientId = validatePatientId();
        if (patientId == -1)
            return;

        // Validasi Kontroller Dokter
        if (doctorController == null) {
            System.out.println(AnsiColor.Red + "Error: DoctorController has not been set." + AnsiColor.Reset);
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

        System.out.println(AnsiColor.Green + "\n========== APPOINTMENT RECEIPT ==========" + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Appointment ID     : " + appointmentId + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Patient ID         : " + patientId + AnsiColor.Reset);
        Patient patient = getPatientById(patientId);
        if (patient != null) {
            System.out.println(AnsiColor.Blue + "Patient Name       : " + patient.getName() + AnsiColor.Reset);
        }
        System.out.println(AnsiColor.Blue + "Doctor             : " + chosenDoctor.getName() + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Specialization     : " + chosenSpecialization + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Appointment Time   : " + appointmentTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + AnsiColor.Reset);
        System.out.println(AnsiColor.Green + "==========================================\n" + AnsiColor.Reset);
    }

    // Validasi ID Pasien
    private int validatePatientId() {
        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "Enter patient ID: " + AnsiColor.Reset);
                int patientId = Integer.parseInt(scanner.nextLine());
                Patient patient = findPatientById(patientId);

                if (patient == null) {
                    System.out.println(AnsiColor.Red + "Patient not found. Please register first." + AnsiColor.Reset);
                    return -1;
                }

                return patientId;
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input. Patient ID must be a number." + AnsiColor.Reset);
            }
        }
    }

    // Pilih Spesialisasi
    private String selectSpecialization() {
        LinkedList<String> specializations = doctorController.getAllSpecializations();

        if (specializations.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctor specializations available." + AnsiColor.Reset);
            return null;
        }

        System.out.println(AnsiColor.Yellow + "Available Specializations:" + AnsiColor.Reset);
        for (int i = 0; i < specializations.size(); i++) {
            System.out.println(AnsiColor.Blue + (i + 1) + ". " + specializations.get(i) + AnsiColor.Reset);
        }

        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "Choose specialization (enter number): " + AnsiColor.Reset);
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > specializations.size()) {
                    System.out.println(AnsiColor.Red + "Invalid specialization choice." + AnsiColor.Reset);
                    continue;
                }

                return specializations.get(choice - 1);
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input. Please enter a number." + AnsiColor.Reset);
            }
        }
    }

    // Pilih Dokter
    private Doctor selectDoctor(String specialization) {
        LinkedList<Doctor> doctors = doctorController.getDoctorsBySpecialization(specialization);

        if (doctors.isEmpty()) {
            System.out.println(AnsiColor.Yellow + "No doctors available for " + specialization + " specialization." + AnsiColor.Reset);
            return null;
        }

        // Display doctors as a table
        System.out.println(AnsiColor.Yellow + "+----+----------------------+-------------------+");
        System.out.println("| No | Doctor Name          | Doctor ID         |");
        System.out.println("+----+----------------------+-------------------+" + AnsiColor.Reset);
        for (int i = 0; i < doctors.size(); i++) {
            Doctor doctor = doctors.get(i);
            System.out.printf(AnsiColor.Blue + "| %-2d | %-20s | %-17s |\n" + AnsiColor.Reset, (i + 1), doctor.getName(), doctor.getId());
        }
        System.out.println(AnsiColor.Yellow + "+----+----------------------+-------------------+" + AnsiColor.Reset);

        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "Choose doctor (enter number): " + AnsiColor.Reset);
                int choice = Integer.parseInt(scanner.nextLine());

                if (choice < 1 || choice > doctors.size()) {
                    System.out.println(AnsiColor.Red + "Invalid doctor choice." + AnsiColor.Reset);
                    continue;
                }

                return doctors.get(choice - 1);
            } catch (NumberFormatException e) {
                System.out.println(AnsiColor.Red + "Invalid input. Please enter a number." + AnsiColor.Reset);
            }
        }
    }

    // Pilih Waktu Appointment
    private LocalDateTime selectAppointmentTime(Doctor doctor) {
        while (true) {
            try {
                System.out.print(AnsiColor.Blue + "Enter appointment date and time (format: yyyy-MM-dd HH:mm): " + AnsiColor.Reset);
                String timeString = scanner.nextLine();

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime appointmentTime = LocalDateTime.parse(timeString, formatter);

                // Validasi waktu masa depan
                if (appointmentTime.isBefore(LocalDateTime.now())) {
                    System.out.println(AnsiColor.Red + "Appointment time must be in the future." + AnsiColor.Reset);
                    continue;
                }

                // Validasi ketersediaan dokter
                if (!doctorController.isDoctorAvailable(doctor.getId(), appointmentTime)) {
                    System.out.println(AnsiColor.Red + "Doctor is not available at the chosen time." + AnsiColor.Reset);
                    continue;
                }

                return appointmentTime;
            } catch (DateTimeParseException e) {
                System.out.println(AnsiColor.Red + "Invalid date and time format. Use yyyy-MM-dd HH:mm." + AnsiColor.Reset);
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
            specializationQueues.put(specialization, new ManualQueue<>(10)); // Inisialisasi ManualQueue dengan kapasitas 10
        }
        ManualQueue<Appointment> queue = specializationQueues.get(specialization);

        queue.enqueue(appointment);
        System.out.println(AnsiColor.Green + "Appointment successfully created and added to the " + specialization + " queue" + AnsiColor.Reset);
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
            System.out.println(AnsiColor.Red + "Patient with ID " + patientId + " not found." + AnsiColor.Reset);
            return;
        }
        patient.setDiagnosis(diagnosis);
    }

    public void addPlan(int patientId, String plan) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println(AnsiColor.Red + "Patient with ID " + patientId + " not found." + AnsiColor.Reset);
            return;
        }
        patient.setPlan(plan);
    }

    public void addPrescriptions(int patientId, String prescriptions) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println(AnsiColor.Red + "Patient with ID " + patientId + " not found." + AnsiColor.Reset);
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
                System.out.println(AnsiColor.Yellow + "+-----------------+------------+------------+---------------------+---------------------+");
                System.out.println("| Appointment ID  | Patient ID | Doctor ID  | Specialization      | Appointment Time    |");
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+" + AnsiColor.Reset);
                System.out.printf(AnsiColor.Blue + "| %-15d | %-10d | %-10d | %-19s | %-19s |\n" + AnsiColor.Reset,
                        nextAppointment.getId(),
                        nextAppointment.getPatientId(),
                        nextAppointment.getDoctorId(),
                        nextAppointment.getSpecialization(),
                        nextAppointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                System.out.println(AnsiColor.Yellow + "+-----------------+------------+------------+---------------------+---------------------+" + AnsiColor.Reset);
                return nextAppointment;
            } else {
                System.out.println(AnsiColor.Yellow + "No appointments in the queue for specialization " + specialization + AnsiColor.Reset);
                return null;
            }
        } else {
            System.out.println(AnsiColor.Yellow + "No queue for specialization " + specialization + AnsiColor.Reset);
            return null;
        }
    }

    public void viewUpcomingAppointments(String specialization) {
        if (specializationQueues.containsKey(specialization)) {
            ManualQueue<Appointment> queue = specializationQueues.get(specialization);
            if (!queue.isEmpty()) {
                System.out.println(AnsiColor.Yellow + "Upcoming Appointments for Specialization " + specialization + ":" + AnsiColor.Reset);
                System.out.println(AnsiColor.Yellow + "+-----------------+------------+------------+---------------------+---------------------+");
                System.out.println("| Appointment ID  | Patient ID | Doctor ID  | Specialization      | Appointment Time    |");
                System.out.println("+-----------------+------------+------------+---------------------+---------------------+" + AnsiColor.Reset);

                ManualQueue<Appointment> tempQueue = new ManualQueue<>(queue.size());
                while (!queue.isEmpty()) {
                    Appointment appointment = queue.dequeue();
                    System.out.printf(AnsiColor.Blue + "| %-15d | %-10d | %-10d | %-19s | %-19s |\n" + AnsiColor.Reset,
                            appointment.getId(),
                            appointment.getPatientId(),
                            appointment.getDoctorId(),
                            appointment.getSpecialization(),
                            appointment.getAppointmentTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                    tempQueue.enqueue(appointment);
                }
                System.out.println(AnsiColor.Yellow + "+-----------------+------------+------------+---------------------+---------------------+" + AnsiColor.Reset);

                // Restore original queue
                while (!tempQueue.isEmpty()) {
                    queue.enqueue(tempQueue.dequeue());
                }
            } else {
                System.out.println(AnsiColor.Yellow + "No upcoming appointments for specialization " + specialization + AnsiColor.Reset);
            }
        } else {
            System.out.println(AnsiColor.Yellow + "No queue for specialization " + specialization + AnsiColor.Reset);
        }
    }

    public void showDiagnosis(int patientId) {
        Patient patient = findPatientById(patientId);
        if (patient == null) {
            System.out.println(AnsiColor.Red + "Patient with ID " + patientId + " not found." + AnsiColor.Reset);
            return;
        }
        String diagnosis = patient.getDiagnosis();
        String plan = patient.getPlan();
        String prescriptions = patient.getPrescriptions();

        System.out.println(AnsiColor.Green + "\n========== DIAGNOSIS RECEIPT ==========" + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Patient ID      : " + patientId + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "Patient Name    : " + patient.getName() + AnsiColor.Reset);
        if (diagnosis == null || diagnosis.isEmpty()) {
            System.out.println(AnsiColor.Red + "Diagnosis       : No diagnosis found." + AnsiColor.Reset);
        } else {
            System.out.println(AnsiColor.Blue + "Diagnosis       : " + diagnosis + AnsiColor.Reset);
        }
        if (plan == null || plan.isEmpty()) {
            System.out.println(AnsiColor.Red + "Plan            : No plan found." + AnsiColor.Reset);
        } else {
            System.out.println(AnsiColor.Blue + "Plan            : " + plan + AnsiColor.Reset);
        }
        if (prescriptions == null || prescriptions.isEmpty()) {
            System.out.println(AnsiColor.Red + "Prescriptions   : No prescriptions found." + AnsiColor.Reset);
        } else {
            System.out.println(AnsiColor.Blue + "Prescriptions   : " + prescriptions + AnsiColor.Reset);
        }
        System.out.println(AnsiColor.Green + "========================================\n" + AnsiColor.Reset);
    }

    public void inOrderDisplay() {
        patientBST.inOrderDisplay();
    }

    public boolean isSpecializationAvailable(String specialization) {
        return specializationQueues.containsKey(specialization);
    }

}

   