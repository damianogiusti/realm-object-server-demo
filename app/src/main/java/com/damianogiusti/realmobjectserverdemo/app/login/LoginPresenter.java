package com.damianogiusti.realmobjectserverdemo.app.login;

import android.text.TextUtils;

import com.damianogiusti.realmobjectserverdemo.app.mvp.BasePresenter;
import com.damianogiusti.realmobjectserverdemo.app.mvp.Presenter;
import com.damianogiusti.realmobjectserverdemo.data.CredentialsRepository;
import com.damianogiusti.realmobjectserverdemo.domain.LoginManager;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class LoginPresenter extends BasePresenter implements Presenter<LoginView> {

    private LoginManager loginManager;
    private CredentialsRepository credentialsRepository;
    private LoginView loginView;

    @Inject
    public LoginPresenter(LoginManager loginManager, CredentialsRepository credentialsRepository) {
        this.loginManager = loginManager;
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    public void create(LoginView view) {
        this.loginView = view;

        if (loginManager.isLoggedIn()) {
            loginView.navigateToHome();
        }
    }

    @Override
    public void resume() {
        Subscription subscription = this.credentialsRepository.getUsername()
                .subscribe(loginView::showLastUsedUsername, loginView::showError);
        compositeSubscription.add(subscription);
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.loginView = null;
        this.compositeSubscription.clear();
    }

    public void loginUser(CharSequence username, CharSequence password) {

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            loginView.showError(error("Invalid credentials"));
            return;
        }
        Subscription saveUsernameSubscription = credentialsRepository.saveUsername(username.toString()).onErrorComplete().subscribe();
        compositeSubscription.add(saveUsernameSubscription);

        Subscription loginSubscription = loginManager.loginUser(username, password)
                .map(syncUser -> String.format("Logged user with ID=%s", syncUser.getIdentity()))
                .doOnNext(loginView::showMessage)
                .subscribe(__ -> loginView.navigateToHome(), loginView::showError);
        compositeSubscription.add(loginSubscription);
    }
}
