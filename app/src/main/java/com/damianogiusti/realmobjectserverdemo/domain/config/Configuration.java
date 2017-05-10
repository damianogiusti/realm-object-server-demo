package com.damianogiusti.realmobjectserverdemo.domain.config;

import com.damianogiusti.realmobjectserverdemo.BuildConfig;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
@Singleton
public final class Configuration {

    public static final String BASE_PORT = "9080";
    public final String REALM_AUTH_URL = String.format("http://%s:%s/auth", BuildConfig.OBJECT_SERVER_IP, BASE_PORT);
    public final String REALM_SYNC = String.format("realm://%s:%s/~/", BuildConfig.OBJECT_SERVER_IP, BASE_PORT);

    @Inject
    public Configuration() {
    }
}
