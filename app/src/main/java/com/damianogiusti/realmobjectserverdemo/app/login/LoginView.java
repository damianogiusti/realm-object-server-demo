package com.damianogiusti.realmobjectserverdemo.app.login;

import com.damianogiusti.realmobjectserverdemo.app.mvp.View;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface LoginView extends View{
    void showLastUsedUsername(String username);

    void navigateToHome();
}
