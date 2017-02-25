package com.projects.elad.hacklist.injection.modules;

import com.projects.elad.hacklist.presentation.main.BookmarksPresenter;
import com.projects.elad.hacklist.presentation.main.HomePresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EladKeyshawn on 25/02/2017.
 */
@Module
public class PresentersModule {

    @Provides
    public HomePresenter provideHomePresenter(){
        return new HomePresenter();
    }

    @Provides
    public BookmarksPresenter provideBookmarksPresenter(){return new BookmarksPresenter();}
}
