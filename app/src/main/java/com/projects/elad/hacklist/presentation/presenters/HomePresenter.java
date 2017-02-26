package com.projects.elad.hacklist.presentation.presenters;

import android.content.Context;

import com.projects.elad.hacklist.HacklistApplication;
import com.projects.elad.hacklist.data.api.HackEvent;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.presentation.views.HomeMvpView;
import com.projects.elad.hacklist.presentation.base.BasePresenter;
import com.projects.elad.hacklist.presentation.main.adapters.ListItem;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentHome;
import com.projects.elad.hacklist.util.Mappers;
import com.projects.elad.hacklist.util.RxUtil;
import com.projects.elad.hacklist.util.Utils;

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

    @Inject DataManager dataManager;
    private HashMap<Integer, Subscription> subscriptions;
    @Inject Context context;

    public HomePresenter() {
        subscriptions = new HashMap<>();
    }


    @Override
    public void attachView(HomeMvpView mvpView) {
        super.attachView(mvpView);
        ((HacklistApplication)(((FragmentHome)getMvpView()).getActivity().getApplication())).getComponent().inject(this);

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
                    dataManager.getHacklistService().getMonthObject(year, Utils.getStringForMonthInt(month))
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.newThread())
                            .map(v -> v.entrySet().iterator().next().getValue())
                            .subscribe(new Subscriber<List<HackEvent>>() {
                                @Override
                                public void onCompleted() {
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


    public void saveListItem(ListItem item) {
        dataManager.saveBookmark(Mappers.mapHomeListItemToBookmarkDbEntity(item));
    }

    public void deleteListItem(String title) {
        dataManager.deleteBookmark(title);
        loadHackEvent();
    }

}
