package com.damianogiusti.realmobjectserverdemo.domain.executors;


import rx.Scheduler;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface RxExecutor {
    Scheduler getScheduler();
}
