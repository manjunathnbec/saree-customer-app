package com.mnb.example.test.hospital.repository;

import com.mnb.example.test.hospital.model.Patient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IPatientRepository extends CrudRepository<Patient, Long> {
    List<Patient> findByName(String name);
}
