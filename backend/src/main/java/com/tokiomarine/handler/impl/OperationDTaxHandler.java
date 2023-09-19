package com.tokiomarine.handler.impl;

import com.tokiomarine.enums.Operation;
import com.tokiomarine.handler.OperationTaxHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import static com.tokiomarine.common.CalendarCommon.getCalendarPlusDays;
import static com.tokiomarine.enums.Operation.D;


@Component
public class OperationDTaxHandler implements OperationTaxHandler {

	@Autowired
	List<OperationTaxHandler> taxRule;
	
	@Override
	public boolean canHandle(Operation operation) {
		return operation.equals(D);
	}

	@Override
	public BigDecimal calculate(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		BigDecimal oneThousand = BigDecimal.valueOf(1000);
		BigDecimal twoThousand = BigDecimal.valueOf(2000);
		
		if (value.compareTo(oneThousand) <= 0)
			return upToOneThousand(value, transferDate, currentDate);
		
		if (value.compareTo(oneThousand) > 0 && value.compareTo(twoThousand) <= 0)
			return betweenOneAndTwoThousand(value, transferDate, currentDate);
		
		return aboveTwoThousand(value, transferDate, currentDate);
	}
	
	private BigDecimal upToOneThousand(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		if (transferDate.equals(currentDate))
			return BigDecimal.valueOf(3).add(value.multiply(BigDecimal.valueOf(0.03)));
		
		return BigDecimal.ZERO;
	}
	
	private BigDecimal betweenOneAndTwoThousand(BigDecimal value, Calendar transferDate, Calendar currentDate) {
		Calendar tenDaysFromToday = getCalendarPlusDays(10, currentDate);
		
		if (transferDate.before(tenDaysFromToday))
			return BigDecimal.valueOf(12);
		
		return BigDecimal.ZERO;
	}
	
	private BigDecimal aboveTwoThousand(BigDecimal value, Calendar transferDate, Calendar currentDate) {
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
