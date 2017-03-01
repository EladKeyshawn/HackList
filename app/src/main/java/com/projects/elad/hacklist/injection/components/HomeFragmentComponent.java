package com.projects.elad.hacklist.injection.components;

import com.projects.elad.hacklist.injection.modules.HomeFragmentModule;
import com.projects.elad.hacklist.injection.scopes.PerFragment;
import com.projects.elad.hacklist.presentation.main.fragments.FragmentHome;

import dagger.Component;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */
@PerFragment
@Component(modules = {HomeFragmentModule.class})
public interface HomeFragmentComponent {
    void inject(FragmentHome fragmentHome);
}
