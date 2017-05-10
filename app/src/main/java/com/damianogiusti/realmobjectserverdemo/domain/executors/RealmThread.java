package com.damianogiusti.realmobjectserverdemo.domain.executors;

import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;


/**
 * Created by Damiano Giusti on 09/05/17.
 */
@Singleton
public class RealmThread implements DatabaseSerialThreadExecutor {

    private Handler handler;
    private HandlerThread handlerThread;
    private Scheduler scheduler;

    @Inject
    public RealmThread() {
        handlerThread = new HandlerThread("RealmThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    @Override
    public Scheduler getScheduler() {
        if (scheduler == null) {
            scheduler = AndroidSchedulers.from(handlerThread.getLooper());
        }
        return scheduler;
    }

    @Override
    public void execute(@NonNull Runnable command) {
        handler.post(command);
    }
}
