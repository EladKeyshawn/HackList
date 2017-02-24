package com.projects.elad.hacklist.injection.component;

import com.projects.elad.hacklist.MainActivity;
import com.projects.elad.hacklist.injection.PerActivity;
import com.projects.elad.hacklist.injection.module.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

}
