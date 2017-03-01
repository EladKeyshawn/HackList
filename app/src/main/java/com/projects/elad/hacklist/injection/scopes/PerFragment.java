package com.projects.elad.hacklist.injection.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by EladKeyshawn on 01/03/2017.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}
