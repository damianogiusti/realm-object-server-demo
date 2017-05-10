package com.damianogiusti.realmobjectserverdemo.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public class RealmDataModel extends RealmObject {

    @PrimaryKey
    private String realmId;
    private String name;
    private String endpoint;

    public String getRealmId() {
        return realmId;
    }

    public void setRealmId(String realmId) {
        this.realmId = realmId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }
}
