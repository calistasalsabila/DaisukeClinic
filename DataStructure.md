# 📁 Folder: `dataStructure/`

This folder describes custom data structures used in the Java-based project, including `LinkedList`, `ManualHashMap`, `ManualPriorityQueue`, `Map`, and `PatientBST`.

---

## 🔗 `LinkedList.java`

### 🌟 Purpose:

Implements a basic **singly linked list** data structure.

### 📦 Components:

* `Node<T>` → inner class to store data and a pointer to the next node.
* `add(T data)` → adds data to the end of the list.
* `remove(T data)` → removes data from the list.
* `contains(T data)` → checks if the list contains the data.
* `printList()` → displays all elements.

### 🔧 Characteristics:

* Generic (`<T>`) → flexible for any data type.
* Manual memory management using the `next` pointer.

---

## 🗜️ `ManualHashMap.java`

### 🌟 Purpose:

Creates a simple implementation of the **HashMap** data structure without using Java's built-in collections.

### 📦 Components:

* `Entry<K, V>` → inner class storing key-value pairs.
* `put(K key, V value)` → adds or updates data.
* `get(K key)` → retrieves a value by key.
* `remove(K key)` → deletes a pair by key.
* `hash(K key)` → simple hash function.

### 🔧 Characteristics:

* Uses an array of `LinkedList<Entry<K, V>>` as buckets.
* Fixed size (default 10), but can be extended to dynamic sizing.

---

## 🏧 `ManualPriorityQueue.java`

### 🌟 Purpose:

Manual implementation of a **priority queue** using an array list.

### 📦 Components:

* `add(E element, int priority)` → inserts element according to priority.
* `poll()` → retrieves the highest priority element.
* `peek()` → views the highest priority element without removing it.

### 🔧 Characteristics:

* Ordered by priority value (smaller value = higher priority).
* Storage uses a manual list/array.

---

## 🗂️ `Map.java`

### 🌟 Purpose:

A simple interface for a custom Map, to be implemented by `ManualHashMap`.

### 📦 Methods:

* `put(K key, V value)`
* `get(K key)`
* `remove(K key)`

### 🔧 Characteristics:

* Does not use external libraries.
* Emphasizes interface-oriented design.

---

## 🌳 `PatientBST.java`

### 🌟 Purpose:

Binary Search Tree (**BST**) structure to store patient data by ID.

### 📦 Components:

* `Node` → stores patient objects and left & right pointers.
* `insert(Patient patient)` → inserts a patient into the tree.
* `search(String id)` → finds a patient by ID.
* `inOrderTraversal()` → displays data in sorted order.
* `delete(String id)` → deletes a patient by ID.

### 🔧 Characteristics:

* Patients are sorted by ID.
* Efficient for large-scale data searches.

---

## 📚 Conclusion

These data structures demonstrate the ability to implement core data structures from scratch without using built-in libraries. This is highly beneficial for learning:

* How data structures work internally.
* Customizing data structures to fit application needs.
* Gaining better control over performance and storage logic.

---
