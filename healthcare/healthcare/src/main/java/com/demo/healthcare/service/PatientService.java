package com.demo.healthcare.service;

import com.demo.healthcare.dto.PatientDTO;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // 1. Create Operation
    public PatientDTO createPatient(PatientDTO patientDTO) {
        // Convert DTO -> Entity
        Patient patient = patientDTO.toEntity();
        
        // Save to DB
        Patient savedPatient = patientRepository.save(patient);
        
        // Convert Entity -> DTO and return
        return PatientDTO.fromEntity(savedPatient);
    }

    // 2. Read Operation
    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        
        // Convert List<Entity> -> List<DTO>
        return patients.stream()
                .map(PatientDTO::fromEntity)
                .collect(Collectors.toList());
    }
}