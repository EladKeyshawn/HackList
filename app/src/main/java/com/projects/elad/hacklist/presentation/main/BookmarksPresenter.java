package com.projects.elad.hacklist.presentation.main;

import android.content.Context;
import android.widget.Toast;

import com.projects.elad.hacklist.HacklistApplication;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.presentation.base.BasePresenter;
import com.projects.elad.hacklist.presentation.main.adapters.BookmarkItem;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentBookmarks;
import com.projects.elad.hacklist.util.Mappers;
import com.projects.elad.hacklist.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */

public class BookmarksPresenter extends BasePresenter<BookmarksMvpView> {

    @Inject DataManager dataManager;
    @Inject Context context;
    private Subscription subscription;
    public BookmarksPresenter() {
    }


    @Override
    public void detachView() {
        super.detachView();
        RxUtil.unsubscribe(subscription);
    }

    @Override
    public void attachView(BookmarksMvpView mvpView) {
        super.attachView(mvpView);
        ((HacklistApplication)(((FragmentBookmarks)getMvpView()).getActivity().getApplication())).getComponent().inject(this);

    }


    public void loadBookmarks() {
        RxUtil.unsubscribe(subscription);
        subscription = dataManager.getAllBookmarks()
                .map(dbEntities -> Mappers.mapBookmarkDbEntityToItem(dbEntities,context))
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<BookmarkItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Error when fetching bookmarks" + e.toString(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<BookmarkItem> bookmarkItems) {
                        if (bookmarkItems.isEmpty()) {
                            getMvpView().showEmpty();
                        }
                        getMvpView().showBookmarks(bookmarkItems);
                    }
                });
    }

    public void refreshBookmarks() {
        loadBookmarks();
    }

    public void deleteBookmark(BookmarkItem bookmark) {
        dataManager.deleteBookmark(bookmark.getBookmarkTitle());
    }
}
