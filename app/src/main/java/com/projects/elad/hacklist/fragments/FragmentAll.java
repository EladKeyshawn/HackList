package com.projects.elad.hacklist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.IItemAdapter;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.adapters.HackEvent;
import com.projects.elad.hacklist.adapters.HacklistApi;
import com.projects.elad.hacklist.adapters.ListItem;
import com.projects.elad.hacklist.utils.Constants;
import com.projects.elad.hacklist.utils.UsefulFunctions;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

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
public class FragmentAll extends Fragment implements FastAdapter.OnClickListener, FastAdapter.OnLongClickListener, SearchView.OnQueryTextListener {


  @BindView(R.id.all_hackathons_list)
  RecyclerView hackEventsList;
  private FastItemAdapter fastAdapter;
  private Context context;
  private ArrayList<ListItem> listItems;
  private List<HackEvent> eventsFromFeed;
  private ContextMenuDialogFragment mMenuDialogFragment;
  private HacklistApi serverInterface;
  private SearchView searchBox;
  private Menu ourOptionsMenu;

  public FragmentAll() {
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

    View ourView = inflater.inflate(R.layout.fragment_view_all, container, false);
    ButterKnife.bind(this, ourView);

    listItems = new ArrayList<>();

    eventsFromFeed = new ArrayList<>();

    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(true);
    fastAdapter.withSelectable(true);
    fastAdapter.withOnClickListener(this);
    fastAdapter.withOnLongClickListener(this);
    fastAdapter.withFilterPredicate(new IItemAdapter.Predicate<ListItem>() {
      @Override
      public boolean filter(ListItem item, CharSequence constraint) {

        if(constraint.toString().length() > 0){
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
    searchBox.setOnCloseListener(new SearchView.OnCloseListener() {
      @Override
      public boolean onClose() {
        searchBox.clearFocus();

        return true;
      }
    });
    searchBox.setOnQueryTextListener(this);



    return ourView;
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


  @Override
  public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
    return false;
  }



  public void HackEventRetrievalCoordinator(int year, int month) {

    String currentYear = String.valueOf(year);
    String currentMonth = UsefulFunctions.getStringForMonthInt(month);

    getHackEventList(currentYear,currentMonth, year, month);

  }

  public void getHackEventList(final String year, final String month, final int yearInt, final int monthInt) {

    serverInterface.getMonthObject(year, month)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Map<String, List<HackEvent>>>() {
          @Override
          public void onCompleted() {
              if(monthInt + 1 <= 11) {
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
      ListItem item = new ListItem(context, event.getTitle(), year , event.getStartDate(), event.getEndDate(),
          event.getHost(), event.getSize(), event.getLength(), event.getTravel(), event.getPrize(), event.getFacebookURL());
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
    return false;
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
}
