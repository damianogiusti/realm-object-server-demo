package com.damianogiusti.realmobjectserverdemo.app.di;

import android.content.Context;

import com.damianogiusti.realmobjectserverdemo.app.login.LoginActivity;
import com.damianogiusti.realmobjectserverdemo.app.main.MainActivity;
import com.damianogiusti.realmobjectserverdemo.domain.executors.DatabaseSerialThreadExecutor;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity loginActivity);

    void inject(MainActivity mainActivity);

    Context context();

    DatabaseSerialThreadExecutor databaseSerialThreadExecutor();
}
