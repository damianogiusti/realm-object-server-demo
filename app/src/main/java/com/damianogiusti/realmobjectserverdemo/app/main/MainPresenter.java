package com.damianogiusti.realmobjectserverdemo.app.main;

import com.damianogiusti.realmobjectserverdemo.app.mvp.BasePresenter;
import com.damianogiusti.realmobjectserverdemo.app.mvp.Presenter;
import com.damianogiusti.realmobjectserverdemo.app.mvp.model.RealmViewModel;
import com.damianogiusti.realmobjectserverdemo.data.ROSRepository;
import com.damianogiusti.realmobjectserverdemo.domain.LoginManager;
import com.damianogiusti.realmobjectserverdemo.domain.adapter.DomainModelAdapter;
import com.damianogiusti.realmobjectserverdemo.domain.model.RealmDomainModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Subscription;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class MainPresenter extends BasePresenter implements Presenter<MainView> {

    private ROSRepository rosRepository;
    private LoginManager loginManager;
    private DomainModelAdapter domainModelAdapter;

    private MainView mainView;

    @Inject
    public MainPresenter(ROSRepository rosRepository, LoginManager loginManager, DomainModelAdapter domainModelAdapter) {
        this.rosRepository = rosRepository;
        this.loginManager = loginManager;
        this.domainModelAdapter = domainModelAdapter;
    }

    @Override
    public void create(MainView view) {
        this.mainView = view;

        Subscription subscription = rosRepository.getAllRealms()
                .map(domainModelAdapter::convertToDomainModel)
                .subscribe(this::onRealmsListReceived, mainView::showError);
        compositeSubscription.add(subscription);
    }

    private void onRealmsListReceived(List<RealmDomainModel> realmDomainModels) {
        List<RealmViewModel> realmViewModels = convertToRealmViewModel(realmDomainModels);
        mainView.showItemsList(realmViewModels);
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.mainView = null;
        this.compositeSubscription.clear();
        this.rosRepository.close();
    }


    public void logoutUser() {
        loginManager.logoutCurrentUser().subscribe(mainView::navigateToLogin, mainView::showError);
    }

    public void createNewItem() {
        mainView.showNewItemAddScreen();
    }

    public void saveNewItem(String realmName) {
        Subscription subscription = rosRepository.saveRealm(domainModelAdapter.createRealmDomainModel(realmName))
                .subscribe(() -> {}, mainView::showError);
        compositeSubscription.add(subscription);
    }

    public List<RealmViewModel> convertToRealmViewModel(List<RealmDomainModel> realmDomainModels) {
        List<RealmViewModel> list = new ArrayList<>();
        for (RealmDomainModel realmDomainModel : realmDomainModels) {
            list.add(new RealmViewModel(realmDomainModel.getRealmId(), realmDomainModel.getName(), realmDomainModel.getEndpoint()));
        }
        return list;
    }
}
