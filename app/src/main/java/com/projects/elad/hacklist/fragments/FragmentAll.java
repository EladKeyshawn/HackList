package com.projects.elad.hacklist.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.projects.elad.hacklist.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentAll extends Fragment {


  public FragmentAll() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_view_all, container, false);
  }

}
