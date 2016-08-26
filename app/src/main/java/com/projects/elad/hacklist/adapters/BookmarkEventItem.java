package com.projects.elad.hacklist.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.projects.elad.hacklist.R;

public class BookmarkEventItem extends AbstractItem<BookmarkEventItem, BookmarkEventItem.ViewHolder> {


  private String bookmarkTitle;


  public BookmarkEventItem(String bookmarkTitle) {
    this.bookmarkTitle = bookmarkTitle;
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
  public void bindView(BookmarkEventItem.ViewHolder holder) {
    super.bindView(holder);

    holder.title.setText(bookmarkTitle);

  }


  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;



    public ViewHolder(View itemView) {
      super(itemView);
      title = (TextView) itemView.findViewById(R.id.bookmark_event_title);


    }
  }

}
