# 🏥 Dokumentasi Sistem Manajemen Clinic Daisuke

## 📌 Gambaran Umum

Program ini adalah sistem manajemen klinik komprehensif yang diimplementasikan dalam Java dan menggunakan berbagai struktur data. Sistem ini mengelola dokter, pasien, dan janji temu dengan berbagai fungsionalitas untuk administrator, dokter, dan pasien.

---

## 🧱 Arsitektur Sistem

### Komponen Utama

1. **Controller** - Menangani logika

   * `AdminController.java` - Mengelola dokter dan pasien
   * `DoctorController.java` - Menangani operasi dokter dan janji temu
   * `PatientController.java` - Mengelola rekam medis pasien dan janji temu

   📄 lebih lengkap → [Controller.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Controller.md)


2. **Struktur Data** - Implementasi kustom

   * `LinkedList.java` - Implementasi linked list generik
   * `PatientBST.java` - Binary Search Tree untuk rekam medis pasien
   * `ManualHashMap.java` - Implementasi hash map
   * `ManualQueue.java` - Implementasi antrian
   * `ManualPriorityQueue.java` - Implementasi antrian prioritas

   📄 lebih lengkap → [DataStructure.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/DataStructure.md)

3. **Model** - Entitas data

   * `Doctor.java` - Informasi dokter
   * `Patient.java` - Informasi pasien
   * `Appointment.java` - Detail janji temu

   📄 lebih lengkap →  [Model.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Model.md)

4. **Menu** - Interface pengguna

   * `AdminMenu.java` - Interface administrator
   * `DokterMenu.java` - Interface dokter
   * `PatientMenu.java` - Interface pasien

   📄 lebih lengkap → [Menu.md](https://github.com/calistasalsabila/DaisukeClinic/blob/main/Menu.md)
---

## 🎯 Fitur Utama

### 📋 Fitur Admin atau Administrator

![Menu Admin](images/Admin_menu.png)

* Menambah/menghapus dokter dan pasien
* Melihat semua dokter dan pasien
* Mencari pasien berdasarkan ID atau nama
* Menampilkan rekam medis pasien dalam urutan BST

### 👨‍⚕️ Fitur Dokter

![Menu Dokter](images/Doctor_menu.png)

* Sistem login/logout
* Melihat dokter yang sedang login
* Memproses janji temu
* Menambahkan diagnosis, rencana perawatan, dan resep

### 🧑‍⚕️ Fitur Pasien

![Menu Pasien](images/Patient_menu.png)

* Pendaftaran pasien
* Pembuatan janji temu
* Melihat janji temu yang akan datang
* Melihat diagnosis, rencana penanganan, dan resep

---

## 🧠 Implementasi Struktur Data

### 1. `LinkedList.java`

* Implementasi linked list generik
* Mendukung operasi add, remove, contains, dan get
* Mengimplementasikan Iterable untuk dukungan foreach

### 2. `PatientBST.java`

* Binary Search Tree untuk rekam medis pasien
* Menggunakan ID pasien sebagai kunci
* Operasi insert, search, delete, dan inOrder display

### 3. `ManualHashMap.java`

* Implementasi hash map dengan chaining
* Kapasitas default 16, menggunakan linked list untuk kolisi

### 4. `ManualQueue.java`

* Implementasi antrian sederhana
* Mendukung enqueue, dequeue, peek, isEmpty, isFull

---

## 🔄 Alur Kerja Utama

### 1. Registrasi Dokter

* Input data: ID, nama, spesialis
* Validasi input, simpan ke sistem

### 2. Login Dokter

* Input ID, validasi, rekam waktu login

### 3. Pendaftaran Pasien

* Input data pribadi, validasi
* Simpan ke LinkedList dan BST

### 4. Pembuatan Janji Temu

* Pilih spesialisasi dan dokter
* Pilih waktu, sistem cek ketersediaan
* Tambahkan ke antrian spesialisasi

### 5. Proses Janji Temu oleh Dokter

* Dokter memproses antrian sesuai spesialisasi
* Tambah diagnosis, plan, resep
* Simpan ke rekam medis pasien

---

## ⭐ Keunggulan Sistem

* Penggunaan banyak struktur data (BST, Queue, HashMap, LinkedList)
* Pemisahan tanggung jawab antara controller, model, dan menu
* Validasi input dan tampilan antarmuka yang rapi
* Cocok untuk pengembangan sistem klinik skala kecil hingga menengah

---

## ⚙️ Catatan Implementasi

* `PatientBST` menggunakan ID sebagai kunci, pencarian O(log n)
* Spesialisasi dokter dikelola dengan ManualHashMap
* `Appointment` menggunakan `Comparable` untuk pengurutan
* Waktu login dokter dicatat untuk pelaporan

---

## 👥 Anggota Kelompok

1. Zuljuhdi Nurnubli - L0124083
2. Az Zahra Sam Putri - L0124089
3. Calista Salsabila - L0124092
4. Fadholi Rizqi Novandra A. - L0124098

---

## 🛠️ Tahap Pengembangan

1. **Daisuke Clinic V0** - Calista, Zuljuhdi, Zahra, Andra (Dasar program: Patient - Calista, Doctor - Andra, Appointment - Zahra, BST - Zuljuhdi)
2. **Daisuke Clinic V1** - Calista (Pemecahan Menu controller, perapian struktur)
3. **Daisuke Clinic V2** - Calista (Penambahan Appointment)
4. **Daisuke Clinic V3** - Calista, Zuljuhdi (Validasi pada Appointment)
5. **Daisuke Clinic V4** - Zahra (Perubahan bahasa sistem)
6. **Daisuke Clinic V5** - Zuljuhdi (Validasi dan perpindahan menu diagnosis)
7. **Daisuke Clinic V6** - Zahra, Andra (Validasi PatientController dan UI output)
8. **Daisuke Clinic V7** - Calista, Zuljuhdi (Validasi ID dan pembetulan sistem appointment)
9. **Daisuke Clinic V8** - Zahra (Penambahan UI, plan, prescription)
10. **Daisuke Clinic V9** - Calista (Penyempurnaan tampilan dan fungsionalitas)
11. **Daisuke Clinic V10** - Zahra (README.md)   
 
---

## 🚀 Cara Menjalankan

1. Compile semua file Java di folder src
2. Jalankan Main.java

bash
javac src/**/*.java
java -cp src Main


---
