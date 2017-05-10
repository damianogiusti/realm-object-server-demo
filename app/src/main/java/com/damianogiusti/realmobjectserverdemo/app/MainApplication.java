package com.damianogiusti.realmobjectserverdemo.app;

import android.app.Application;

import com.damianogiusti.realmobjectserverdemo.BuildConfig;
import com.damianogiusti.realmobjectserverdemo.app.di.ApplicationComponent;
import com.damianogiusti.realmobjectserverdemo.app.di.ApplicationModule;
import com.damianogiusti.realmobjectserverdemo.app.di.DaggerApplicationComponent;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class MainApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        createApplicationComponent();

        Realm.init(this);
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void createApplicationComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
