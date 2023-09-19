package com.tokiomarine.common;

import lombok.NoArgsConstructor;

import java.util.Calendar;

@NoArgsConstructor
public class CalendarCommon {
    public static Calendar getCalendarOnlyDate(Calendar currentDate) {
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate;
    }

    public static Calendar getCalendarPlusDays(Integer daysToSum, Calendar currentDate) {
        Calendar calendar = (Calendar) currentDate.clone();
        calendar.add(Calendar.DAY_OF_MONTH, daysToSum);
        return calendar;
    }
}
