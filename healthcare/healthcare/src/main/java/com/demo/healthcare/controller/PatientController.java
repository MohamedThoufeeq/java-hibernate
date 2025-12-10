package com.demo.healthcare.controller;

import com.demo.healthcare.model.MedicalRecord;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repo.MedicalRecordRepository;
import com.demo.healthcare.repo.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientController implements CommandLineRunner {

    private final PatientRepository prepo;
    private final MedicalRecordRepository mrRepo;

    @Override
    public void run(String... args) throws Exception {
        //One to One relationship
        MedicalRecord mr1 = mrRepo.save(MedicalRecord.builder().diagnosis("High Cold").symptoms("very low temp").build());
        Patient p = Patient.builder()
                .name("kohn").age(30).email("kohn@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr1)
                .build();
        Patient save = prepo.save(p);
        System.out.println("Saved "+save.toString());

        MedicalRecord mr2 = mrRepo.save(MedicalRecord.builder().diagnosis("Light Fever").symptoms("very Low temp").build());
        Patient p2 = Patient.builder()
                .name("bon").age(20).email("bon@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr2)
                .build();
        Patient save2 = prepo.save(p2);
        System.out.println("Saved "+save2.toString());
        System.out.println("-------------------------------------------------------------------");
        MedicalRecord medicalRecord = mrRepo.findById(mr2.getId()).orElseThrow();
        Patient patient = medicalRecord.getPatient();
        System.out.println(medicalRecord.toString());
        System.out.println(patient.toString());

    }
}
