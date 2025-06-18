# 📁 Folder: `Model/`


Dokumentasi ini menjelaskan tiga file penting yang berfungsi sebagai representasi data utama dalam sistem manajemen klinik. Ketiga file ini digunakan untuk menyimpan dan mengelola informasi yang berkaitan dengan pasien, dokter, dan janji temu (appointment).

---

## 🧑 Patient.java

### 📌 Deskripsi

File ini berisi kelas Patient yang digunakan untuk merepresentasikan entitas pasien dalam sistem.

### 🔍 Properti Utama:

* id: ID unik untuk setiap pasien
* name: Nama lengkap pasien
* age: Umur pasien
* gender: Jenis kelamin
* medicalHistory: Riwayat medis pasien

### 🧱 Struktur:

* Constructor untuk inisialisasi data
* Getter dan Setter untuk setiap field
* Override toString() untuk menampilkan informasi pasien secara ringkas

---

## 🩺 Doctor.java

### 📌 Deskripsi

Kelas Doctor merepresentasikan dokter dalam sistem, termasuk informasi dasar dan spesialisasinya.

### 🔍 Properti Utama:

* id: ID dokter
* name: Nama dokter
* specialization: Spesialisasi medis (misalnya, umum, gigi, dll)

### 🧱 Struktur:

* Constructor untuk pembuatan objek dokter
* Getter dan Setter untuk semua atribut
* Override toString() untuk menampilkan informasi dokter

---

## 📅 Appointment.java

### 📌 Deskripsi

Kelas ini merepresentasikan janji temu (appointment) antara pasien dan dokter pada waktu tertentu.

### 🔍 Properti Utama:

* appointmentId: ID unik janji temu
* patient: Objek Patient terkait
* doctor: Objek Doctor terkait
* appointmentDate: Tanggal dan waktu janji temu
* notes: Catatan tambahan dari dokter

### 🧱 Struktur:

* Constructor lengkap
* Getter dan Setter untuk setiap atribut
* Override toString() untuk ringkasan janji temu

---