package com.demo.healthcare.controller;

import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientController implements CommandLineRunner {

    private final PatientRepository repo;

    @Override
    public void run(String... args) throws Exception {
        Patient p = Patient.builder().name("john").age(30).email("john@gmail.com").mobileNumber(1234567890).build();
        Patient save = repo.save(p);
        System.out.println("Saved "+save.toString());
        Patient p2 = Patient.builder().name("don").age(20).email("don@gmail.com").mobileNumber(1234567890).build();
        Patient save2 = repo.save(p2);
        System.out.println("Saved "+save2.toString());


        Optional<Patient> findById = repo.findById(p.getId());
        if(findById.isPresent()){
            Long id = findById.get().getId();
            repo.deleteById(id);
            System.out.println("Deleted Id : "+id);
        }
    }
}
