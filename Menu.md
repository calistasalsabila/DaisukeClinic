# 📁 Folder: `menu/`


Dokumentasi ini menjelaskan fungsi dan struktur dari tiga file utama dalam proyek Java ini yang berkaitan dengan tampilan dan manajemen menu berdasarkan peran pengguna: Admin, Dokter, dan Pasien. Setiap file merepresentasikan menu yang berbeda sesuai dengan hak akses dan fungsionalitas pengguna.

---

## 🧑‍💼 AdminMenu.java

### 📌 Deskripsi

File ini berisi kelas AdminMenu yang menampilkan antarmuka menu untuk pengguna dengan peran Admin. Admin memiliki hak akses tertinggi dan dapat melakukan berbagai manajemen data seperti pengguna, dokter, pasien, dan lainnya.

### 🔍 Fitur Utama:

* Menampilkan menu utama untuk admin.
* Menyediakan pilihan seperti:

  * Manajemen Dokter
  * Manajemen Pasien
  * Laporan
  * Logout
* Berfungsi sebagai entry point ke berbagai fitur backend.

### 🧱 Struktur:

* showMenu(): Metode utama untuk menampilkan menu dan menangani input dari admin.
* switch-case: Digunakan untuk menangani logika berdasarkan pilihan user.

---

## 🩺 DokterMenu.java

### 📌 Deskripsi

File ini berisi kelas DokterMenu yang menampilkan menu untuk pengguna dengan peran Dokter. Menu ini memberikan akses fitur yang relevan dengan praktik dokter.

### 🔍 Fitur Utama:

* Menampilkan pilihan seperti:

  * Melihat daftar pasien
  * Menulis/melihat catatan medis
  * Logout
* Fokus pada informasi pasien dan interaksi medis.

### 🧱 Struktur:

* showMenu(): Metode utama untuk menampilkan opsi menu bagi dokter.
* Logika penanganan input menggunakan switch.

---

## 🧑‍⚕ PatientMenu.java

### 📌 Deskripsi

Kelas PatientMenu digunakan untuk memberikan antarmuka menu kepada pengguna dengan peran Pasien. Menu ini menyediakan fitur-fitur yang berhubungan dengan kebutuhan pasien.

### 🔍 Fitur Utama:

* Menampilkan opsi:

  * Melihat jadwal konsultasi
  * Melihat/mengubah profil
  * Logout
* Fungsionalitas sederhana dan terfokus pada akses informasi pribadi.

### 🧱 Struktur:

* showMenu(): Menampilkan menu dan menangani input dari pasien.
* Penanganan input dilakukan dengan switch-case.

---

##