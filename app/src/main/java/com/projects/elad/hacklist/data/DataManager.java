package com.projects.elad.hacklist.data;
import javax.inject.Inject;
import javax.inject.Singleton;

import com.projects.elad.hacklist.data.db.BookmarkDbEntity;
import com.projects.elad.hacklist.data.db.RealmBookmarksHelper;
import com.projects.elad.hacklist.data.api.HacklistService;
import com.projects.elad.hacklist.util.CurrDate;

import java.util.List;

import rx.Observable;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

@Singleton
public class DataManager {
    private HacklistService hacklistService;
    private CurrDate date;
    private RealmBookmarksHelper realmHelper;
    @Inject
    public DataManager(HacklistService hacklistService , CurrDate date, RealmBookmarksHelper realmBookmarksHelper) {
        this.hacklistService = hacklistService;
        this.date = date;
        this.realmHelper = realmBookmarksHelper;
    }

    public HacklistService getHacklistService() {
        return hacklistService;
    }

    public CurrDate getDate() {
        return date;
    }

    public Observable<List<BookmarkDbEntity>> getAllBookmarks() {
        return Observable.just(realmHelper.findAllBookmarks());
    }

    public void saveBookmark(BookmarkDbEntity item) {
        realmHelper.writeToRealm(item);
    }

    public void deleteBookmark(String title) {
        realmHelper.deleteBookmark(title);
    }
}
