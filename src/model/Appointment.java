package model;

import java.time.LocalDateTime;

public class Appointment implements Comparable<Appointment> {
    private int id;
    private int patientId;
    private int doctorId;
    private String specialization;
    private LocalDateTime appointmentTime;

    public Appointment(int id, int patientId, int doctorId, String specialization, LocalDateTime appointmentTime) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.appointmentTime = appointmentTime;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getSpecialization() {
        return specialization;
    }

    // Tambahkan getter untuk appointmentTime
    public LocalDateTime getAppointmentTime() {
        return appointmentTime;
    }

    @Override
    public int compareTo(Appointment other) {
        return this.appointmentTime.compareTo(other.appointmentTime);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", doctorId=" + doctorId +
                ", specialization='" + specialization + '\'' +
                ", appointmentTime=" + appointmentTime +
                '}';
    }
}