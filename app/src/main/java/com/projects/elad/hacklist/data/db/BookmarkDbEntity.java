package com.projects.elad.hacklist.data.db;

import com.projects.elad.hacklist.presentation.main.adapters.ListItem;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
public class BookmarkDbEntity extends RealmObject {
    @PrimaryKey
    private String eventTitle;
    private String facebookUrl;


    public BookmarkDbEntity() {

    }

    public BookmarkDbEntity(ListItem item) {
        this.eventTitle = item.getTitle();
        this.facebookUrl = item.getFacebookUrl();
    }


    public String getEventTitle() {
        return eventTitle;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setEventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }
}
