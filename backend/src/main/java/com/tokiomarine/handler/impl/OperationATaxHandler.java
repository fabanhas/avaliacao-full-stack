package com.tokiomarine.handler.impl;

import com.tokiomarine.enums.Operation;
import com.tokiomarine.handler.OperationTaxHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;

import static com.tokiomarine.enums.Operation.A;
import static java.math.BigDecimal.ZERO;

@Component
public class OperationATaxHandler implements OperationTaxHandler {

	@Override
	public boolean canHandle(Operation operation) {
		return operation.equals(A);
	}

	@Override
	public BigDecimal calculate(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		if (transferDate.equals(currentDate))
			return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
		
		return ZERO;
	}

}
