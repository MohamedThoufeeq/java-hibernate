package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "medical_record")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String diagnosis;
    private String symptoms;

    @OneToOne(mappedBy = "medicalRecord")
    @ToString.Exclude
    private Patient patient;
}
