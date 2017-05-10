package com.damianogiusti.realmobjectserverdemo.app.mvp;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public abstract class BasePresenter {

    protected CompositeSubscription compositeSubscription = new CompositeSubscription();

    protected Throwable error(String errorMessage) {
        return new Throwable(errorMessage);
    }
}
