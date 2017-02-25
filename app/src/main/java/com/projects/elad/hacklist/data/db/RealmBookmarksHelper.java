package com.projects.elad.hacklist.data.db;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */

public class RealmBookmarksHelper {
    @Inject Realm realm;

    public void writeToRealm(BookmarkDbEntity bookmarkToSave) {
        realm.executeTransaction(transactionRealm -> {
            BookmarkDbEntity bookmark = findInRealm(transactionRealm, bookmarkToSave.getEventTitle());
            if (bookmark == null)
                transactionRealm.createObject(BookmarkDbEntity.class, bookmarkToSave);
        });
        realm.close();
    }


    public BookmarkDbEntity findInRealm(Realm realm, String title) {
        return realm.where(BookmarkDbEntity.class).equalTo("eventTitle",title).findFirst();
    }

    public List<BookmarkDbEntity> findAllBookmarks() {
        return realm.where(BookmarkDbEntity.class).findAll();
    }
}
