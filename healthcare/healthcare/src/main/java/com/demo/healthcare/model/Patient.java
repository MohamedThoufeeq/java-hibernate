package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
//@Builder
//@Table(name = "patient")
public class Patient extends Person {

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    /*
    Many to One means Doctors can be many, but the patient can have only one doctor
    The first "Many" indicates the field it's indicated with, which is Doctor.
    Doctor can have many number of patients. That's why Many is indicated for Doctor in this Entity.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "doctor_id")
    @ToString.Exclude @EqualsAndHashCode.Exclude
    private Doctor doctor;

    @CreationTimestamp
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private LocalDateTime updatedAt;

    //will execute only when INSERT query is executed
    @PrePersist
    private void updateRole(){
        this.setRole(Role.PATIENT);
    }
}
