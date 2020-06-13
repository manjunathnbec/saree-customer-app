package com.mnb.example.test.hospital.controller;

import com.mnb.example.test.hospital.model.Patient;
import com.mnb.example.test.hospital.repository.IPatientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    private final IPatientRepository patientRepository;
    public HospitalController(IPatientRepository patientRepository){
        this.patientRepository = patientRepository;
    }

    @GetMapping(value="/patients" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Patient> getAllPatients(){
        return  patientRepository.findAll();
    }

    @GetMapping(value = "/patient/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Patient getPatientById(@PathVariable long id){
        return patientRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patient Not in DB"));
    }

    @PostMapping(value="/patients/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Patient addPatient(@RequestBody Patient patient){
        return patientRepository.save(patient);
    }
}
