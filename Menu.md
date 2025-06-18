# 📁 Folder: `menu/`

This documentation explains the functionality and structure of the three main files in this Java project related to menu display and management based on user roles: Admin, Doctor, and Patient. Each file represents a different menu aligned with user permissions and functionality.

---

## 🧑‍💼 `AdminMenu.java`

### 📌 Description

This file contains the `AdminMenu` class, which displays the menu interface for users with the Admin role. Admins have the highest access privileges and can manage various data such as users, doctors, and patients.

### 🔍 Key Features:

* Displays the main menu for admins.
* Provides options such as:

  * Doctor Management
  * Patient Management
  * Display patient and doctor data

### 🧱 Structure:

* `showMenu()`: Main method to display the menu and handle admin input.
* `switch-case`: Used to manage logic based on the user's selection.

---

## 🩺 `DokterMenu.java`

### 📌 Description

This file contains the `DokterMenu` class, which displays the menu for users with the Doctor role. This menu provides access to features relevant to doctors.

### 🔍 Key Features:

* Displays options such as:

  * Login Doctor
  * Logout Doctor
  * View Logged-in Doctors
  * Register Doctor
  * Back to Main Menu

### 🧱 Structure:

* `showMenu()`: Main method to display menu options for doctors.
* Input handling logic is implemented using `switch` statements.

---

## 🧑‍⚕ `PatientMenu.java`

### 📌 Description

The `PatientMenu` class provides the menu interface for users with the Patient role. This menu offers features related to patient needs.

### 🔍 Key Features:

* Displays options:

  * Register as Patient
  * Create Appointment
  * View Upcoming Appointments
  * Show Diagnosis
  * Back to Main Menu

### 🧱 Structure:

* `showMenu()`: Displays the menu and handles input from the patient.
* Input handling is done using `switch-case` logic.

---
