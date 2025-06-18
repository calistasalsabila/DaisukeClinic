# 🏥 Daisuke Clinic Management System Documentation

## 📌 Overview

This program is a comprehensive clinic management system implemented in Java and utilizes various data structures. The system manages doctors, patients, and appointments with multiple functionalities for administrators, doctors, and patients.

---

## 🧱 System Architecture

### Main Components

1. **Controller** - Handles logic

   * `AdminController.java` - Manages doctors and patients
   * `DoctorController.java` - Handles doctor operations and appointments
   * `PatientController.java` - Manages patient medical records and appointments

   📄 more details → [Controller.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Controller.md)

2. **Data Structure** - Custom implementations

   * `LinkedList.java` - Generic linked list implementation
   * `PatientBST.java` - Binary Search Tree for patient medical records
   * `ManualHashMap.java` - Hash map implementation
   * `ManualQueue.java` - Queue implementation
   * `ManualPriorityQueue.java` - Priority queue implementation

   📄 more details → [DataStructure.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/DataStructure.md)

3. **Model** - Data entities

   * `Doctor.java` - Doctor information
   * `Patient.java` - Patient information
   * `Appointment.java` - Appointment details

   📄 more details → [Model.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Model.md)

4. **Menu** - User interface

   * `AdminMenu.java` - Administrator interface
   * `DokterMenu.java` - Doctor interface
   * `PatientMenu.java` - Patient interface

   📄 more details → [Menu.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Menu.md)

---

## 🌟 Main Features

### 📋 Admin Features

* Add/remove doctors and patients
* View all doctors and patients
* Search patients by ID or name
* Display patient medical records in BST order

### 👨‍⚕️ Doctor Features

* Login/logout system
* View currently logged-in doctors
* Process appointments
* Add diagnosis, treatment plans, and prescriptions

### 🧑‍⚕️ Patient Features

* Patient registration
* Create appointment
* View upcoming appointments
* View diagnosis, treatment plans, and prescriptions

---

## 🧠 Data Structure Implementation

### 1. `LinkedList.java`

* Generic linked list implementation
* Supports add, remove, contains, and get operations
* Implements Iterable for foreach support

### 2. `PatientBST.java`

* Binary Search Tree for patient medical records
* Uses patient ID as key
* Supports insert, search, delete, and in-order display

### 3. `ManualHashMap.java`

* Hash map implementation with chaining
* Default capacity 16, uses linked list for collisions

### 4. `ManualQueue.java`

* Simple queue implementation
* Supports enqueue, dequeue, peek, isEmpty, isFull

---

## 🔄 Main Workflows

### 1. Doctor Registration

* Input data: ID, name, specialization
* Validate input, save to system

### 2. Doctor Login

* Input ID, validate, record login time

### 3. Patient Registration

* Input personal data, validate
* Save to LinkedList and BST

### 4. Appointment Creation

* Choose specialization and doctor
* Select time, system checks availability
* Add to specialization queue

### 5. Appointment Processing by Doctor

* Doctor processes queue by specialization
* Add diagnosis, plan, prescription
* Save to patient medical records

📄 more details and screenshots → [workFlow.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/WorkFlow.md)

---

## ⭐ System Advantages

* Utilizes multiple data structures (BST, Queue, HashMap, LinkedList)
* Separation of concerns among controller, model, and menu
* Input validation and clean interface
* Suitable for small to medium-scale clinic system development

---

## ⚙️ Implementation Notes

* `PatientBST` uses ID as key, O(log n) search
* Doctor specializations managed with ManualHashMap
* `Appointment` uses `Comparable` for sorting
* Doctor login time recorded for reporting

---

## 👥 Team Members

1. Zuljuhdi Nurnubli - L0124083
2. Az Zahra Sam Putri - L0124089
3. Calista Salsabila - L0124092
4. Fadholi Rizqi Novandra A. - L0124098

---

## 💠 Development Stages

1. **Daisuke Clinic V0** - Calista, Zuljuhdi, Zahra, Andra (Program base: Patient - Calista, Doctor - Andra, Appointment - Zahra, BST - Zuljuhdi)
2. **Daisuke Clinic V1** - Calista (Separation into controller, menu, dataStructure, and model folders)
3. **Daisuke Clinic V2** - Calista, Zuljuhdi (Validation on Appointment)
4. **Daisuke Clinic V3** - Zahra (System language change)
5. **Daisuke Clinic V4** - Zuljuhdi (Validation and diagnosis menu migration)
6. **Daisuke Clinic V5** - Zahra, Andra (Validation on PatientController and UI output)
7. **Daisuke Clinic V6** - Calista, Zuljuhdi (ID validation and appointment system fix)
8. **Daisuke Clinic V7** - Zahra (UI addition, plan, prescription)
9. **Daisuke Clinic V8** - Calista (UI and functionality refinement)
10. **Daisuke Clinic V9** - Zahra, Calista (README.md)

---

## 🚀 How to Run the DaisukeClinic Project

Follow the steps below to run the **DaisukeClinic** Java project on your local machine.

---

### 🔁 1. Clone the Repository

Use the following command to clone the project from GitHub:

```bash
git clone https://github.com/calistasalsabila/DaisukeClinic.git
```

This will create a new folder named `DaisukeClinic` containing all the source code.

---

### 📁 2. Navigate to Project Directory

```bash
cd DaisukeClinic
```

Make sure the folder contains the following structure:

```
DaisukeClinic/
├── src/            # Source files
├── bin/            # Compiled classes (will be generated)
├── README.md
└── ...
```

---

### 💻 3. Compile Java Files (Windows PowerShell)

Compile all `.java` files from the `src` folder into the `bin` folder:

```powershell
javac -d bin -cp src $(Get-ChildItem -Recurse -Filter *.java -Path src | ForEach-Object { $_.FullName })
```

> 💡 Make sure `bin` folder exists or create it using `mkdir bin` if needed.

---

### ▶️ 4. Run the Program

Run the main class `DaisukeClinic` from the `bin` folder:

```bash
java -cp bin DaisukeClinic
```

You should now see the menu interface for the DaisukeClinic system.

---

### ✅ Done!

The system should now be running and ready to interact with. You can explore features for:

* Admin management
* Doctor and patient data
* Appointment scheduling


---

### 📝 Notes:

* `-d bin`: outputs `.class` files to the `bin` folder
* `-cp bin`: runs classes from the `bin` folder
