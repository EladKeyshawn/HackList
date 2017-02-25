package com.projects.elad.hacklist.injection.modules;

import com.projects.elad.hacklist.data.db.RealmBookmarksHelper;
import com.projects.elad.hacklist.data.remote.HacklistService;
import com.projects.elad.hacklist.util.CurrDate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
@Module
public class NetModule {
    private CurrDate currDate;

    public NetModule() {
        currDate = new CurrDate();
    }

    @Provides
    @Singleton
    public CurrDate provideCurrDate() {
        return currDate;
    }

    @Provides
    @Singleton
    public HacklistService provideHacklistService(){
        return HacklistService.ServiceCreator.newHacklistService();
    }


}
