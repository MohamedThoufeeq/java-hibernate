package com.demo.healthcare.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /*
    Doctor can have many patients
     */
    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<Patient> patients;
}
