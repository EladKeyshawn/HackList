package com.projects.elad.hacklist.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.FastItemAdapter;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.adapters.HacklistApi;
import com.projects.elad.hacklist.adapters.ListItem;

import java.util.ArrayList;
import java.util.List;

import retrofit.RestAdapter;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAll extends Fragment implements FastAdapter.OnClickListener {


  private static final String SERVER_BASE_URL = "http://www.hackalist.org/api/1.0";
  private FastItemAdapter fastAdapter;
  private Context context;
  RecyclerView hackEventsList;
  private ArrayList<ListItem> hacklistItems;

  public FragmentAll() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    context = super.getActivity();
    hacklistItems = new ArrayList<>();

    Toast.makeText(context, "onCreateView called", Toast.LENGTH_SHORT).show();

    return inflater.inflate(R.layout.fragment_view_all, container, false);
  }


  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//    ButterKnife.bind(getActivity()); // check later for crashes
    Toast.makeText(context, "onViewCreate called", Toast.LENGTH_SHORT).show();

    fastAdapter = new FastItemAdapter();
    fastAdapter.withSelectOnLongClick(true);
    fastAdapter.withSelectable(true);
    fastAdapter.withOnClickListener(this);



    hackEventsList = (RecyclerView) view.findViewById(R.id.all_hackathons_list);
    hackEventsList.setLayoutManager(new LinearLayoutManager(context));
    hackEventsList.setAdapter(fastAdapter);

    Toast.makeText(context, "initialized recyclerview list and  adapter", Toast.LENGTH_SHORT).show();

    getHackEventList();

  }

  @Override
  public boolean onClick(View v, IAdapter adapter, IItem item, int position) {
    return false;
  }
  
  
  public void getHackEventList () {
    RestAdapter restAdapter = new RestAdapter.Builder()
        .setLogLevel(RestAdapter.LogLevel.FULL)
        .setEndpoint(SERVER_BASE_URL)
        .build();

    Toast.makeText(context, "Created rest adapter", Toast.LENGTH_SHORT).show();


    HacklistApi serverInterface = restAdapter.create(HacklistApi.class);
    serverInterface.getMonthObject("2016", "01")
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<List<ListItem>>() {
          @Override
          public void onCompleted() {

          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onNext(List<ListItem> hacklistItems) {
              fastAdapter.add(hacklistItems);
          }
        });


  }
}
