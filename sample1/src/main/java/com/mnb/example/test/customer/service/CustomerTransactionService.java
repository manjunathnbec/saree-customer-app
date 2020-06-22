package com.mnb.example.test.customer.service;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.model.TransactionInfo;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import com.mnb.example.test.customer.repository.ITransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerTransactionService {
    @Autowired
    private ITransactionRepository transactionRepository;
    @Autowired
    private ICustomerRepository customerRepository;

    public Iterable<TransactionInfo> findAll() {
        return transactionRepository.findAll();
    }

    public TransactionInfo save(TransactionInfo transactionInfo) {
        TransactionInfo txnInfo = transactionRepository.save(transactionInfo);
        Optional<Customer> customer = customerRepository.findById(transactionInfo.getCustomerId());
        if(customer.isPresent()){
            double balance = customer.get().getBalance();
            balance += transactionInfo.getTransactionAmount();
            balance -= transactionInfo.getAmountPaid();
            customer.get().setBalance(balance);
            Customer updatedCustomer = customerRepository.save(customer.get());
            //print the log
        }
        return txnInfo;
    }

    public Iterable<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> findCustomerById(long id) {
        return customerRepository.findById(id);
    }

    public Iterable<TransactionInfo> findAllTransactionsForCustomer(long customerId) {
        List<TransactionInfo> transactionInfoList = (List<TransactionInfo>) transactionRepository.findAll();
        return transactionInfoList.stream()
                .filter((transactionInfo -> transactionInfo.getCustomerId() == customerId)).collect(Collectors.toList());

    }


    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }
}
