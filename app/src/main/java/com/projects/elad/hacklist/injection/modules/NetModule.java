package com.projects.elad.hacklist.injection.modules;

import com.projects.elad.hacklist.data.api.HacklistService;
import com.projects.elad.hacklist.util.CurrDate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
