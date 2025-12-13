package com.demo.healthcare.service;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.repo.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository repository;

    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }
}
