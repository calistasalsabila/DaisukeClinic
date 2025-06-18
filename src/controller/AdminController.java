package controller;

import dataStructure.LinkedList;
import dataStructure.PatientBST;
import model.Doctor;
import model.Patient;

public class AdminController {

    private DoctorController doctorController;
    private PatientController patientController;
    private PatientBST patientBST;

    public AdminController(DoctorController doctorController, PatientController patientController,
            PatientBST patientBST) {
        this.doctorController = doctorController;
        this.patientController = patientController;
        this.patientBST = patientBST;
    }

    public void addDoctor(int id, String name, String specialization) {
        Doctor newDoctor = new Doctor(id, name, specialization);
        doctorController.addDoctor(newDoctor);
    }

    public void removeDoctor(int id) {
        LinkedList<Doctor> doctorsList = doctorController.getDoctorsList();
        boolean removed = doctorsList.removeIf(doctor -> doctor.getId() == id);
        System.out.println("+----------------------+----------------------+");
        System.out.println("|      Doctor ID       |      Status          |");
        System.out.println("+----------------------+----------------------+");
        if (removed) {
            System.out.printf("| %-20d | %-20s |\n", id, "Deleted");
        } else {
            System.out.printf("| %-20d | %-20s |\n", id, "Not Found");
        }
        System.out.println("+----------------------+----------------------+");
    }

    public void addPatient(int id, String name, int age, String address, String phoneNumber) {
        Patient newPatient = new Patient(id, name, age, address, phoneNumber);
        patientController.addPatient(newPatient);
        patientBST.insertPatient(newPatient);
    }

    public void removePatient(int id) {
        patientController.removePatientById(id);
        patientBST.deletePatient(id);
    }

    public LinkedList<Doctor> showDoctor() {
        return doctorController.getDoctorsList();
    }

    public LinkedList<Patient> displayAllPatient() {
        return patientController.getPatientsList();
    }

    public Patient findPatientById(int id) {
        return patientController.getPatientById(id);
    }

    public void displayAllPatientsInOrder() {
        patientController.inOrderDisplay();
    }

    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctorController.getDoctorsList()) {
            if (doctor.getId() == id) {
                return doctor;
            }
        }
        return null;
    }
    public Patient getPatientById(int id) {
        return patientController.getPatientById(id); 
    }
}
