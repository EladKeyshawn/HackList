package com.projects.elad.hacklist.injection.module;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

import android.app.Application;
import android.content.Context;

import com.projects.elad.hacklist.adapters.HacklistService;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.injection.ApplicationContext;
import com.projects.elad.hacklist.presentation.main.HomePresenter;
import com.projects.elad.hacklist.util.CurrDate;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provide application-level dependencies.
 */
@Module
public class ApplicationModule {
    protected final Application mApplication;
    protected DataManager dataManager;
    protected HacklistService hacklistService;
    public ApplicationModule(Application application) {
        mApplication = application;
    }


    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    HacklistService provideRibotsService() {
        hacklistService =  HacklistService.ServiceCreator.newHacklistService();
        return hacklistService;
    }

    @Provides
    @Singleton
    DataManager provideDataManager() {
        return new DataManager(hacklistService,new CurrDate());
    }
    @Provides
    @Singleton
    HomePresenter provideHomePresenter() {
        return new HomePresenter(dataManager);
    }

}