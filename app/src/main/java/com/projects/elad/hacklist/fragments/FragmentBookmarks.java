package com.projects.elad.hacklist.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.adapters.BookmarkEventItem;
import com.projects.elad.hacklist.db.EventBookmark;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentBookmarks extends Fragment {

  private FastItemAdapter fastAdapter;
  private RecyclerView bookmarksList;
  private FragmentActivity context;

  public FragmentBookmarks() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    context = super.getActivity();
    View ourView = inflater.inflate(R.layout.fragment_bookmarks, container, false);


    bookmarksList = (RecyclerView) ourView.findViewById(R.id.bookmarks_list);

    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(false);
    fastAdapter.withSelectable(false);


    bookmarksList.setLayoutManager(new LinearLayoutManager(context));
    bookmarksList.setAdapter(fastAdapter);

    getBookmarksFromDb();


    return ourView;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);



  }

  public void getBookmarksFromDb() {
    ArrayList<EventBookmark> bookmarks = (ArrayList<EventBookmark>) EventBookmark.listAll(EventBookmark.class);
    addBookmarksToAdapter(bookmarks);
  }

  private void addBookmarksToAdapter(ArrayList<EventBookmark> bookmarks) {
    for(EventBookmark bookmark : bookmarks) {
      BookmarkEventItem newListItem = new BookmarkEventItem(bookmark.getEventTitle());
      fastAdapter.add(newListItem);
    }

  }
}
