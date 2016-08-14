package com.projects.elad.hacklist.utils;


public class UsefulFunctions {


  // get month in calender format and gives a string
  public static String getStringForMonthInt(int monthInt) {
    int temp = monthInt + 1;
    StringBuilder resultString = new StringBuilder();
    if (temp > 0 && temp < 10) {
      resultString.append("0").append(String.valueOf(temp));
    } else {
      resultString.append(String.valueOf(temp));
    }
    return resultString.toString();
  }

  public static String getPageIdFromUrl (String url) {

    int slashIndex = url.lastIndexOf('/');

    return url.substring(slashIndex + 1) + "/picture?type=large";
  }
}
