package com.tokiomarine.handler.impl;


import com.tokiomarine.enums.Operation;
import com.tokiomarine.handler.OperationTaxHandler;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;

import static com.tokiomarine.common.CalendarCommon.getCalendarPlusDays;
import static com.tokiomarine.enums.Operation.C;


@Component
public class OperationCTaxHandler implements OperationTaxHandler {

	@Override
	public boolean canHandle(Operation operation) {
		return operation.equals(C);
	}

	@Override
	public BigDecimal calculate(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		Calendar tenDaysFromToday = getCalendarPlusDays(10, currentDate);
		Calendar twentyDaysFromToday = getCalendarPlusDays(20, currentDate);
		Calendar thirtyDaysFromToday = getCalendarPlusDays(30, currentDate);
		Calendar fortyDaysFromToday = getCalendarPlusDays(40, currentDate);
		
		if (transferDate.after(tenDaysFromToday) && transferDate.before(twentyDaysFromToday))
			return value.multiply(BigDecimal.valueOf(0.082));
		
		if (transferDate.after(twentyDaysFromToday) && transferDate.before(thirtyDaysFromToday))
			return value.multiply(BigDecimal.valueOf(0.069));
		
		if (transferDate.after(thirtyDaysFromToday) && transferDate.before(fortyDaysFromToday))
			return value.multiply(BigDecimal.valueOf(0.047));	
		
		return value.multiply(BigDecimal.valueOf(0.017));
	}

}
