package com.projects.elad.hacklist.util;

import rx.Subscription;

/**
 * Created by EladKeyshawn on 24/02/2017.
 */

public class RxUtil {
    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}
