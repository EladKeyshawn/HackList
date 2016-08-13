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
  String endDate;


  String host;
  String location;
  String people;
  String duration;


  boolean travel;
  boolean prizes;


  public ListItem(String title, String startDate, boolean travel) {
    this.title = title;
    this.startDate = startDate;
    this.travel = travel;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public String getTitle() {
    return title;
  }

  public boolean getTravel() {
    return travel;
  }


  public String getHost() {
    return host;
  }

  public String getLocation() {
    return location;
  }

  public String getPeople() {
    return people;
  }

  public String getDuration() {
    return duration;
  }


  public boolean getPrizes() {
    return prizes;
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
    holder.endDate.setText(getEndDate());
    holder.host.setText(getHost());
    holder.location.setText(getLocation());
    holder.people.setText(getPeople());
    holder.duration.setText(getDuration());


    if (!getTravel()) {
      holder.travelIcon.setImageResource(R.drawable.ic_x_red);
    }
    if (!getPrizes()) {
      holder.prizesIcon.setImageResource(R.drawable.ic_x_red);
    }

  }


  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView startDate;
    TextView endDate;
    TextView host;
    TextView location;
    TextView people;
    TextView duration;

    ImageView travelIcon;
    ImageView prizesIcon;


    public ViewHolder(View itemView) {
      super(itemView);

      title = (TextView) itemView.findViewById(R.id.list_item_title);
      startDate = (TextView) itemView.findViewById(R.id.list_item_start_date);
      endDate = (TextView) itemView.findViewById(R.id.list_item_end_date);
      host = (TextView) itemView.findViewById(R.id.list_item_host);
      location = (TextView) itemView.findViewById(R.id.list_item_location);
      travelIcon = (ImageView) itemView.findViewById(R.id.list_item_travel_tick);
      prizesIcon = (ImageView) itemView.findViewById(R.id.list_item_prize_tick);
      people = (TextView) itemView.findViewById(R.id.list_item_size);
      duration = (TextView) itemView.findViewById(R.id.list_item_length);


    }
  }


}
