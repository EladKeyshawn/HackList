package com.projects.elad.hacklist.adapters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class HackEvent  {



  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("startDate")
  @Expose
  private String startDate;
  @SerializedName("endDate")
  @Expose
  private String endDate;
  @SerializedName("year")
  @Expose
  private String year;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("host")
  @Expose
  private String host;
  @SerializedName("length")
  @Expose
  private String length;
  @SerializedName("size")
  @Expose
  private String size;
  @SerializedName("travel")
  @Expose
  private String travel;
  @SerializedName("prize")
  @Expose
  private String prize;
  @SerializedName("highSchoolers")
  @Expose
  private String highSchoolers;
  @SerializedName("facebookURL")
  @Expose
  private String facebookURL;
  @SerializedName("twitterURL")
  @Expose
  private String twitterURL;
  @SerializedName("googlePlusURL")
  @Expose
  private String googlePlusURL;
  @SerializedName("notes")
  @Expose
  private String notes;


  public String getTitle() {
    return title;
  }

  public String getUrl() {
    return url;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public String getYear() {
    return year;
  }

  public String getCity() {
    return city;
  }

  public String getHost() {
    return host;
  }

  public String getLength() {
    return length;
  }

  public String getSize() {
    return size;
  }

  public String getTravel() {
    return travel;
  }

  public String getPrize() {
    return prize;
  }

  public String getHighSchoolers() {
    return highSchoolers;
  }

  public String getFacebookURL() {
    return facebookURL;
  }

  public String getTwitterURL() {
    return twitterURL;
  }

  public String getGooglePlusURL() {
    return googlePlusURL;
  }

  public String getNotes() {
    return notes;
  }
}
