# 📁 Folder: `controller/`

Folder `controller/` dalam arsitektur berbasis MVC (Model-View-Controller) bertugas menangani logika yang mengatur komunikasi antara tampilan (view) dan data (model). Di proyek ini, folder `controller/` berisi tiga file utama:

* `AdminController.java`
* `DoctorController.java`
* `PatientController.java`

File `AnsiColor.java` juga termasuk sebagai utilitas tambahan untuk memperindah tampilan log output dengan warna.

---

## 📄 `AdminController.java`

### 🧠 Tujuan:

Mengatur seluruh fitur CRUD (Create, Read, Update, Delete) serta manajemen data untuk entitas "Admin" dalam aplikasi.

### 🔍 Penjelasan:

* Menyediakan fungsionalitas seperti login, menampilkan data admin, dan edit data admin.
* Terhubung dengan `AdminModel` sebagai sumber data.
* Menggunakan metode `Scanner` untuk interaksi user via CLI.
* Output diformat dengan `AnsiColor` untuk mempermudah pembacaan.

### ⚙️ Fitur-fitur Utama:

* `loginAdmin()` → login menggunakan username & password.
* `menuAdmin()` → menampilkan menu utama khusus admin.
* `lihatDataDokter()`, `lihatDataPasien()` → menampilkan data dari entitas lain.
* `hapusDataDokter()`, `hapusDataPasien()` → hapus data berdasarkan ID.

### 📌 Catatan:

Menggunakan banyak switch-case sebagai navigasi menu.

---

## 📄 `DoctorController.java`

### 🧠 Tujuan:

Mengelola interaksi user dengan sistem pada bagian dokter. Fungsinya seperti login, menampilkan profil, dan input diagnosis.

### 🔍 Penjelasan:

* Interaksi berbasis teks via CLI.
* Dokter login berdasarkan ID dan nama.
* Diagnosis pasien diinput oleh dokter melalui menu khusus.

### ⚙️ Fitur-fitur Utama:

* `loginDokter()` → login dokter.
* `menuDokter()` → menu setelah login.
* `tambahDiagnosis()` → dokter bisa memasukkan diagnosis pasien berdasarkan ID pasien.

### 📌 Catatan:

Terkoneksi dengan model pasien dan diagnosis.

---

## 📄 `PatientController.java`

### 🧠 Tujuan:

Mengatur interaksi user (pasien) seperti login, melihat data pribadi, serta diagnosis yang telah diberikan dokter.

### 🔍 Penjelasan:

* Login menggunakan ID dan nama pasien.
* Menampilkan hasil diagnosis yang telah dimasukkan oleh dokter.

### ⚙️ Fitur-fitur Utama:

* `loginPasien()` → login pasien.
* `menuPasien()` → menu utama setelah login.
* `lihatDataPribadi()` → melihat informasi pasien.
* `lihatDiagnosis()` → melihat daftar diagnosis.

---

## 🛠️ `AnsiColor.java`

### 🎯 Tujuan:

Utilitas untuk menambahkan warna pada teks CLI menggunakan ANSI escape codes. Ini meningkatkan keterbacaan saat menampilkan teks berwarna-warni di terminal.

### 💡 Contoh penggunaan:

```java
System.out.println(AnsiColor.ANSI_GREEN + "Login Berhasil!" + AnsiColor.ANSI_RESET);
```

---

## 📚 Kesimpulan

Folder `controller/` adalah jembatan utama antara pengguna (user) dan data dalam sistem. Dengan pendekatan CLI (Command Line Interface), sistem ini cukup modular dan mudah dikembangkan. Penggunaan `AnsiColor` juga menunjukkan perhatian pada aspek user experience walau hanya berbasis terminal.

---

> 📌 **Rekomendasi ke depan**:
>
> * Terapkan validasi input agar lebih aman.
> * Pisahkan logika tampilan dan logika bisnis ke helper/helper view.
> * Implementasi exception handling lebih dalam untuk error yang lebih spesifik dan user-friendly.
