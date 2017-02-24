package com.projects.elad.hacklist.presentation.main;

import android.content.Context;

import com.projects.elad.hacklist.adapters.ListItem;
import com.projects.elad.hacklist.presentation.base.MvpView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public interface HomeMvpView extends MvpView {
     void popBottomSheet(final ListItem item);
     void openWebsiteDialog(String url);
     void showHackEvents(List<ListItem> listItems);
     Context getContext();
     }


