package com.mnb.example.test.customer.repository;

import com.mnb.example.test.customer.model.TransactionInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionRepository extends CrudRepository<TransactionInfo, Long> {
}
