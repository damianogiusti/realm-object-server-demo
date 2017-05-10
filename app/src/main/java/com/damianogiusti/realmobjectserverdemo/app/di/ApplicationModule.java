package com.damianogiusti.realmobjectserverdemo.app.di;

import android.content.Context;

import com.damianogiusti.realmobjectserverdemo.app.MainApplication;
import com.damianogiusti.realmobjectserverdemo.domain.executors.DatabaseSerialThreadExecutor;
import com.damianogiusti.realmobjectserverdemo.domain.executors.RealmThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
@Module
public class ApplicationModule {

    private MainApplication mainApplication;

    public ApplicationModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    Context providesApplicationContext() {
        return mainApplication;
    }

    @Provides
    @Singleton
    DatabaseSerialThreadExecutor databaseSerialThreadExecutor(RealmThread executor) {
        return executor;
    }
}
