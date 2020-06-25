package com.mnb.example.test.customer

import com.mnb.example.test.customer.controller.CustomerController
import com.mnb.example.test.customer.model.Customer
import com.mnb.example.test.customer.model.TransactionInfo
import com.mnb.example.test.customer.service.CustomerTransactionService
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDate

@SpringBootTest
class CustomerControllerSpec extends  Specification {
    private CustomerTransactionService customerTransactionService
    private CustomerController customerController

    def setup(){
        customerTransactionService = Mock(CustomerTransactionService.class)
        customerController = new CustomerController(customerTransactionService: customerTransactionService)
    }

    def "test customer add" () {
        given: "test data of customer "

        Customer c = new Customer();
        c.setCustomerID(1)
        c.setAddress("abc")
        c.setBalance(20.0)
        c.setName("mnb")
        c.setPhone("9632332322")

        Customer cSend = Mock(Customer.class)

        customerTransactionService.saveCustomer(_ as Customer) >> c
        when:
        Customer res = customerController.addCustomer(cSend)
        then:
        assert res.address == c.address
        assert res.customerID == c.customerID
        assert res.balance == c.balance
        assert res.phone == c.phone
        assert res.name == c.name
    }


    def "test get customer" () {
        given: "test data of customer "

        Customer c = new Customer();
        c.setCustomerID(1)
        c.setAddress("abc")
        c.setBalance(20.0)
        c.setName("mnb")
        c.setPhone("9632332322")

        Optional<Customer> optionalCustomer = new Optional<>(c);

        customerTransactionService.findCustomerById(_ as Long) >> optionalCustomer
        when:
        Customer res = customerController.getCustomerById(1)
        then:
        assert res.address == c.address
        assert res.customerID == c.customerID
        assert res.balance == c.balance
        assert res.phone == c.phone
        assert res.name == c.name
    }

    def "test get all customers" () {
        given: "test data of all customer "

        Customer c = new Customer();
        c.setCustomerID(1)
        c.setAddress("abc")
        c.setBalance(20.0)
        c.setName("mnb")
        c.setPhone("9632332322")


        Customer c1 = new Customer();
        c1.setCustomerID(2)
        c1.setAddress("abcxyz")
        c1.setBalance(30.0)
        c1.setName("anu")
        c1.setPhone("8080808080")

        Iterable<Customer> customerList = new ArrayList<>()
        customerList.add(c)
        customerList.add(c1)


        customerTransactionService.findAllCustomers() >> customerList
        when:
        Iterable<Customer> res = customerController.getAllCustomers()
        then:
        assert res.size() == customerList.size()
    }


    def "test get txn for customer" () {
        given: "test txn data of customer"

        Customer c = new Customer();
        c.setCustomerID(1)
        c.setAddress("abc")
        c.setBalance(20.0)
        c.setName("mnb")
        c.setPhone("9632332322")

        TransactionInfo t1 = new TransactionInfo();
        t1.transactionId = 1
        t1.transactionDetails = "abc"
        t1.transactionAmount = 22.0
        t1.amountPaid = 10.0
        t1.customerId = 1
        t1.date = LocalDate.now()

        Iterable<TransactionInfo> transactionInfos = new ArrayList<>()
        transactionInfos.add(t1)

        customerTransactionService.findAllTransactionsForCustomer(_ as Long) >> transactionInfos
        when:
        Iterable<TransactionInfo> res = customerController.getTransactionsForCustomer(1)
        then:
        assert res.size() == transactionInfos.size()
    }
}
