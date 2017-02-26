package com.projects.elad.hacklist.data.db;

import android.content.Context;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */

public class RealmBookmarksHelper {
    private Realm realm;
    private Context context;
    @Inject
    public RealmBookmarksHelper(Context context) {
        Realm.init(context);
        this.context = context;
    }

    public void writeToRealm(BookmarkDbEntity bookmarkToSave) {
       try {
           realm = Realm.getDefaultInstance();
           realm.executeTransaction(transactionRealm -> {
               BookmarkDbEntity bookmark = findInRealm(transactionRealm, bookmarkToSave.getEventTitle());
               if (bookmark == null)
                   bookmark = transactionRealm.createObject(BookmarkDbEntity.class, bookmarkToSave.getEventTitle());
               bookmark.setFacebookUrl(bookmarkToSave.getFacebookUrl());

           });
       } finally {
           realm.close();
       }
    }


    public BookmarkDbEntity findInRealm(Realm realm, String title) {
        return realm.where(BookmarkDbEntity.class).equalTo("eventTitle",title).findFirst();
    }

    public List<BookmarkDbEntity> findAllBookmarks() {
        realm = Realm.getDefaultInstance();
        return realm.where(BookmarkDbEntity.class).findAll();
    }

    public void deleteBookmark(String title) {
        try {
            realm = Realm.getDefaultInstance();

            realm.executeTransaction(transactionRealm -> {
                BookmarkDbEntity bookmark = findInRealm(transactionRealm, title);
                if (bookmark!=null) bookmark.deleteFromRealm();
            });
        } finally {
            realm.close();
        }
    }

    public boolean isInDatabase(String title) {
        realm = Realm.getDefaultInstance();
        BookmarkDbEntity entity = realm.where(BookmarkDbEntity.class).equalTo("eventTitle", title).findFirst();
        realm.close();
        return entity != null;
    }
}
