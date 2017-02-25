package com.projects.elad.hacklist;

import android.app.Application;
import android.content.Context;


/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class HacklistApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static HacklistApplication get(Context context) {
        return (HacklistApplication) context.getApplicationContext();
    }


}
