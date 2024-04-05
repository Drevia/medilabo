package com.openclassrooms.medilabo.controller;

import com.openclassrooms.medilabo.exception.PatientNotFoundException;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.service.MediLaboService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MediLaboController implements MediLaboControllerSwagger {

    @Autowired
    private MediLaboService mediLaboService;

    private final Logger logger = LoggerFactory.getLogger(MediLaboController.class);

    public List<Patient> getAllPatient() {
        logger.info("find all patient");
        return mediLaboService.findAllPatient();
    }

    public ResponseEntity<?> getPatientById(String id) {
        try {
            return ResponseEntity.ok(mediLaboService.findPatientById(id));
        } catch (PatientNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        }
    }

    public Patient createPatient(PatientDto patientDto) {
        return mediLaboService.savePatient(patientDto);
    }

    public Patient updatePatient(PatientDto patientDto, String id) throws PatientNotFoundException {
        return mediLaboService.updatePatient(patientDto, id);
    }

    public void deletePatient(String id) {
        logger.info("delete patient with id: {}", id);
        mediLaboService.deletePatient(id);
    }
}
