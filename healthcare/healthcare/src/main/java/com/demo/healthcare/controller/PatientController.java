package com.demo.healthcare.controller;

import com.demo.healthcare.model.Address;
import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.model.GENDER;
import com.demo.healthcare.model.Patient;
import com.demo.healthcare.repo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PatientController implements CommandLineRunner {

    private final PatientRepository prepo;
    private final MedicalRecordRepository mrRepo;
    private final DoctorRepository dRepo;
    private final MedicineRepository medRepo;
    private final PrescriptionRepository presRepo;

    @Override
    public void run(String... args) throws Exception {
        //One to One relationship
        /*MedicalRecord mr1 = mrRepo.save(MedicalRecord.builder().diagnosis("High Cold").symptoms("very low temp").build());
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
        System.out.println(patient.toString());*/

        // Many to One
        /*Doctor doctor1 = Doctor.builder().name("Alex").build();
        Doctor doctor2 = Doctor.builder().name("Kalam").build();
        Doctor doctor3 = Doctor.builder().name("Tamilan").build();
        dRepo.save(doctor1);
        dRepo.save(doctor2);
        dRepo.save(doctor3);

        MedicalRecord mr1 = mrRepo.save(MedicalRecord.builder().diagnosis("High Cold").symptoms("very low temp").build());
        Patient p = Patient.builder()
                .name("kohn").age(30).email("kohn@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr1)
                .doctor(doctor1)
                .build();
        Patient save = prepo.save(p);
        System.out.println("Saved "+save.toString());

        MedicalRecord mr2 = mrRepo.save(MedicalRecord.builder().diagnosis("Light Fever").symptoms("very Low temp").build());
        Patient p2 = Patient.builder()
                .name("bon").age(20).email("bon@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr2)
                .doctor(doctor1)
                .build();
        Patient save2 = prepo.save(p2);
        System.out.println("Saved "+save2.toString());

        MedicalRecord mr3 = mrRepo.save(MedicalRecord.builder().diagnosis("Cough").symptoms("throat pain").build());
        Patient p3 = Patient.builder()
                .name("bane").age(29).email("bane@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr3)
                .doctor(doctor3)
                .build();
        Patient save3 = prepo.save(p3);
        System.out.println("Saved "+save3.toString());*/

        // One to Many
        /* UnOptimized Version
        // Create Patients with their medical records and save it to db
        MedicalRecord mr1 = mrRepo.save(MedicalRecord.builder().diagnosis("High Cold").symptoms("very low temp").build());
        Patient p1 = Patient.builder()
                .name("kohn").age(30).email("kohn@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr1)
                .build();
        p1 = prepo.save(p1);

        MedicalRecord mr2 = mrRepo.save(MedicalRecord.builder().diagnosis("Light Fever").symptoms("very Low temp").build());
        Patient p2 = Patient.builder()
                .name("bon").age(20).email("bon@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr2)
                .build();
        p2 = prepo.save(p2);

        MedicalRecord mr3 = mrRepo.save(MedicalRecord.builder().diagnosis("Cough").symptoms("throat pain").build());
        Patient p3 = Patient.builder()
                .name("bane").age(29).email("bane@gmail.com").mobileNumber(1234567890)
                .medicalRecord(mr3)
                .build();
        p3 = prepo.save(p3);

        //Create doctors with their patients
        Doctor doctor1 = dRepo.save(Doctor.builder().name("Alex").patients(List.of(p1, p2)).build());
        Doctor doctor2 = dRepo.save(Doctor.builder().name("Kalam").patients(List.of(p3)).build());
        Doctor doctor3 = dRepo.save(Doctor.builder().name("Tamil").patients(List.of()).build());

        // Assign Doctor in Patient
        p1.setDoctor(doctor1);
        p2.setDoctor(doctor1);
        p3.setDoctor(doctor2);

        //SAVE operations
        Patient save1 = prepo.save(p1);
        System.out.println("Saved "+save1.toString());

        Patient save2 = prepo.save(p2);
        System.out.println("Saved "+save2.toString());

        Patient save3 = prepo.save(p3);
        System.out.println("Saved "+save3.toString());
        */
        //Optimized version
        /*
        // 1. Create and Save INDEPENDENT entities first (Doctors & Medical Records)
        MedicalRecord mr1 = mrRepo.save(MedicalRecord.builder().diagnosis("Cold").symptoms("Sneezing").build());
        MedicalRecord mr2 = mrRepo.save(MedicalRecord.builder().diagnosis("Fever").symptoms("Hot").build());
        MedicalRecord mr3 = mrRepo.save(MedicalRecord.builder().diagnosis("Cough").symptoms("Pain").build());

        Doctor doctor1 = dRepo.save(Doctor.builder().name("Alex").build());
        Doctor doctor2 = dRepo.save(Doctor.builder().name("Kalam").build());

        // 2. Create Patients with ALL connections set immediately
        // Notice we set .doctor() inside the builder!
        Patient p1 = Patient.builder()
                .name("Kohn")
                .age(30)
                .email("kohn@gmail.com")
                .mobileNumber(1234567890L)
                .medicalRecord(mr1) // Link MR
                .doctor(doctor1)    // Link Doctor
                .build();

        Patient p2 = Patient.builder()
                .name("Bon")
                .age(20)
                .email("bon@gmail.com")
                .mobileNumber(1234567890L)
                .medicalRecord(mr2)
                .doctor(doctor1)    // Same Doctor
                .build();

        Patient p3 = Patient.builder()
                .name("Bane")
                .age(29)
                .email("bane@gmail.com")
                .mobileNumber(1234567890L)
                .medicalRecord(mr3)
                .doctor(doctor2)    // Different Doctor
                .build();

        // 3. Save Patients ONCE
        // This generates simple INSERTs with all Foreign Keys already filled.
        prepo.saveAll(List.of(p1, p2, p3));

        System.out.println("All data saved efficiently!");
         */
        /*
        // CASCADE PERSIST
        MedicalRecord mr1 = MedicalRecord.builder().diagnosis("Cold").symptoms("Sneezing").build();
        MedicalRecord mr2 = MedicalRecord.builder().diagnosis("Fever").symptoms("Hot").build();
        MedicalRecord mr3 = (MedicalRecord.builder().diagnosis("Cough").symptoms("Pain").build());

        Doctor doctor1 = Doctor.builder().name("Alex").build();
        Doctor doctor2 = Doctor.builder().name("Kalam").build();

        Patient p1 = Patient.builder()
                .name("Kohn").age(30).email("kohn@gmail.com").mobileNumber(1234567890L)
                .medicalRecord(mr1) // Link MR
                .doctor(doctor1)    // Link Doctor
                .build();

        Patient p2 = Patient.builder().name("Bon").age(20).email("bon@gmail.com").mobileNumber(1234567890L)
                .medicalRecord(mr2)
                .doctor(doctor1)    // Same Doctor
                .build();

        Patient p3 = Patient.builder().name("Bane").age(29).email("bane@gmail.com").mobileNumber(1234567890L)
                .medicalRecord(mr3)
                .doctor(doctor2)    // Different Doctor
                .build();

        // This will throw unsaved Transient instance error because we are saving unsaved doctors & medical records to Patients.
        // in order to arrest it, mark the Doctor and MedicalRecord as Cascade in patient.
        prepo.saveAll(List.of(p1, p2, p3));
        System.out.println("All data saved efficiently!");
        */
        // CASCADE REMOVE -- skipping

        /*
        //Many to many
        Medicine m1 = Medicine.builder().name("paracetomol").build();
        Medicine m2 = Medicine.builder().name("amoxicilin").build();
        Medicine m3 = Medicine.builder().name("pentab").build();

        medRepo.saveAll(List.of(m1,m2,m3));

        Prescription p1 = Prescription.builder().medicines(List.of(m1,m3)).build();
        Prescription p2 = Prescription.builder().medicines(List.of(m2,m3)).build();

        presRepo.saveAll(List.of(p1,p2));

        //Embedded
        Doctor d1 = Doctor.builder().name("Doc").address(Address.builder().street("abc").city("NY").state("NewYork").country("US").zipcode(60000).build()).build();
        dRepo.save(d1);
        */

        //Inheritance
        Doctor alicDoc = new Doctor();
        alicDoc.setName("Alice");
        alicDoc.setAddress(new Address("123", "NYC", "NY", "US", 12345));
        alicDoc.setAge(40);
        alicDoc.setEmail("aliceDoctor@hospital.com");
        alicDoc.setMobileNumber(123456789);
        alicDoc.setGender(GENDER.MALE);
        alicDoc.setDesignation("Surgeon");

        Doctor stephenDoctor = new Doctor();
        stephenDoctor.setName("Stephen");
        stephenDoctor.setAddress(new Address("300", "Dubai City", "Dubai", "UAE", 12345));
        stephenDoctor.setAge(50);
        stephenDoctor.setEmail("stephene@hospital.com");
        stephenDoctor.setGender(GENDER.MALE);
        stephenDoctor.setDesignation("ENT");

        dRepo.saveAll(List.of(alicDoc, stephenDoctor));

        Patient patient = new Patient();
        patient.setAge(18); patient.setName("thoufeeq");
        patient.setEmail("thoufeeq@patient.com"); patient.setMobileNumber(12345678);
        patient.setGender(GENDER.MALE);
        patient.setAddress(new Address("20", "Abudhabi City", "Dubai", "UAE", 76578));
        patient = prepo.save(patient);
        patient.setDoctor(dRepo.findById(1l).get());
        patient = prepo.save(patient);



    }
}
