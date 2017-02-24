package com.projects.elad.hacklist.presentation.base;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}