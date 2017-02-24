package com.projects.elad.hacklist.injection.component;

import android.app.Application;
import android.content.Context;

import com.projects.elad.hacklist.adapters.HacklistService;
import com.projects.elad.hacklist.data.DataManager;
import com.projects.elad.hacklist.injection.ApplicationContext;
import com.projects.elad.hacklist.injection.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {


    @ApplicationContext
    Context context();
    Application application();
    HacklistService ribotsService();
    DataManager dataManager();

}