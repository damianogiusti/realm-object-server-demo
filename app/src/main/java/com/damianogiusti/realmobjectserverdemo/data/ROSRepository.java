package com.damianogiusti.realmobjectserverdemo.data;

import com.damianogiusti.realmobjectserverdemo.data.adapter.DataModelAdapter;
import com.damianogiusti.realmobjectserverdemo.data.model.RealmDataModel;
import com.damianogiusti.realmobjectserverdemo.domain.config.Configuration;
import com.damianogiusti.realmobjectserverdemo.domain.executors.DatabaseSerialThreadExecutor;
import com.damianogiusti.realmobjectserverdemo.domain.model.RealmDomainModel;

import java.io.Closeable;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.SyncConfiguration;
import io.realm.SyncUser;
import rx.Completable;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class ROSRepository implements Closeable {

    private Realm realm;
    private Configuration configuration;
    private DatabaseSerialThreadExecutor databaseSerialThreadExecutor;
    private DataModelAdapter dataModelAdapter;

    @Inject
    public ROSRepository(DataModelAdapter dataModelAdapter, DatabaseSerialThreadExecutor databaseSerialThreadExecutor, Configuration configuration) {
        this.dataModelAdapter = dataModelAdapter;
        this.databaseSerialThreadExecutor = databaseSerialThreadExecutor;
        this.configuration = configuration;

        try {
            realm = Realm.getDefaultInstance();
        } catch (NullPointerException e) {
            SyncConfiguration syncConfiguration = new SyncConfiguration.Builder(SyncUser.currentUser(), configuration.REALM_SYNC)
                    .build();
            Realm.setDefaultConfiguration(syncConfiguration);
            realm = Realm.getInstance(syncConfiguration);
        }
    }

    @Override
    public void close() {
        realm.close();
        realm = null;
    }

    public Completable saveRealm(RealmDomainModel realmDomainModel) {
        return Completable.fromAction(() -> {
            RealmDataModel realmDataModel = dataModelAdapter.convertToDataModel(realmDomainModel);
            realm.executeTransaction(realm -> realm.insert(realmDataModel));
        });
    }

    public Observable<List<RealmDataModel>> getAllRealms() {
        return Observable.defer(() -> realm.where(RealmDataModel.class)
                .findAllAsync()
                .asObservable()
                .filter(list -> list.isLoaded() && list.isValid())
                .map((Func1<RealmResults<RealmDataModel>, List<RealmDataModel>>) realmDataModels -> realmDataModels));
    }
}
