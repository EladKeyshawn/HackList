package com.projects.elad.hacklist.injection.components;

import com.projects.elad.hacklist.injection.modules.BookmarksFragmentModule;
import com.projects.elad.hacklist.injection.scopes.PerFragment;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentBookmarks;

import dagger.Component;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */

@PerFragment
@Component(modules = BookmarksFragmentModule.class)
public interface BookmarksFragmentComponent {
    void inject(FragmentBookmarks fragmentHome);
}
