package com.tokiomarine.handler.impl;


import com.tokiomarine.enums.Operation;
import com.tokiomarine.handler.OperationTaxHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;


import static com.tokiomarine.common.CalendarCommon.getCalendarPlusDays;
import static com.tokiomarine.enums.Operation.B;
import static java.math.BigDecimal.ZERO;

@Component
public class OperationBTaxHandler implements OperationTaxHandler {

	@Override
	public boolean canHandle(Operation operation) {
		return operation.equals(B);
	}

	@Override
	public BigDecimal calculate(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		Calendar tenDaysFromToday = getCalendarPlusDays(11, currentDate);
		
		if (transferDate.before(tenDaysFromToday))
			return BigDecimal.valueOf(12);

		return ZERO;
	}
	

}
