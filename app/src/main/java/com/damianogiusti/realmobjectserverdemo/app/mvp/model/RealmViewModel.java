package com.damianogiusti.realmobjectserverdemo.app.mvp.model;

/**
 * Created by Damiano Giusti on 10/05/17.
 */
public class RealmViewModel {

    private String id;
    private String name;
    private String description;

    public RealmViewModel(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
