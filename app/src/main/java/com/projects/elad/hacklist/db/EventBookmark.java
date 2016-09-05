package com.projects.elad.hacklist.db;

import com.orm.SugarRecord;
import com.projects.elad.hacklist.adapters.ListItem;


public class EventBookmark extends SugarRecord {
  String eventTitle;
  String facebookUrl;
  ListItem eventObj;


  public EventBookmark () {

  }

  public EventBookmark(String eventTitle, ListItem eventObj) {
    this.eventTitle = eventTitle;
    this.eventObj = eventObj;
    this.facebookUrl = eventObj.getFacebookUrl();
  }

  public String getEventTitle() {
    return eventTitle;
  }

  public ListItem getEventObj() {
    return eventObj;
  }

  public String getFacebookUrl() {
    return facebookUrl;
  }

}
