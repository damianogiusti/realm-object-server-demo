package com.damianogiusti.realmobjectserverdemo.data.adapter;

import com.damianogiusti.realmobjectserverdemo.data.model.RealmDataModel;
import com.damianogiusti.realmobjectserverdemo.domain.config.Configuration;
import com.damianogiusti.realmobjectserverdemo.domain.model.RealmDomainModel;

import javax.inject.Inject;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class DataModelAdapter {

    private Configuration configuration;

    @Inject
    public DataModelAdapter(Configuration configuration) {
        this.configuration = configuration;
    }

    public RealmDataModel convertToDataModel(RealmDomainModel realmDomainModel) {
        RealmDataModel realmDataModel = new RealmDataModel();
        realmDataModel.setRealmId(realmDomainModel.getRealmId());
        realmDataModel.setName(realmDomainModel.getName());
        realmDataModel.setEndpoint(realmDomainModel.getEndpoint());
        return realmDataModel;
    }
}
