package com.projects.elad.hacklist.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.projects.elad.hacklist.R;

public class ListItem extends AbstractItem<ListItem, ListItem.ViewHolder> {


  String title;
  String startDate;
  boolean travel;


  public String getStartDate() {
    return startDate;
  }

  public String getTitle() {
    return title;
  }

  public boolean getTravel() {
    return travel;
  }

/*   Here starts fast adapter implementation   */

  @Override
  public int getType() {
    return 0;
  }

  @Override
  public int getLayoutRes() {
    return R.layout.list_card_item;
  }

  @Override
  public void bindView(ListItem.ViewHolder holder) {
    //call super so the selection is already handled for you
    super.bindView(holder);

    holder.title.setText(getTitle());
    holder.startDate.setText(getStartDate());

  if (!getTravel()) {
      holder.travelIcon.setImageResource(R.drawable.ic_x_red);
    }

  }


  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView startDate;
    ImageView travelIcon;


    public ViewHolder(View itemView) {
      super(itemView);

      title = (TextView) itemView.findViewById(R.id.list_item_title);
      startDate = (TextView) itemView.findViewById(R.id.list_item_date);
      travelIcon = (ImageView) itemView.findViewById(R.id.list_item_travel_tick);
    }
  }


}
