package com.mnb.example.test.customer.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int transactionId;
    private LocalDate date;
    private long customerId;
    private double transactionAmount;
    private String transactionDetails;
    private double amountPaid;

    public TransactionInfo(LocalDate localDate, long customerId,
                           double transactionAmount,
                           String transactionDetails,
                           double amountPaid){
        this.date = localDate;
        this.customerId = customerId;
        this.transactionAmount = transactionAmount;
        this.transactionDetails = transactionDetails;
        this.amountPaid = amountPaid;
    }




}
