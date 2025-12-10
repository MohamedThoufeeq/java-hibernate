package com.demo.healthcare.dto;

import com.demo.healthcare.model.Patient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO {

    private Long id; // ID is optional for Create, mandatory for Update/Response
    private String name;
    private int age;
    private String address;
    private String email;
    private long mobileNumber;

    // --- Mapper Method: Convert Entity to DTO ---
    public static PatientDTO fromEntity(Patient patient) {
        return PatientDTO.builder()
                .id(patient.getId())
                .name(patient.getName())
                .age(patient.getAge())
                .address(patient.getAddress())
                .email(patient.getEmail())
                .mobileNumber(patient.getMobileNumber())
                .build();
    }

    // --- Mapper Method: Convert DTO to Entity ---
    public Patient toEntity() {
        return Patient.builder()
                .id(this.id) // Usually null for new creates
                .name(this.name)
                .age(this.age)
                .address(this.address)
                .email(this.email)
                .mobileNumber(this.mobileNumber)
                .build();
    }
}