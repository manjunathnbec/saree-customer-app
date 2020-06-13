package com.mnb.example.test.hospital;

import com.mnb.example.test.hospital.model.Patient;
import com.mnb.example.test.hospital.repository.IPatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(IPatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient("Manju", "498359435345"));
			patientRepository.save(new Patient("Nath", "34348787"));
			patientRepository.save(new Patient("Anu", "32424324"));
		};
	}

}
