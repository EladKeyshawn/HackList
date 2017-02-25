package com.projects.elad.hacklist.injection.components;

import com.projects.elad.hacklist.injection.modules.AppModule;
import com.projects.elad.hacklist.injection.modules.NetModule;
import com.projects.elad.hacklist.injection.modules.PresentersModule;
import com.projects.elad.hacklist.presentation.main.BookmarksPresenter;
import com.projects.elad.hacklist.presentation.main.HomePresenter;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentBookmarks;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentHome;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class, PresentersModule.class})
public interface BaseComponent {
    void inject(FragmentHome fragmentHome);
    void inject(FragmentBookmarks fragmentBookmarks);
    void inject(HomePresenter homePresenter);
    void inject(BookmarksPresenter bookmarksPresenter);
}
