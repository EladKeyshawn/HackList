package com.projects.elad.hacklist.util;


public class Utils {


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

    StringBuilder resultUrl = new StringBuilder();
    resultUrl.append(Constants.FACEBOOK_API_GET_PAGE_PICTURE);
    int slashIndex = url.lastIndexOf('/');

    resultUrl.append( url.substring(slashIndex + 1)).append("/picture?type=large");

    return resultUrl.toString();


//    int slashIndex = url.lastIndexOf('/');
//
//    return url.substring(slashIndex + 1) + "/picture?type=large";
  }
}
