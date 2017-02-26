package com.projects.elad.hacklist.presentation.views;

import android.content.Context;

import com.projects.elad.hacklist.presentation.base.MvpView;
import com.projects.elad.hacklist.presentation.main.adapters.ListItem;

import java.util.List;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public interface HomeMvpView extends MvpView {
     void openWebsiteDialog(String url);
     void showHackEvents(List<ListItem> listItems);
     Context getContext();
     }


