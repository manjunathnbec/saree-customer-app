package com.mnb.example.test.customer.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;


@Entity
@Table(name = "TxnInfo")
@NoArgsConstructor
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



    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(String transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionInfo that = (TransactionInfo) o;
        return transactionId == that.transactionId &&
                customerId == that.customerId &&
                Double.compare(that.transactionAmount, transactionAmount) == 0 &&
                Double.compare(that.amountPaid, amountPaid) == 0 &&
                Objects.equals(date, that.date) &&
                Objects.equals(transactionDetails, that.transactionDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, date, customerId, transactionAmount, transactionDetails, amountPaid);
    }

    @Override
    public String toString() {
        return "TransactionInfo{" +
                "transactionId=" + transactionId +
                ", date=" + date +
                ", customerId=" + customerId +
                ", transactionAmount=" + transactionAmount +
                ", transactionDetails='" + transactionDetails + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
