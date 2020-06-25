package com.mnb.example.test.customer;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.model.TransactionInfo;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import com.mnb.example.test.customer.service.CustomerTransactionService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.time.LocalDate;

@SpringBootApplication
@EnableSwagger2
public class CustomerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}

	@Bean
	public CommandLineRunner sampleData(ICustomerRepository customerRepository, CustomerTransactionService transactionService){
		return args -> {
			customerRepository.save(new Customer("Manju", "498359435345", "abc", 0));
			customerRepository.save(new Customer("Nath", "34348787", "xyz",0));
			customerRepository.save(new Customer("Anu", "32424324", "abcxyz",0));

			transactionService.saveTransaction(
					new TransactionInfo(LocalDate.now(), 1, 100.0, "sareee 1", 50));
			transactionService.saveTransaction(
					new TransactionInfo(LocalDate.now(), 2, 120.0, "sareee 2", 70));
			transactionService.saveTransaction(
					new TransactionInfo(LocalDate.now(), 1, 90.0, "sareee 3", 0));
		};
	}

}
