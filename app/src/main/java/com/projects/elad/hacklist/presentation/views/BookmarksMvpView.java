package com.projects.elad.hacklist.presentation.views;

import com.projects.elad.hacklist.presentation.base.MvpView;
import com.projects.elad.hacklist.presentation.main.adapters.BookmarkItem;

import java.util.List;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */

public interface BookmarksMvpView extends MvpView {

     void showBookmarks(List<BookmarkItem> items);

     void showEmpty();
}
