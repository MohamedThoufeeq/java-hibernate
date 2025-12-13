package com.demo.healthcare.controller;

import com.demo.healthcare.model.Doctor;
import com.demo.healthcare.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService service;

    @GetMapping("/get/all")
    public ResponseEntity<List<Doctor>> getAllDoctors(){
        List<Doctor> doctors = service.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatusCode.valueOf(200));
    }
}
