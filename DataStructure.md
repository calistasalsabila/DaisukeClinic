# 📁 Folder: `dataStructure/`


Folder ini menjelaskan struktur data buatan sendiri (custom) yang digunakan dalam proyek berbasis Java, termasuk `LinkedList`, `ManualHashMap`, `ManualPriorityQueue`, `Map`, dan `PatientBST`.

---

## 🔗 `LinkedList.java`

### 🎯 Tujuan:

Membuat implementasi dasar struktur data **Linked List** satu arah.

### 📦 Komponen:

* `Node<T>` → kelas dalam (inner class) untuk menyimpan data dan pointer ke node berikutnya.
* `add(T data)` → menambah data ke akhir list.
* `remove(T data)` → menghapus data dari list.
* `contains(T data)` → mengecek apakah data ada.
* `printList()` → menampilkan semua elemen.

### 🔧 Karakteristik:

* Generik (`<T>`) → fleksibel untuk semua tipe data.
* Pendekatan manual untuk pengelolaan memori via pointer `next`.

---

## 🗺️ `ManualHashMap.java`

### 🎯 Tujuan:

Membuat implementasi sederhana struktur data **HashMap** tanpa menggunakan koleksi bawaan Java.

### 📦 Komponen:

* `Entry<K, V>` → inner class menyimpan pasangan key-value.
* `put(K key, V value)` → menambahkan atau mengupdate data.
* `get(K key)` → mengambil value berdasarkan key.
* `remove(K key)` → menghapus pasangan berdasarkan key.
* `hash(K key)` → fungsi hash sederhana.

### 🔧 Karakteristik:

* Menggunakan array dari `LinkedList<Entry<K, V>>` sebagai bucket.
* Ukuran tetap (default 10), bisa dikembangkan menjadi dinamis.

---

## 🏗️ `ManualPriorityQueue.java`

### 🎯 Tujuan:

Implementasi antrian prioritas (**Priority Queue**) manual berbasis array list.

### 📦 Komponen:

* `add(E element, int priority)` → memasukkan elemen sesuai prioritas.
* `poll()` → mengambil elemen dengan prioritas tertinggi.
* `peek()` → melihat elemen tertinggi tanpa menghapus.

### 🔧 Karakteristik:

* Disusun berdasarkan nilai prioritas (semakin kecil, semakin tinggi).
* Penyimpanan menggunakan list/array manual.

---

## 🗂️ `Map.java`

### 🎯 Tujuan:

Interface sederhana untuk custom Map agar bisa diimplementasikan oleh `ManualHashMap`.

### 📦 Method:

* `put(K key, V value)`
* `get(K key)`
* `remove(K key)`

### 🔧 Karakteristik:

* Tidak menggunakan library eksternal.
* Menjaga prinsip interface-oriented design.

---

## 🌳 `PatientBST.java`

### 🎯 Tujuan:

Struktur data pohon biner pencarian (**Binary Search Tree**) untuk menyimpan data pasien berdasarkan ID.

### 📦 Komponen:

* `Node` → menyimpan objek pasien dan pointer kiri & kanan.
* `insert(Patient patient)` → memasukkan pasien ke tree.
* `search(String id)` → mencari pasien berdasarkan ID.
* `inOrderTraversal()` → menampilkan data secara terurut.
* `delete(String id)` → menghapus pasien berdasarkan ID.

### 🔧 Karakteristik:

* Setiap pasien diurutkan berdasar ID.
* Efisien untuk pencarian data besar.

---

## 📚 Kesimpulan

Struktur data ini memperlihatkan kemampuan implementasi dasar struktur data penting tanpa menggunakan library built-in. Ini sangat berguna untuk belajar:

* Cara kerja internal struktur data.
* Menyesuaikan struktur data sesuai kebutuhan aplikasi.
* Meningkatkan kontrol terhadap performa dan logika penyimpanan.


