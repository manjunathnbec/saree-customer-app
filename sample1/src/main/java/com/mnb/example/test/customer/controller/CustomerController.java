package com.mnb.example.test.customer.controller;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.model.TransactionInfo;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import com.mnb.example.test.customer.repository.ITransactionRepository;
import com.mnb.example.test.customer.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @GetMapping(value="/customers" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Customer> getAllCustomers(){
        return  customerTransactionService.findAllCustomers();
    }

    @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Customer getCustomerById(@PathVariable long id){
        return (Customer) customerTransactionService.findCustomerById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not in DB"));
    }

    @GetMapping(value = "/transactions/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TransactionInfo> getTransactionsForCustomer(@PathVariable long customerId){
        return  customerTransactionService.findAllTransactionsForCustomer(customerId);
    }
    @PostMapping(value="/customers/add", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Customer addCustomer(@RequestBody Customer customer){
        return customerTransactionService.saveCustomer(customer);
    }
}
