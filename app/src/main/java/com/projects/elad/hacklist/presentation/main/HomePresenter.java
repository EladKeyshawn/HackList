package com.projects.elad.hacklist.presentation.main;

import android.content.Context;
import android.widget.Toast;

import com.projects.elad.hacklist.MainActivity;
import com.projects.elad.hacklist.adapters.HackEvent;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.presentation.base.BasePresenter;
import com.projects.elad.hacklist.util.Mappers;
import com.projects.elad.hacklist.util.RxUtil;
import com.projects.elad.hacklist.util.UsefulFunctions;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class HomePresenter extends BasePresenter<HomeMvpView> {

    private DataManager dataManager;
    private HashMap<Integer, Subscription> subscriptions;

    @Inject Context context;

    public HomePresenter(DataManager dataManager, Context context) {
        this.dataManager = dataManager;
        subscriptions = new HashMap<>();
        this.context = context;
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
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .map(v -> v.entrySet().iterator().next().getValue())
                            .subscribe(new Subscriber<List<HackEvent>>() {
                                @Override
                                public void onCompleted() {
                                    Toast.makeText(context, "Fetch events completed", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(List<HackEvent> events) {
                                    getMvpView().showHackEvents(Mappers.mapHackEventsToListItems(events,context));
                                }
                            }));

            month++;
        }

    }

}
