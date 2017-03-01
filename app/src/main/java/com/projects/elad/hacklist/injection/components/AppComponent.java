package com.projects.elad.hacklist.injection.components;

import com.projects.elad.hacklist.injection.modules.AppModule;
import com.projects.elad.hacklist.injection.modules.NetModule;
import com.projects.elad.hacklist.injection.scopes.HacklistApplicationScope;
import com.projects.elad.hacklist.presentation.presenters.BookmarksPresenter;
import com.projects.elad.hacklist.presentation.presenters.HomePresenter;

import dagger.Component;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
@HacklistApplicationScope
@Component(modules = {AppModule.class, NetModule.class})
public interface AppComponent {
    void inject(HomePresenter homePresenter);
    void inject(BookmarksPresenter bookmarksPresenter);
}
