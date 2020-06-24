package com.mnb.example.test.customer.controller;

import com.mnb.example.test.customer.model.TransactionInfo;
import com.mnb.example.test.customer.service.CustomerTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private CustomerTransactionService customerTransactionService;

    @GetMapping(value = "/all" , produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<TransactionInfo> getTransactions(){
        return  customerTransactionService.findAllTransactions();
    }

    @PostMapping(value = "/add" , produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public TransactionInfo addTransaction(@RequestBody TransactionInfo transactionInfo){
        //TODO : Add assertions to validate the input
        return customerTransactionService.saveTransaction(transactionInfo);
    }

}
