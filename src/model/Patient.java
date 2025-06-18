
package model;

public class Patient {
    
    private Patient next;
    private int id;
    private String name;
    private int age;
    private String address;
    private String phoneNumber;
    private String diagnosis; // diisi setelah periksa
    private String plan; // diisi setelah periksa
    private String prescriptions; // diisi setelah periksa


    public Patient(int id, String name, int age, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.diagnosis = "";
        this.plan = "";
        this.prescriptions = "";
        this.next = null;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
    
    public String getPlan() {
        return plan;
    }
    public void setPlan(String plan) {
        this.plan = plan;
    }
    
    public String getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(String prescriptions) {
        this.prescriptions = prescriptions;
    }

    // Untuk tampil
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age +
                ", Address: " + address + ", Phone: " + phoneNumber +
                ", Diagnosis: " + (diagnosis.isEmpty() ? "Belum diperiksa" : diagnosis) +
                ", Plan: " + (plan.isEmpty() ? "Belum diperiksa" : plan) +
                ", Prescriptions: " + (prescriptions.isEmpty() ? "Belum diperiksa" : prescriptions);

    }
    
}
