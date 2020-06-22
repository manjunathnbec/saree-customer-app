package com.mnb.example.test.customer;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(ICustomerRepository customerRepository){
		return args -> {
			customerRepository.save(new Customer("Manju", "498359435345"));
			customerRepository.save(new Customer("Nath", "34348787"));
			customerRepository.save(new Customer("Anu", "32424324"));
		};
	}

}
