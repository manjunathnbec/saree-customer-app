package com.mnb.example.test.customer.repository;

import com.mnb.example.test.customer.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByName(String name);
}
