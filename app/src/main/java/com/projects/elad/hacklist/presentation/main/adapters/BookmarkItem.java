package com.projects.elad.hacklist.presentation.main.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.data.db.BookmarkDbEntity;
import com.projects.elad.hacklist.util.Utils;
import com.squareup.picasso.Picasso;

public class BookmarkItem extends AbstractItem<BookmarkItem, BookmarkItem.ViewHolder> {


  private String bookmarkTitle;
  private String eventFacebookUrl;
  private Context context;

  public BookmarkItem(Context context, BookmarkDbEntity entity) {
    this.bookmarkTitle = entity.getEventTitle();
    this.eventFacebookUrl = entity.getFacebookUrl();
    this.context = context;
  }


  public String getBookmarkTitle() {
    return bookmarkTitle;
  }

  public String getEventFacebookUrl() {
    return eventFacebookUrl;
  }

  public Context getContext() {
    return context;
  }

  @Override
  public int getType() {
    return 0;
  }

  @Override
  public int getLayoutRes() {
    return R.layout.bookmark_event_item;
  }


  @Override
  public void bindView(BookmarkItem.ViewHolder holder) {
    super.bindView(holder);
    holder.title.setText(bookmarkTitle);
    Picasso.with(context)
        //        .load("https://hackthenorth.com/2014/img/logo.png")
        .load(Utils.getPageIdFromUrl(eventFacebookUrl))
        .placeholder(R.mipmap.ic_launcher)
        .into(holder.profile);
  }


  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView profile;


    public ViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.bookmarks_event_title);
      profile = (ImageView) itemView.findViewById(R.id.bookmarks_event_icon);

    }
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    if (!super.equals(o)) return false;

    BookmarkItem that = (BookmarkItem) o;

    if (!bookmarkTitle.equals(that.bookmarkTitle)) return false;
    return eventFacebookUrl != null ? eventFacebookUrl.equals(that.eventFacebookUrl) : that.eventFacebookUrl == null;

  }

  @Override
  public int hashCode() {
    int result = super.hashCode();
    result = 31 * result + bookmarkTitle.hashCode();
    result = 31 * result + (eventFacebookUrl != null ? eventFacebookUrl.hashCode() : 0);
    return result;
  }
}
