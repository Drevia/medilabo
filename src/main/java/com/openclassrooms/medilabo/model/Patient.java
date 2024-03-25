package com.openclassrooms.medilabo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;

@Data
@Entity
public class Patient {

    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "id")
    private String id;
    private String firstName;
    private String lastName;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private OffsetDateTime birthDate;
    private Gender gender;
    private String address;
    private String phoneNumber;
}
