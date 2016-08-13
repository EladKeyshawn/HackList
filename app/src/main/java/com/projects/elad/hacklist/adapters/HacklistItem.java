package com.projects.elad.hacklist.adapters;


import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mikepenz.fastadapter.items.AbstractItem;
import com.projects.elad.hacklist.R;

public class HacklistItem  extends AbstractItem<HacklistItem, HacklistItem.ViewHolder> {


  @SerializedName("title")
  @Expose
  private String title;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("startDate")
  @Expose
  private String startDate;
  @SerializedName("endDate")
  @Expose
  private String endDate;
  @SerializedName("year")
  @Expose
  private String year;
  @SerializedName("city")
  @Expose
  private String city;
  @SerializedName("host")
  @Expose
  private String host;
  @SerializedName("length")
  @Expose
  private String length;
  @SerializedName("size")
  @Expose
  private String size;
  @SerializedName("travel")
  @Expose
  private String travel;
  @SerializedName("prize")
  @Expose
  private String prize;
  @SerializedName("highSchoolers")
  @Expose
  private String highSchoolers;
  @SerializedName("facebookURL")
  @Expose
  private String facebookURL;
  @SerializedName("twitterURL")
  @Expose
  private String twitterURL;
  @SerializedName("googlePlusURL")
  @Expose
  private String googlePlusURL;
  @SerializedName("notes")
  @Expose
  private String notes;

  public String getStartDate() {
    return startDate;
  }

  public String getTitle() {
    return title;
  }

  public String getTravel() {
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
  public void bindView(HacklistItem.ViewHolder holder) {
    //call super so the selection is already handled for you
    super.bindView(holder);

    holder.title.setText(getTitle());
    holder.startDate.setText(getStartDate());

    if (getTravel().equals("no")) {
      holder.travelIcon.setImageResource(R.drawable.ic_x_red);
    }

  }


  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    TextView startDate;
    ImageView travelIcon;


    public ViewHolder(View itemView) {
      super(itemView);
//      ourFont = Typeface.createFromAsset(itemView.getResources().getAssets(), "fonts/main_font_bold.ttf");

      title = (TextView) itemView.findViewById(R.id.list_item_title);
      startDate = (TextView) itemView.findViewById(R.id.list_item_date);
      travelIcon = (ImageView) itemView.findViewById(R.id.list_item_travel_tick);
    }
  }


}
