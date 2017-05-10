package com.damianogiusti.realmobjectserverdemo.app.mvp;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface View {
    void showError(Throwable error);

    void showMessage(String message);
}
