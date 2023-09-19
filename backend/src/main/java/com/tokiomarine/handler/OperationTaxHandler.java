package com.tokiomarine.handler;

import com.tokiomarine.enums.Operation;

import java.math.BigDecimal;
import java.util.Calendar;

public interface OperationTaxHandler {

	boolean canHandle(Operation operation);
	
	BigDecimal calculate(BigDecimal value, Calendar transferDate, Calendar currentDate);
}
