package com.damianogiusti.realmobjectserverdemo.domain;

import com.damianogiusti.realmobjectserverdemo.domain.config.Configuration;

import javax.inject.Inject;

import io.realm.SyncCredentials;
import io.realm.SyncUser;
import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class LoginManager extends BaseManager {

    private Configuration configuration;

    @Inject
    public LoginManager(Configuration configuration) {
        this.configuration = configuration;
    }

    public Observable<SyncUser> loginUser(CharSequence username, CharSequence password) {
        return Observable.defer(() -> {
            SyncCredentials syncCredentials = SyncCredentials.usernamePassword(username.toString(), password.toString());
            SyncUser login = SyncUser.login(syncCredentials, configuration.REALM_AUTH_URL);
            return Observable.just(login);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<SyncUser> loginNewUser(CharSequence username, CharSequence password) {
        return Observable.defer(() -> {
            SyncCredentials syncCredentials = SyncCredentials.usernamePassword(username.toString(), password.toString(), true);
            SyncUser login = SyncUser.login(syncCredentials, configuration.REALM_AUTH_URL);
            return Observable.just(login);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public boolean isLoggedIn() {
        return SyncUser.currentUser() != null && SyncUser.currentUser().isValid();
    }

    public Completable logoutCurrentUser() {
        return Completable.fromAction(() -> SyncUser.currentUser().logout())
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
