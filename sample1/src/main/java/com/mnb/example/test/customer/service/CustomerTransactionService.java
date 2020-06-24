package com.mnb.example.test.customer.service;

import com.mnb.example.test.customer.model.Customer;
import com.mnb.example.test.customer.model.TransactionInfo;
import com.mnb.example.test.customer.repository.ICustomerRepository;
import com.mnb.example.test.customer.repository.ITransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerTransactionService {
    @Autowired
    private  ITransactionRepository transactionRepository;
    @Autowired
    private  ICustomerRepository customerRepository;


    public Iterable<TransactionInfo> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Transactional
    public TransactionInfo saveTransaction(TransactionInfo transactionInfo) {

        Optional<Customer> customer = customerRepository.findById(transactionInfo.getCustomerId());
        if(customer.isPresent()){
            double balance = customer.get().getBalance();
            balance += transactionInfo.getTransactionAmount();
            balance -= transactionInfo.getAmountPaid();
            customer.get().setBalance(balance);
            customerRepository.save(customer.get());
        }
        TransactionInfo txnInfo = transactionRepository.save(transactionInfo);
        log.info("adding txn info {}", transactionInfo);
        log.info("Updated customer info {}", customer.get());
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
