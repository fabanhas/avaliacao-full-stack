package com.tokiomarine.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDTO {
    private String fromAccount;

    private String toAccount;

    private BigDecimal value;

    private Calendar transferDate;

    private String operation;
}
