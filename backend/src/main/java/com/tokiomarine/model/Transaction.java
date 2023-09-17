package com.tokiomarine.model;

import com.tokiomarine.enums.Operation;
import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Calendar;

@Data
@Entity
@ToString
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "FROM_ACCOUNT")
    private String fromAccount;

    @Column(name = "TO_ACCOUNT")
    private String toAccount;

    @Column(name = "TAXES")
    private BigDecimal taxes;

    @Column(name = "VALUE")
    private BigDecimal value;

    @Column(name = "TRANSFER_DATE")
    private Calendar transferDate;

    @Column(name = "SCHEDULED_DATE")
    private Calendar scheduleDate = Calendar.getInstance();

    @Column(name = "OPERATION")
    private Operation operation;
}