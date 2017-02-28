package com.projects.elad.hacklist.presentation.views;

import com.projects.elad.hacklist.presentation.base.MvpView;
import com.projects.elad.hacklist.presentation.main.adapters.ListItem;

import java.util.List;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */
public interface HomeMvpView extends MvpView {

    void showHackEvents(List<ListItem> listItems);
}
