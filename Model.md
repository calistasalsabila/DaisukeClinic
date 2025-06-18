# 📁 Folder: `Model/`

This documentation explains three essential files that function as the primary data representations in the clinic management system. These files store and manage information related to patients, doctors, and appointments.

---

## 🧑 `Patient.java`

### 📌 Description

This file contains the `Patient` class, which is used to represent the patient entity in the system.

### 🔍 Key Properties:

* `id`: Unique ID for each patient
* `name`: Full name of the patient
* `age`: Age of the patient
* `gender`: Gender of the patient
* `medicalHistory`: Medical history of the patient

### 🧱 Structure:

* Constructor for data initialization
* Getters and setters for each field
* Overridden `toString()` method to display patient information concisely

---

## 🩺 `Doctor.java`

### 📌 Description

The `Doctor` class represents doctors in the system, including basic information and specialization.

### 🔍 Key Properties:

* `id`: Doctor's ID
* `name`: Doctor's name
* `specialization`: Medical specialization (e.g., general, dental, etc.)

### 🧱 Structure:

* Constructor for creating doctor objects
* Getters and setters for all attributes
* Overridden `toString()` method to display doctor information

---

## 🗕️ `Appointment.java`

### 📌 Description

This class represents an appointment between a patient and a doctor at a specific date and time.

### 🔍 Key Properties:

* `appointmentId`: Unique ID for the appointment
* `patient`: Associated `Patient` object
* `doctor`: Associated `Doctor` object
* `appointmentDate`: Date and time of the appointment
* `notes`: Additional notes from the doctor

### 🧱 Structure:

* Full constructor
* Getters and setters for each attribute
* Overridden `toString()` for appointment summary

---
