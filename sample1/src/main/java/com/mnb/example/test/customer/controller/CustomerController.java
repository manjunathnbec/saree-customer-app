package com.mnb.example.test.customer.controller;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final ICustomerRepository customerRepository;
    public CustomerController(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @GetMapping(value="/customers" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Customer> getAllCustomers(){
        return  customerRepository.findAll();
    }

    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(@PathVariable long id){
        return customerRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not in DB"));
    }

    @PostMapping(value="/customers/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
}
