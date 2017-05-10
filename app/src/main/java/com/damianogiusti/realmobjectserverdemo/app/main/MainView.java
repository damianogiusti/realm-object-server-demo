package com.damianogiusti.realmobjectserverdemo.app.main;

import com.damianogiusti.realmobjectserverdemo.app.mvp.View;
import com.damianogiusti.realmobjectserverdemo.app.mvp.model.RealmViewModel;

import java.util.List;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface MainView extends View {
    void navigateToLogin();

    void showNewItemAddScreen();

    void showItemsList(List<RealmViewModel> realmViewModels);
}
