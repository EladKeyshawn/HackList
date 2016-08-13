package com.projects.elad.hacklist.adapters;



import java.util.ArrayList;


public class MonthObject {


  private ArrayList<HackEvent> hackEvents = new ArrayList<>();

  /**
   * @return The directionOptions
   */
  public ArrayList<HackEvent> getHackEvents() {
    return hackEvents;
  }

  /**
   * @param hackEvents The directionOptions
   */
  public void setHackEvent(ArrayList<HackEvent> hackEvents) {
    this.hackEvents = hackEvents;
  }

}
