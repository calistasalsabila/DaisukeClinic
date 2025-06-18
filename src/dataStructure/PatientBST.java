package dataStructure;

import model.Patient;

public class PatientBST {

    private Node root;

    private class Node {
        Patient patient;
        Node left;
        Node right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    public PatientBST() {
        this.root = null;
    }

    public void insertPatient(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node root, Patient patient) {
        // Jika subtree kosong, buat node baru
        if (root == null) {
            root = new Node(patient);
            return root;
        }

        // Bandingkan ID pasien dengan ID node saat ini
        if (patient.getId() < root.patient.getId()) {
            root.left = insertRec(root.left, patient); // Sisipkan di subtree kiri
        } else if (patient.getId() > root.patient.getId()) {
            root.right = insertRec(root.right, patient); // Sisipkan di subtree kanan
        } else {
            // ID sama, pasien duplikat tidak diperbolehkan (atau dapat diganti sesuai
            // kebutuhan)
            System.out.println("Patient with ID " + patient.getId() + " already exists.");
            return root; // Tidak melakukan apa-apa jika ID sudah ada
        }

        return root;
    }

    public Patient searchPatient(int id) {
        return searchRec(root, id);
    }

    private Patient searchRec(Node root, int id) {
        // Jika subtree kosong, pasien tidak ditemukan
        if (root == null) {
            return null;
        }

        // Bandingkan ID yang dicari dengan ID node saat ini
        if (id == root.patient.getId()) {
            return root.patient; // Pasien ditemukan
        } else if (id < root.patient.getId()) {
            return searchRec(root.left, id); // Cari di subtree kiri
        } else {
            return searchRec(root.right, id); // Cari di subtree kanan
        }
    }

    public void inOrderDisplay() {
        System.out.println("+--------+----------------------+------+" );
        System.out.println("| ID     | Name                 | Age  |");
        System.out.println("+--------+----------------------+------+" );
        inOrderRec(root);
        System.out.println("+--------+----------------------+------+" );
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left); // Visit left subtree
            printPatientRow(root.patient);
            inOrderRec(root.right); // Visit right subtree
        }
    }

    // Helper method to print a single patient row
    private void printPatientRow(Patient patient) {
        System.out.printf("| %-6s | %-20s | %-4s |\n", patient.getId(), patient.getName(), patient.getAge());
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void deletePatient(int id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, int id) {
        if (root == null) {
            return root;
        }

        if (id < root.patient.getId()) {
            root.left = deleteRec(root.left, id);
        } else if (id > root.patient.getId()) {
            root.right = deleteRec(root.right, id);
        } else {
            // Node with the key to be deleted found

            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor
            // (smallest in the right subtree)
            root.patient = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.patient.getId());
        }

        return root;
    }

    Patient minValue(Node root) {
        Patient minv = root.patient;
        while (root.left != null) {
            minv = root.left.patient;
            root = root.left;
        }
        return minv;
    }
}