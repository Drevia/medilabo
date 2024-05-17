package com.openclassrooms.medilabo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.medilabo.config.JwtAuthenticationFilter;
import com.openclassrooms.medilabo.model.Gender;
import com.openclassrooms.medilabo.model.Patient;
import com.openclassrooms.medilabo.model.PatientDto;
import com.openclassrooms.medilabo.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
class MedilaboControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PatientRepository repository;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @WithMockUser(value = "admin", authorities = "ROLE_ADMIN")
    void createPatientOk() throws Exception {

        doNothing().when(jwtAuthenticationFilter).doFilterInternal(any(), any(), any());

        PatientDto patient = buildPatient();
        String json = mapper.writeValueAsString(patient);

        mockMvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .header("Authorization", "Bearer jwt-test-token")).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @WithMockUser("admin")
    void retrieveAllPatientOk() throws Exception {

        Patient patient = new Patient();
        repository.save(patient);

        mockMvc.perform(get("/patient")
                        .with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    @WithMockUser("admin")
    void retrievePatientByIdOk() throws Exception {
        mockMvc.perform(get("/patient/1")
                .with(csrf())).andDo(print())
                .andExpect(status().is2xxSuccessful());
    }

    private PatientDto buildPatient() {
        PatientDto patient = new PatientDto();
        patient.setAddress("address");
        patient.setFirstName("firstName");
        patient.setLastName("lastName");
        patient.setGender(Gender.MALE);
        patient.setPhoneNumber("phone");
        patient.setBirthDate(OffsetDateTime.now());
        return patient;
    }

}
