package com.projects.elad.hacklist.injection.modules;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
@Module
public class AppModule {
    protected final Application application;
    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    Context provideContext(){
        return application;
    }





}
