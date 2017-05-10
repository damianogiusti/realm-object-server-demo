package com.damianogiusti.realmobjectserverdemo.data;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class CredentialsRepository {

    private static final String USERNAME = "username";

    private SharedPreferences sharedPreferences;

    @Inject
    public CredentialsRepository(Context context) {
        sharedPreferences = context.getSharedPreferences("lru_credentials", Context.MODE_PRIVATE);
    }

    public Observable<String> getUsername() {
        return Observable.fromCallable(() -> {
            String username = sharedPreferences.getString(USERNAME, "");
            return username;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Completable saveUsername(String username) {
        return Completable.fromAction(() -> sharedPreferences.edit().putString(USERNAME, username).commit())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
