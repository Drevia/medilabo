package com.openclassrooms.medilabo.controller;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;

import java.util.List;

public interface MediLaboControllerSwagger {

    List<Patient> getAllPatient();

    Patient getPatientById(String id) throws PatientNotFoundException;

    Patient createPatient(PatientDto patientDto);

    Patient updatePatient(PatientDto patientDto, String id) throws PatientNotFoundException;

    void deletePatient(String id);
}
