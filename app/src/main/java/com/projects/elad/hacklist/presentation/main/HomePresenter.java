package com.projects.elad.hacklist.presentation.main;

import android.content.Context;

import com.projects.elad.hacklist.MainActivity;
import com.projects.elad.hacklist.adapters.HackEvent;
import com.projects.elad.hacklist.adapters.ListItem;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.presentation.base.BasePresenter;
import com.projects.elad.hacklist.util.RxUtil;
import com.projects.elad.hacklist.util.UsefulFunctions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {

    private DataManager dataManager;
    private HashMap<Integer, Subscription> subscriptions;
    @Inject private MainActivity context;

    public HomePresenter(DataManager dataManager) {
        this.dataManager = dataManager;
        subscriptions = new HashMap<>();
    }


    @Override
    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
    }

    @Override
    public void detachView() {
        super.detachView();
        unSubscribeAll();
    }

    void unSubscribeAll() {
        for (Subscription sub : subscriptions.values()) {
            RxUtil.unsubscribe(sub);
        }
        subscriptions.clear();
    }

    public void loadHackEvent() {
        checkViewAttached();
        unSubscribeAll();

        int month = dataManager.getDate().getCurrMonthInt();
        String year = dataManager.getDate().getCurrentYear();
        while (month < 12) {
            subscriptions.put(month,
                    dataManager.getHacklistService().getMonthObject(year, UsefulFunctions.getStringForMonthInt(month))
                            .map(new Func1<List<HackEvent>, List<ListItem>>() {
                                @Override
                                public List<ListItem> call(List<HackEvent> hackEvents) {
                                    List<ListItem> items = new ArrayList<ListItem>();
                                    for (HackEvent event : hackEvents) {
                                        items.add(ListItem.from(event, context));
                                    }
                                    return items;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .subscribe(new Subscriber<List<ListItem>>() {
                                @Override
                                public void onCompleted() {
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(List<ListItem> listItems) {
                                    if (!listItems.isEmpty()) {
                                        getMvpView().showHackEvents(listItems);
                                    }
                                }

                            }));
            month++;
        }

    }

}
