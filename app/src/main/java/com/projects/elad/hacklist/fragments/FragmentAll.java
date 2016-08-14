package com.projects.elad.hacklist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import java.util.ArrayList;
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
public class FragmentAll extends Fragment implements FastAdapter.OnClickListener, FastAdapter.OnLongClickListener {


  @BindView(R.id.all_hackathons_list)
  RecyclerView hackEventsList;
  private FastItemAdapter fastAdapter;
  private Context context;
  private ArrayList<ListItem> listItems;
  private List<HackEvent> eventsFromFeed;
  private ContextMenuDialogFragment mMenuDialogFragment;

  public FragmentAll() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setHasOptionsMenu(true);

  }

  @Override
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    context = super.getActivity();

    View ourView = inflater.inflate(R.layout.fragment_view_all, container, false);
    ButterKnife.bind(this, ourView);

    listItems = new ArrayList<>();

    eventsFromFeed = new ArrayList<>();


    return ourView;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {


    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(true);
    fastAdapter.withSelectable(true);
    fastAdapter.withOnClickListener(this);
    fastAdapter.withOnLongClickListener(this);
    fastAdapter.withFilterPredicate(new IItemAdapter.Predicate<ListItem>() {
      @Override
      public boolean filter(ListItem item, CharSequence constraint) {
        return item.getTravel().equals(constraint.toString());
      }
    });
    hackEventsList.setLayoutManager(new LinearLayoutManager(context));
    hackEventsList.setAdapter(fastAdapter);


//    listItems.add(new ListItem("HackMIT", "17-18 aug", false));
//    listItems.add(new ListItem("YHack", "17-18 aug", true));
//    listItems.add(new ListItem("MHacks", "17-18 aug", false));
//    fastAdapter.add(listItems);

    getHackEventList();


  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_travel_only:
        if (item.isChecked()) {
          item.setChecked(false);
          // TODO: unapply filter
          fastAdapter.filter("");
        } else {
          item.setChecked(true);
          // TODO: apply filter
          fastAdapter.filter("no"); // filter out all that contain "no"
        }
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  private void addHackEventsToListAdapter() {
    for (HackEvent event : eventsFromFeed) {
//      boolean travel = event.getTravel().equals("yes");
//      boolean prizes = event.getPrize().equals("yes");
      ListItem item = new ListItem(context, event.getTitle(), event.getStartDate(), event.getEndDate(),
          event.getHost(), event.getSize(), event.getLength(), event.getTravel(), event.getPrize(), event.getFacebookURL());
      fastAdapter.add(item);
    }

  }

  @Override
  public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
    return false;
  }


  public void getHackEventList() { // remember to move to RxJava

    RestAdapter restAdapter = new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setEndpoint(Constants.HACKALIST_BASE_URL)
        .build();


    HacklistApi serverInterface = restAdapter.create(HacklistApi.class);
    serverInterface.getMonthObject("2016", "02")
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.newThread())
        .subscribe(new Subscriber<Map<String, List<HackEvent>>>() {
          @Override
          public void onCompleted() {
            addHackEventsToListAdapter();

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(Map<String, List<HackEvent>> stringListMap) {
            for (Map.Entry<String, List<HackEvent>> entry : stringListMap.entrySet()) {
              eventsFromFeed.addAll(entry.getValue());
            }
          }
        });
  }

  @Override
  public boolean onLongClick(View v, IAdapter adapter, IItem item, int position) {
    return false;
  }


}
