package com.mnb.example.test.customer.controllers

import com.mnb.example.test.customer.controller.TransactionController
import com.mnb.example.test.customer.model.TransactionInfo
import com.mnb.example.test.customer.service.CustomerTransactionService
import spock.lang.Specification

import java.time.LocalDate

class TransactionControllerSpec extends  Specification{

    private CustomerTransactionService customerTransactionService
    private TransactionController transactionController

    def setup(){
        customerTransactionService = Mock(CustomerTransactionService)
        transactionController = new TransactionController(customerTransactionService:
                customerTransactionService )
    }

    def "add txn"(){
        given:

        TransactionInfo transactionInfo = new TransactionInfo();
        transactionInfo.transactionId = 1
        transactionInfo.transactionDetails = "abc"
        transactionInfo.transactionAmount = 22.0
        transactionInfo.amountPaid = 10.0
        transactionInfo.customerId = 1
        transactionInfo.date = LocalDate.now()

        customerTransactionService.saveTransaction(_ as TransactionInfo) >> transactionInfo

        when:
        TransactionInfo  res = transactionController.addTransaction(Mock(TransactionInfo.class))

        then:
        assert res.date == transactionInfo.date
        assert res.customerId == transactionInfo.customerId
        assert res.amountPaid == transactionInfo.amountPaid
        assert res.transactionAmount == transactionInfo.transactionAmount
        assert res.transactionId == transactionInfo.transactionId
        assert res.transactionDetails == transactionInfo.transactionDetails

    }

    def "get all txns"(){
        given:

        TransactionInfo t1 = new TransactionInfo();
        t1.transactionId = 1
        t1.transactionDetails = "abc"
        t1.transactionAmount = 22.0
        t1.amountPaid = 10.0
        t1.customerId = 1
        t1.date = LocalDate.now()

        TransactionInfo t2 = new TransactionInfo();
        t2.transactionId = 2
        t2.transactionDetails = "abc"
        t2.transactionAmount = 22.0
        t2.amountPaid = 10.0
        t2.customerId = 2
        t2.date = LocalDate.now()

        Iterable<TransactionInfo> transactionInfos = new ArrayList<>();
        transactionInfos.add(t1)
        transactionInfos.add(t2)

        customerTransactionService.findAllTransactions() >> transactionInfos

        when:
        Iterable<TransactionInfo>  res = transactionController.getTransactions()

        then:
        assert res.size() == transactionInfos.size()

    }


}
