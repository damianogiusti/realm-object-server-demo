package com.damianogiusti.realmobjectserverdemo.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.damianogiusti.realmobjectserverdemo.app.di.ApplicationComponent;

import butterknife.ButterKnife;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInject();
    }

    protected abstract void onInject();

    protected <T extends BaseActivity> void bindViews(T activity) {
        ButterKnife.bind(activity);
    }

    @Override
    public MainApplication getApplicationContext() {
        return (MainApplication) super.getApplicationContext();
    }

    protected ApplicationComponent getApplicationComponent() {
        return getApplicationContext().getApplicationComponent();
    }
}
