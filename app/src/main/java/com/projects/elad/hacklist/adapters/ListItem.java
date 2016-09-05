package com.projects.elad.hacklist.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikepenz.fastadapter.items.AbstractItem;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.utils.Constants;
import com.projects.elad.hacklist.utils.UsefulFunctions;
import com.squareup.picasso.Picasso;

public class ListItem extends AbstractItem<ListItem, ListItem.ViewHolder> {


  Context context;
  String title;
  String year;
  String startDate;
  String endDate;
  String website;
  String facebookProfileLink;
  String host;
  //  String location;
  String people;
  String duration;


  String travel;
  String prizes;

  String facebookUrl;


  public ListItem(Context context, String title, String year, String startDate, String endDate, String host,
                  String people, String duration, String travel, String prizes, String facebookUrl, String website) {
    this.context = context;
    this.title = title;
    this.year = year;
    this.startDate = startDate;
    this.endDate = endDate;
    this.host = host;
    this.people = people;
    this.duration = duration;
    this.travel = travel;
    this.prizes = prizes;
    this.website = website;
    this.facebookUrl = facebookUrl;
//    facebookProfileLink = Constants.FACEBOOK_API_GET_PAGE_PICTURE + UsefulFunctions.getPageIdFromUrl(facebookUrl);

  }


  public String getFacebookProfileLink() {
    return facebookProfileLink;
  }

  public String getWebsite() {
    return website;
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

  public String getTravel() {
    return travel;
  }


  public String getHost() {
    return host;
  }


  public String getPeople() {
    return people;
  }

  public String getDuration() {
    return duration;
  }


  public String getPrizes() {
    return prizes;
  }

  public String getFacebookUrl() {
    return facebookUrl;
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
    super.bindView(holder);

    holder.title.setText(getTitle());
    holder.startDate.setText(getStartDate());
    holder.endDate.setText(getEndDate());
    holder.host.setText(getHost());
//    holder.location.setText(getLocation());
    holder.people.setText(getPeople());
    holder.duration.setText(getDuration());
    holder.year.setText("- " + year);


    switch (travel) {
      case "no":
        holder.travelIcon.setImageResource(R.drawable.ic_x_red);
        break;
      case "unknown":
        holder.travelIcon.setImageResource(R.drawable.ic_question);
        break;
      case "yes":
        holder.travelIcon.setImageResource(R.drawable.ic_ok_tick);
        break;
    }
    switch (getPrizes()) {
      case "no":
        holder.prizesIcon.setImageResource(R.drawable.ic_x_red);
        break;
      case "unknown":
        holder.prizesIcon.setImageResource(R.drawable.ic_question);
        break;
      case "yes":
        holder.prizesIcon.setImageResource(R.drawable.ic_ok_tick);
        break;
    }


    Picasso.with(context)
        //        .load("https://hackthenorth.com/2014/img/logo.png")
        .load(UsefulFunctions.getPageIdFromUrl(facebookUrl))
        .placeholder(R.mipmap.ic_launcher)
        .into(holder.profile);


  }
    protected static class ViewHolder extends RecyclerView.ViewHolder {
      TextView title;
      TextView year;
      TextView startDate;
      TextView endDate;
      TextView host;
      //    TextView location;
      TextView people;
      TextView duration;

      ImageView travelIcon;
      ImageView prizesIcon;
      ImageView profile;


      public ViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.list_item_title);
        startDate = (TextView) itemView.findViewById(R.id.list_item_start_date);
        endDate = (TextView) itemView.findViewById(R.id.list_item_end_date);
        host = (TextView) itemView.findViewById(R.id.list_item_host);
        year = (TextView) itemView.findViewById(R.id.list_item_year);
        travelIcon = (ImageView) itemView.findViewById(R.id.list_item_travel_tick);
        prizesIcon = (ImageView) itemView.findViewById(R.id.list_item_prize_tick);
        people = (TextView) itemView.findViewById(R.id.list_item_size);
        duration = (TextView) itemView.findViewById(R.id.list_item_length);
        profile = (ImageView) itemView.findViewById(R.id.list_item_icon);

      }
    }


  }
