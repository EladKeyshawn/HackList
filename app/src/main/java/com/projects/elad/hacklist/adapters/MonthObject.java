package com.projects.elad.hacklist.adapters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Elad on 8/13/2016.
 */
public class MonthObject {

  @SerializedName("January")
  @Expose
  private ArrayList<HacklistItem> hacklistItems = new ArrayList<>();

  /**
   * @return The directionOptions
   */
  public ArrayList<HacklistItem> getHacklistItems() {
    return hacklistItems;
  }

  /**
   * @param hacklistItems The directionOptions
   */
  public void setHacklistItems(ArrayList<HacklistItem> hacklistItems) {
    this.hacklistItems = hacklistItems;
  }



}
