# 📁 Folder: `controller/`

The `controller/` folder in an MVC (Model-View-Controller) based architecture handles the logic that manages communication between the view and the data (model). In this project, the `controller/` folder contains three main files:

* `AdminController.java`
* `DoctorController.java`
* `PatientController.java`

The file `AnsiColor.java` is also included as an additional utility to enhance the appearance of log output using colors.

---

## 📄 `AdminController.java`

### 🧠 Purpose:

Manages all CRUD (Create, Read, Update, Delete) features and data management for the "Admin" entity in the application.

### 🔍 Explanation:

* Provides functionalities such as login, displaying admin data, and editing admin data.
* Connected to `AdminModel` as the data source.
* Uses the `Scanner` method for user interaction via CLI.
* Output is formatted using `AnsiColor` for easier readability.


### 📌 Notes:

Uses multiple switch-case statements for menu navigation.

---

## 📄 `DoctorController.java`

### 🧠 Purpose:

Manages user interaction with the system for doctors. Functions include login, profile display, and inputting diagnoses.

### 🔍 Explanation:

* Text-based interaction via CLI.
* Doctors log in using their ID and name.
* Diagnoses are input by the doctor through a special menu.


### 📌 Notes:

Connected to the patient and diagnosis models.

---

## 📄 `PatientController.java`

### 🧠 Purpose:

Manages user (patient) interaction such as login, viewing personal data, and viewing diagnoses given by the doctor.

### 🔍 Explanation:

* Login using patient ID and name.
* Displays diagnosis results entered by the doctor.

---

## 🛠️ `AnsiColor.java`

### 🌟 Purpose:

Utility for adding color to CLI text using ANSI escape codes. This improves readability when displaying colored text in the terminal.

### 💡 Example usage:

```java
System.out.println(AnsiColor.ANSI_GREEN + "Login Successful!" + AnsiColor.ANSI_RESET);
```

---

## 📚 Conclusion

The `controller/` folder is the main bridge between the user and the data in the system. With a CLI (Command Line Interface) approach, this system is fairly modular and easy to develop. The use of `AnsiColor` also shows attention to the user experience aspect, even though it is terminal-based.

---
