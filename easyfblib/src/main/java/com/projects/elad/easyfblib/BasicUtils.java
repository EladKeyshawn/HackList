package com.projects.elad.easyfblib;

/**
 * Created by Elad on 9/5/2016.
 */
public class BasicUtils {

  public static String getPPFromUrl (String url) {
    StringBuilder resultUrl = new StringBuilder();
    resultUrl.append(Constants.FACEBOOK_API_GET_PAGE_PICTURE);
    int slashIndex = url.lastIndexOf('/');

    resultUrl.append( url.substring(slashIndex + 1)).append("/picture?type=large");

    return resultUrl.toString();

  }

}
