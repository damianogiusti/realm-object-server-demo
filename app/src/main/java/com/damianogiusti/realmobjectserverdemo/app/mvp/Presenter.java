package com.damianogiusti.realmobjectserverdemo.app.mvp;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface Presenter<T> {

    void create(T view);

    void resume();

    void pause();

    void destroy();
}
