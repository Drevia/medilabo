package com.openclassrooms.medilabo.controller;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


public interface MediLaboControllerSwagger {

    @GetMapping("/patient")
    ResponseEntity<List<Patient>> getAllPatient();

    @GetMapping("/patient/{id}")
    ResponseEntity<?> getPatientById(@PathVariable String id) throws PatientNotFoundException;

    @PostMapping("/patient")
    ResponseEntity<Patient> createPatient(@RequestBody PatientDto patientDto);

    @PatchMapping("/patient/{id}")
    ResponseEntity<?> updatePatient(@RequestBody PatientDto patientDto,@PathVariable String id) throws PatientNotFoundException;

    @DeleteMapping("/patient/{id}")
    void deletePatient(@PathVariable String id);
}
