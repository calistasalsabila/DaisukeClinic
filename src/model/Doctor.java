package model;

import dataStructure.LinkedList; // Import LinkedList manual
import java.time.LocalDateTime;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private LocalDateTime loginTime;
    private LinkedList<Appointment> appointments; // Daftar appointment dokter

    public Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.appointments = new LinkedList<>(); // Inisialisasi daftar appointment
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    // Method untuk set login time saat login
    public void setLoginTimeNow() {
        this.loginTime = LocalDateTime.now();
    }

    public LinkedList<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", specialization='" + specialization + '\'' +
                ", loginTime=" + loginTime + // Ubah format loginTime
                '}';
    }
}