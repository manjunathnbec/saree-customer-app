package com.mnb.example.test.hospital.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long patientId;
    private String name;
    private String phone;

    public Patient(String name, String ph) {
        this.name = name;
        this.phone = ph;
    }
}
