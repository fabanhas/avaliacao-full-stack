package com.tokiomarine.service;

import com.tokiomarine.exception.BadRequestException;
import com.tokiomarine.model.Transaction;

import java.util.List;

public interface TransactionService {

    void schedule(Transaction transaction, String operation) throws BadRequestException;

    List<Transaction> getAll();

}
