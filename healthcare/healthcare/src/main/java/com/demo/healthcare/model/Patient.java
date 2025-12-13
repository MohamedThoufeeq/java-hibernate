package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class Patient extends Person {

    public Patient(MedicalRecord medicalRecord, Doctor doctor) {
        this.medicalRecord = medicalRecord;
        this.doctor = doctor;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medical_record_id")
    @Getter @Setter
    private MedicalRecord medicalRecord;

    /*
    Many to One means Doctors can be many, but the patient can have only one doctor
    The first "Many" indicates the field it's indicated with, which is Doctor.
    Doctor can have many number of patients. That's why Many is indicated for Doctor in this Entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doctor_id")
    @Getter @Setter
    private Doctor doctor;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    //will execute only when INSERT query is executed
    @PrePersist
    private void updateRole(){
        this.setRole(Role.PATIENT);
    }
}
