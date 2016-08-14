package com.projects.elad.hacklist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.adapters.HackEvent;
import com.projects.elad.hacklist.adapters.HacklistApi;
import com.projects.elad.hacklist.adapters.ListItem;
import com.projects.elad.hacklist.utils.Constants;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

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
  public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    context = super.getActivity();

    View ourView = inflater.inflate(R.layout.fragment_view_all, container, false);
    ButterKnife.bind(this,ourView);

    listItems = new ArrayList<>();

    eventsFromFeed = new ArrayList<>();


    return ourView;
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    setUpRefineMenu();


    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(true);
    fastAdapter.withSelectable(true);
    fastAdapter.withOnClickListener(this);
    fastAdapter.withOnLongClickListener(this);

    hackEventsList.setLayoutManager(new LinearLayoutManager(context));
    hackEventsList.setAdapter(fastAdapter);


//    listItems.add(new ListItem("HackMIT", "17-18 aug", false));
//    listItems.add(new ListItem("YHack", "17-18 aug", true));
//    listItems.add(new ListItem("MHacks", "17-18 aug", false));
//    fastAdapter.add(listItems);

    getHackEventList();


  }

  private void setUpRefineMenu() {
    MenuObject close = new MenuObject();
    close.setResource(R.drawable.ic_ok_tick);

    MenuObject send = new MenuObject("Only Travel");
    send.setResource(R.drawable.ic_x_red);

    int toolbarHeight;

    AppCompatActivity compatParentActivity = (AppCompatActivity) getActivity();
    if (compatParentActivity.getSupportActionBar() != null) {
       toolbarHeight = compatParentActivity.getSupportActionBar().getHeight();
    } else {
      toolbarHeight = 2;
    }
    List<MenuObject> menuObjects = new ArrayList<>();
    menuObjects.add(close);
    menuObjects.add(send);



    MenuParams menuParams = new MenuParams();
    menuParams.setActionBarSize(toolbarHeight);
    menuParams.setMenuObjects(menuObjects);
    menuParams.setClosableOutside(true);
    // set other settings to meet your needs
    mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);


  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    if(item.getItemId() == R.id.main_menu_action_refine) {
      mMenuDialogFragment.show(getFragmentManager(), "ContextMenuDialogFragment");

    }

    return super.onOptionsItemSelected(item);

  }

  private void addHackEventsToListAdapter() {
    for (HackEvent event : eventsFromFeed) {
      boolean travel = event.getTravel().equals("yes");
      boolean prizes = event.getPrize().equals("yes");
      ListItem item = new ListItem(context,event.getTitle(),event.getStartDate(),event.getEndDate(),
          event.getHost(), event.getSize(), event.getLength(), travel, prizes, event.getFacebookURL());
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
