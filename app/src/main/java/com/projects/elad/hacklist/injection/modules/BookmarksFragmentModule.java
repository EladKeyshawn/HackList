package com.projects.elad.hacklist.injection.modules;

import com.projects.elad.hacklist.injection.scopes.PerFragment;
import com.projects.elad.hacklist.presentation.presenters.BookmarksPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */

@Module
public class BookmarksFragmentModule {

    @PerFragment
    @Provides
    public BookmarksPresenter provideBookmarksPresenter() {
        return new BookmarksPresenter();
    }
}
