package com.projects.elad.hacklist.presentation.main.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.injection.components.BookmarksFragmentComponent;
import com.projects.elad.hacklist.injection.components.DaggerBookmarksFragmentComponent;
import com.projects.elad.hacklist.injection.modules.BookmarksFragmentModule;
import com.projects.elad.hacklist.presentation.main.adapters.BookmarkItem;
import com.projects.elad.hacklist.presentation.presenters.BookmarksPresenter;
import com.projects.elad.hacklist.presentation.views.BookmarksMvpView;

import java.util.List;

import javax.inject.Inject;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBookmarks extends Fragment implements FastAdapter.OnLongClickListener, BookmarksMvpView {

    private FastAdapter fastAdapter;
    private ItemAdapter<BookmarkItem> itemFastAdapter;
    private LinearLayout noBookmarksLayout;
    private RecyclerView bookmarksList;
    private FragmentActivity context;
    @Inject BookmarksPresenter bookmarksPresenter;


    public FragmentBookmarks() {
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getComponent().inject(this);
    }

    BookmarksFragmentComponent getComponent() {
        return DaggerBookmarksFragmentComponent.builder().bookmarksFragmentModule(new BookmarksFragmentModule()).build();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = super.getActivity();
        View ourView = inflater.inflate(R.layout.fragment_bookmarks, container, false);

        noBookmarksLayout = (LinearLayout) ourView.findViewById(R.id.no_bookmarks_layout);
        bookmarksList = (RecyclerView) ourView.findViewById(R.id.bookmarks_list);

        fastAdapter = new FastAdapter();
        itemFastAdapter = new ItemAdapter<>();
        fastAdapter.withSelectOnLongClick(false);
        fastAdapter.withSelectable(false);
        fastAdapter.withOnLongClickListener(this);


        bookmarksList.setLayoutManager(new LinearLayoutManager(context));
        bookmarksList.setAdapter(itemFastAdapter.wrap(fastAdapter));


        return ourView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bookmarksPresenter.attachView(this);
        bookmarksPresenter.loadBookmarks();
    }


    public void showBookmarks(List<BookmarkItem> items) {
        noBookmarksLayout.setVisibility(View.GONE);
        itemFastAdapter.clear();
        itemFastAdapter.add(items);
    }

    @Override
    public void showEmpty() {
        itemFastAdapter.clear();
        noBookmarksLayout.setVisibility(View.VISIBLE);
    }


    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bookmarksPresenter.detachView();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) { // fragment is visible so update bookmarks
            bookmarksPresenter.loadBookmarks();
        }

    }

    @Override
    public boolean onLongClick(View v, IAdapter adapter, IItem item, int position) {
        BookmarkItem bookmark = (BookmarkItem) item;
        openDeleteBookmarkDialog(bookmark);
        return true;
    }

    private void openDeleteBookmarkDialog(final BookmarkItem bookmark) {
        new MaterialDialog.Builder(context)
                .title("Delete bookmark")
                .content("Are you sure you to delete this bookmark?")
                .positiveText("delete")
                .positiveColor(Color.RED)
                .negativeText("cancel")
                .onPositive((dialog1, which) -> bookmarksPresenter.deleteBookmark(bookmark))
                .show();

    }

}
