package com.projects.elad.hacklist.util;

import java.util.Calendar;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class CurrDate {
   private  int currYearInt;
   private  int currMonthInt;
   private  String currentYear;
   private  String currentMonth;

    public CurrDate() {
         Calendar calender = Calendar.getInstance();

         currYearInt = calender.get(Calendar.YEAR);
         currMonthInt = calender.get(Calendar.MONTH);

         currentYear = String.valueOf(currYearInt);
         currentMonth = Utils.getStringForMonthInt(currMonthInt);
    }

    public int getCurrYearInt() {
        return currYearInt;
    }

    public int getCurrMonthInt() {
        return currMonthInt;
    }

    public String getCurrentYear() {
        return currentYear;
    }

    public String getCurrentMonth() {
        return currentMonth;
    }
}
