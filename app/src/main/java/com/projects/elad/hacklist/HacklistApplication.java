package com.projects.elad.hacklist;

import android.app.Application;
import android.content.Context;

import com.projects.elad.hacklist.injection.components.BaseComponent;
import com.projects.elad.hacklist.injection.components.DaggerBaseComponent;
import com.projects.elad.hacklist.injection.modules.AppModule;


/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class HacklistApplication extends Application {

    private BaseComponent baseComponent;
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HacklistApplication get(Context context) {
        return (HacklistApplication) context.getApplicationContext();
    }

    public  BaseComponent getComponent() {
        if (baseComponent == null) {
            baseComponent = DaggerBaseComponent.builder()
                    .appModule(new AppModule(this))
                    .build();

        }
        return baseComponent;
    }

    // Needed to replace the component with a test specific one
    public void setComponent(BaseComponent baseComponent) {
        this.baseComponent = baseComponent;
    }



}
