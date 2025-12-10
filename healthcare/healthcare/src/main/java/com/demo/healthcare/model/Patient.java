package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "patient_id")
    private Long id;

    private String name;
    private int age;
    private String address;
    private String email;
    @Column(name = "mobile_number")
    private long mobileNumber;

    @OneToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    /*
    Many to One means Doctors can be many, but the patient can have only one doctor
    The first "Many" indicates the field it's indicated with, which is Doctor.
    Doctor can have many number of patients. That's why Many is indicated for Doctor in this Entity.
     */
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude
    private Doctor doctor;
}
