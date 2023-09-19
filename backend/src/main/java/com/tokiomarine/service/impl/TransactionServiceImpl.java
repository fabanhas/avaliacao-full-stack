package com.tokiomarine.service.impl;

import com.tokiomarine.enums.Operation;
import com.tokiomarine.exception.BadRequestException;
import com.tokiomarine.facade.OperationTaxFacade;
import com.tokiomarine.model.Transaction;
import com.tokiomarine.repository.TransactionRepository;
import com.tokiomarine.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.math.BigDecimal.ZERO;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository repository;

    @Autowired
    OperationTaxFacade operationTaxFacade;

    @Override
    public void schedule(Transaction transaction, String operation) throws BadRequestException {
        Operation operationEnum = Operation.forName(operation);
        validateTransfer(transaction);

        transaction.setOperation(operationEnum);
        transaction.setTaxes(operationTaxFacade.calculateOperationTax(transaction));
        repository.save(transaction);
    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    private void validateTransfer(Transaction transaction) throws BadRequestException {
        if (transaction.getFromAccount() == null)
            throw new BadRequestException("Invalid origin account");

        if (transaction.getToAccount() == null)
            throw new BadRequestException("Invalid destiny account");

        if (transaction.getTransferDate() == null)
            throw new BadRequestException("Invalid transfer date");

        if (transaction.getValue() == null || transaction.getValue() == ZERO)
            throw new BadRequestException("Invalid transfer value");
    }


}
