//package com.projects.elad.hacklist.presentation.main.fragments;
//
//
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.afollestad.materialdialogs.DialogAction;
//import com.afollestad.materialdialogs.MaterialDialog;
//import com.mikepenz.fastadapter.FastAdapter;
//import com.mikepenz.fastadapter.IAdapter;
//import com.mikepenz.fastadapter.IItem;
//import com.mikepenz.fastadapter.adapters.FastItemAdapter;
//import com.projects.elad.hacklist.R;
//
//
//
///**
// * A simple {@link Fragment} subclass.
// */
//public class FragmentBookmarks extends Fragment implements FastAdapter.OnLongClickListener {
//
//  private FastItemAdapter fastAdapter;
//  private RecyclerView bookmarksList;
//  private FragmentActivity context;
//
//  public FragmentBookmarks() {
//    // Required empty public constructor
//  }
//
//
//  @Override
//  public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                           Bundle savedInstanceState) {
//    context = super.getActivity();
//    View ourView = inflater.inflate(R.layout.fragment_bookmarks, container, false);
//
//
//    bookmarksList = (RecyclerView) ourView.findViewById(R.id.bookmarks_list);
//
//    fastAdapter = new FastItemAdapter();
//    fastAdapter.withSelectOnLongClick(false);
//    fastAdapter.withSelectable(false);
//    fastAdapter.withOnLongClickListener(this);
//
//
//    bookmarksList.setLayoutManager(new LinearLayoutManager(context));
//    bookmarksList.setAdapter(fastAdapter);
//
//    updateBookmarks();
//
//
//    return ourView;
//  }
//
//
//  @Override
//  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//    super.onViewCreated(view, savedInstanceState);
//
//
//  }
//
//
//  public void updateBookmarks() {
//    ArrayList<EventBookmark> bookmarks = getBookmarksFromDb();
//    addBookmarksToAdapter(bookmarks);
//  }
//
//  public ArrayList<EventBookmark> getBookmarksFromDb() {
//    return (ArrayList<EventBookmark>) EventBookmark.listAll(EventBookmark.class);
//  }
//
//  private void addBookmarksToAdapter(ArrayList<EventBookmark> bookmarks) {
//    fastAdapter.clear();
//
//    for (EventBookmark bookmark : bookmarks) {
//      BookmarkEventItem newListItem = new BookmarkEventItem(context, bookmark);
//      fastAdapter.add(newListItem);
//    }
//
//  }
//
//
//  @Override
//  public void onResume() {
//    super.onResume();
//  }
//
//  @Override
//  public void setUserVisibleHint(boolean isVisibleToUser) {
//    super.setUserVisibleHint(isVisibleToUser);
//
//    if (isVisibleToUser) { // fragment is visible so update bookmarks
//      updateBookmarks();
//    }
//
//
//  }
//
//  @Override
//  public boolean onLongClick(View v, IAdapter adapter, IItem item, int position) {
//    BookmarkEventItem bookmark = (BookmarkEventItem) item;
//    openDeleteBookmarkDialog(bookmark);
//
//    return true;
//  }
//
//  private void openDeleteBookmarkDialog(final BookmarkEventItem bookmark) {
//    MaterialDialog.Builder dialog = new MaterialDialog.Builder(context)
//        .title("Delete bookmark")
//        .content("Are you sure you to delete this bookmark?")
//        .positiveText("delete")
//        .positiveColor(Color.RED)
//        .negativeText("cancel")
//        .onPositive(new MaterialDialog.SingleButtonCallback() {
//          @Override
//          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//              deleteBookmark(bookmark);
//          }
//        })
//        .onNegative(new MaterialDialog.SingleButtonCallback() {
//          @Override
//          public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//
//          }
//        });
//
//    dialog.show();
//  }
//
//  private void deleteBookmark(BookmarkEventItem bookmark) {
//    ArrayList<EventBookmark> bookmarkMatched = (ArrayList<EventBookmark>)
//        EventBookmark.find(EventBookmark.class,"event_title = ?", bookmark.getBookmarkTitle());
//
//    bookmarkMatched.get(0).delete();
//    updateBookmarks();
//  }
//}
