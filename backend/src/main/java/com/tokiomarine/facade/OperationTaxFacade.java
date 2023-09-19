package com.tokiomarine.facade;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.tokiomarine.exception.BadRequestException;
import com.tokiomarine.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tokiomarine.enums.Operation;
import com.tokiomarine.handler.OperationTaxHandler;

import static com.tokiomarine.common.CalendarCommon.getCalendarOnlyDate;

@Component
public class OperationTaxFacade {

    @Autowired
    List<OperationTaxHandler> taxRules;

    public BigDecimal calculateOperationTax(Transaction transaction) throws BadRequestException {
        BigDecimal value = transaction.getValue();

        Calendar transferDate = getCalendarOnlyDate(transaction.getTransferDate());
        Calendar currentDate = getCalendarOnlyDate(transaction.getScheduleDate());
        currentDate.setTimeZone(transferDate.getTimeZone());

        Operation operation = transaction.getOperation();

        for (OperationTaxHandler taxRule : taxRules) {
            if (taxRule.canHandle(operation)) {
                return taxRule.calculate(value, transferDate, currentDate);
            }
        }

        throw new BadRequestException("Cannot handle operation type");
    }
}
