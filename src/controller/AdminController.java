package controller;

import dataStructure.LinkedList;
import dataStructure.PatientBST;
import model.Doctor;
import model.Patient;
import controller.AnsiColor;

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
        System.out.println(AnsiColor.Blue + "+----------------------+----------------------+" + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "| Doctor id            | Status               |" + AnsiColor.Reset);
        System.out.println(AnsiColor.Blue + "+----------------------+----------------------+" + AnsiColor.Reset);
        if (removed) {
            System.out.printf("| %-20d | %s%-20s%s |\n", id, AnsiColor.Green, "Deleted", AnsiColor.Reset);
        } else {
            System.out.printf("| %-20d | %s%-20s%s |\n", id, AnsiColor.Red, "Not found", AnsiColor.Reset);
        }
        System.out.println(AnsiColor.Blue + "+----------------------+----------------------+" + AnsiColor.Reset);
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
