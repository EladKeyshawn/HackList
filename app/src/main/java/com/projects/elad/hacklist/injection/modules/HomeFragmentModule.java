package com.projects.elad.hacklist.injection.modules;

import com.projects.elad.hacklist.injection.scopes.PerFragment;
import com.projects.elad.hacklist.presentation.presenters.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */
@Module
public class HomeFragmentModule {


    @Provides
    @PerFragment
    public HomePresenter providePresenter() {
        return new HomePresenter();
    }
}
