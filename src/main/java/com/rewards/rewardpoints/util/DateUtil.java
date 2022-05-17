package com.rewards.rewardpoints.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtil {

    public static Date get3MonthOldDate(){
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MONTH, -3);
        return calendar.getTime();
    }

    public static Date getDate(){
        Calendar calendar = GregorianCalendar.getInstance();
        Random random = new Random();
        int result = random.nextInt(80);
        calendar.add(Calendar.DATE, -result);
        return calendar.getTime();
    }

}
