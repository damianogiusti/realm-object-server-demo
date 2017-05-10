package com.damianogiusti.realmobjectserverdemo.domain.adapter;

import com.damianogiusti.realmobjectserverdemo.data.model.RealmDataModel;
import com.damianogiusti.realmobjectserverdemo.domain.config.Configuration;
import com.damianogiusti.realmobjectserverdemo.domain.model.RealmDomainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class DomainModelAdapter {

    private Configuration configuration;

    @Inject
    public DomainModelAdapter(Configuration configuration) {
        this.configuration = configuration;
    }

    public RealmDomainModel createRealmDomainModel(String name) {
        RealmDomainModel realm = new RealmDomainModel();
        realm.setRealmId(UUID.randomUUID().toString());
        realm.setName(name);
        String normalizedName = name.replace(" ", "_").toLowerCase();
        realm.setEndpoint(configuration.REALM_SYNC + normalizedName);
        return realm;
    }

    public List<RealmDomainModel> convertToDomainModel(List<RealmDataModel> realmDataModels) {
        List<RealmDomainModel> list = new ArrayList<>(realmDataModels.size());
        for (RealmDataModel realmDataModel : realmDataModels) {
            list.add(convertToDomainModel(realmDataModel));
        }
        return list;
    }

    public RealmDomainModel convertToDomainModel(RealmDataModel realmDataModel) {
        RealmDomainModel realmDomainModel = new RealmDomainModel();
        realmDomainModel.setRealmId(realmDataModel.getRealmId());
        realmDomainModel.setName(realmDataModel.getName());
        realmDomainModel.setEndpoint(realmDataModel.getEndpoint());
        return realmDomainModel;
    }
}
