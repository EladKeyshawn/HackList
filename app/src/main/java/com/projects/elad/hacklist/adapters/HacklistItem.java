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



  /*   Here starts fast adapter implementation   */

  @Override
  public int getType() {
    return 0;
  }

  @Override
  public int getLayoutRes() {
    return R.layout.list_card_item;
  }

  protected static class ViewHolder extends RecyclerView.ViewHolder {
    TextView direction;
    TextView agency;
    ImageView agencyIcon;
    Typeface ourFont;


    public ViewHolder(View itemView) {
      super(itemView);
//      ourFont = Typeface.createFromAsset(itemView.getResources().getAssets(), "fonts/main_font_bold.ttf");

    }
  }


}
