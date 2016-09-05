package com.projects.elad.hacklist.fragments;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.adapters.HackEvent;
import com.projects.elad.hacklist.adapters.HacklistApi;
import com.projects.elad.hacklist.adapters.ListItem;
import com.projects.elad.hacklist.db.EventBookmark;
import com.projects.elad.hacklist.utils.Constants;
import com.projects.elad.hacklist.utils.UsefulFunctions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit.RestAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements  FastAdapter.OnLongClickListener, SearchView.OnQueryTextListener{


  @BindView(R.id.all_hackathons_list)
  RecyclerView hackEventsList;
  @BindView(R.id.fragment_home_bottomsheet)
  BottomSheetLayout bottomsheet;
  private FastItemAdapter fastAdapter;
  private Context context;
  private ArrayList<ListItem> listItems;
  private List<HackEvent> eventsFromFeed;
  private HacklistApi serverInterface;
  private SearchView searchBox;
  private Menu ourOptionsMenu;
  private ListItem bottomSheetEventItem = null;
  public FragmentHome() {
    // Required empty public constructor
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_travel_only: {
        if (item.isChecked()) { // unchecking it now
          item.setChecked(false);
          // TODO: unapply filter
          fastAdapter.filter("");
        } else {
          item.setChecked(true);
          // TODO: apply filter
          fastAdapter.filter("travel"); // filter out all that contain "no"
        }
        return true;
      }
      default:
        return super.onOptionsItemSelected(item);
    }
  }


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    ourOptionsMenu = menu;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    context = super.getActivity();

    View ourView = inflater.inflate(R.layout.fragment_home, container, false);
    ButterKnife.bind(this, ourView);

    listItems = new ArrayList<>();

    eventsFromFeed = new ArrayList<>();

    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(false);
    fastAdapter.withSelectable(false);
    fastAdapter.withOnLongClickListener(this);
    fastAdapter.withOnClickListener(new FastAdapter.OnClickListener<ListItem>() {
      @Override
      public boolean onClick(View v, IAdapter<ListItem> adapter, ListItem item, int position) {
        boolean itemSaved = false;
        if (isEventSaved(item.getTitle())){
          itemSaved = true;
        }
        popBottomSheet(item, itemSaved);
        return true;
      }
    });
    fastAdapter.withFilterPredicate(new IItemAdapter.Predicate<ListItem>() {
      @Override
      public boolean filter(ListItem item, CharSequence constraint) {

        if (constraint.toString().length() > 0) {
          switch (constraint.toString()) {
            case "travel":
              return item.getTravel().equals("no") || item.getTravel().equals("unknown");

            default:
//              ourOptionsMenu.getItem(R.id.action_travel_only).setChecked(false);
              return !item.getTitle().toLowerCase().contains(constraint.toString().toLowerCase());

          }
        } else {
          return true;
        }


      }
    });

    hackEventsList.setLayoutManager(new LinearLayoutManager(context));
    hackEventsList.setAdapter(fastAdapter);

    searchBox = (SearchView) getActivity().findViewById(R.id.trip_search_edit);
    searchBox.clearFocus();

    searchBox.setOnQueryTextListener(this);


    return ourView;
  }

  private boolean isEventSaved(String title) {
    ArrayList<EventBookmark> bookmark = (ArrayList<EventBookmark>) EventBookmark.find(EventBookmark.class, "event_title = ?", title);
    return !(bookmark.size() == 0);
  }

  private void popBottomSheet(final ListItem item, boolean itemSaved) {
    View.OnClickListener bottomSheetClickListener = new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        switch (view.getId()) {
          case R.id.bottom_sheet_save:
            bottomsheet.dismissSheet();
            EventBookmark newRecord = new EventBookmark(item.getTitle(), item);
            newRecord.save();
            Snackbar.make(view,"Saved", Snackbar.LENGTH_LONG).show();
            break;
          case R.id.bottom_sheet_webview:
            bottomsheet.dismissSheet();
            openWebsiteDialog(item.getWebsite());
            break;
          case R.id.bottom_sheet_apply:
            bottomsheet.dismissSheet();
            goToWebsiteOnBrowser();
        }
      }
    };
    bottomsheet.setPeekSheetTranslation(1200);
    bottomsheet.showWithSheetView(LayoutInflater.from(context).inflate(R.layout.bottomsheet_event_item, bottomsheet, false));
    ImageView eventPic = (ImageView) bottomsheet.findViewById(R.id.bottom_sheet_event_logo);
    Button saveBtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_save);
    Button webviewBtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_webview);
    Button applyButtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_apply);
    if (itemSaved) {
      saveBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_selected_bottomsheet,0,0);
    }

    Picasso.with(context)
        .load(item.getFacebookProfileLink())
        .placeholder(R.mipmap.ic_launcher)
        .into(eventPic);
    saveBtn.setOnClickListener( bottomSheetClickListener);
    webviewBtn.setOnClickListener(bottomSheetClickListener);
    applyButtn.setOnClickListener(bottomSheetClickListener);
  }

  private void goToWebsiteOnBrowser() {
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


//    listItems.add(new ListItem("HackMIT", "17-18 aug", false));
//    listItems.add(new ListItem("YHack", "17-18 aug", true));
//    listItems.add(new ListItem("MHacks", "17-18 aug", false));
//    fastAdapter.add(listItems);

    buildRESTAdapter();

    Calendar calender = Calendar.getInstance();
    int currYear = calender.get(Calendar.YEAR);
    int currMonth = calender.get(Calendar.MONTH);
    HackEventRetrievalCoordinator(currYear, currMonth);


  }

  private void buildRESTAdapter() {
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setEndpoint(Constants.HACKALIST_BASE_URL)
        .build();

    serverInterface = restAdapter.create(HacklistApi.class);

  }





  public void HackEventRetrievalCoordinator(int year, int month) {

    String currentYear = String.valueOf(year);
    String currentMonth = UsefulFunctions.getStringForMonthInt(month);

    getHackEventList(currentYear, currentMonth, year, month);

  }

  public void getHackEventList(final String year, final String month, final int yearInt, final int monthInt) {

    serverInterface.getMonthObject(year, month)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Map<String, List<HackEvent>>>() {
          @Override
          public void onCompleted() {
            if (monthInt + 1 <= 11) {
              int nextMonth = monthInt + 1;
              HackEventRetrievalCoordinator(yearInt, nextMonth);
            }
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(Map<String, List<HackEvent>> stringListMap) {
            for (Map.Entry<String, List<HackEvent>> entry : stringListMap.entrySet()) {
              eventsFromFeed.addAll(entry.getValue());
            }

            addHackEventsToListAdapter(year);
          }
        });
  }


  private void addHackEventsToListAdapter(String year) {
    ArrayList tempItems = new ArrayList();
    for (HackEvent event : eventsFromFeed) {
      ListItem item = new ListItem(context, event.getTitle(), year, event.getStartDate(), event.getEndDate(),
          event.getHost(), event.getSize(), event.getLength(),
          event.getTravel(), event.getPrize(), event.getFacebookURL(), event.getUrl());
      tempItems.add(item);
    }


    fastAdapter.add(tempItems);

    tempItems.clear();
    eventsFromFeed.clear();

  }


  @Override
  public void onDestroyView() {
    super.onDestroyView();
  }

  @Override
  public boolean onLongClick(View v, IAdapter adapter, IItem item, int position) {
    Toast.makeText(context, "long click", Toast.LENGTH_SHORT).show();
    return true;
  }


  @Override
  public boolean onQueryTextSubmit(String query) {
    return false;
  }

  @Override
  public boolean onQueryTextChange(String newText) {
    fastAdapter.filter(newText);
    return true;
  }


  public void openWebsiteDialog(String url) {


    WebView webview = (WebView) LayoutInflater.from(getContext()).inflate(R.layout.website_webview, null);
    webview.loadUrl(url);
    webview.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return false;
      }
    });
    AlertDialog mAlertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
        .setView(webview)
        .setPositiveButton(android.R.string.ok, null)
        .show();


  }

}
