package com.projects.elad.hacklist;

import android.app.Application;
import android.content.Context;

import com.projects.elad.hacklist.injection.components.AppComponent;
import com.projects.elad.hacklist.injection.components.DaggerAppComponent;
import com.projects.elad.hacklist.injection.modules.AppModule;


/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class HacklistApplication extends Application {

    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HacklistApplication get(Context context) {
        return (HacklistApplication) context.getApplicationContext();
    }

    public AppComponent getComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .build();

        }
        return appComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(AppComponent appComponent) {
        this.appComponent = appComponent;
    }



}
