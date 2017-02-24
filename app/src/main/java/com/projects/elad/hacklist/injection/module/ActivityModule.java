package com.projects.elad.hacklist.injection.module;

import android.app.Activity;
import android.content.Context;

import com.projects.elad.hacklist.injection.ActivityContext;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

@Module
public class ActivityModule {

    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }

    @Provides
    @ActivityContext
    Context providesContext() {
        return mActivity;
    }
}
