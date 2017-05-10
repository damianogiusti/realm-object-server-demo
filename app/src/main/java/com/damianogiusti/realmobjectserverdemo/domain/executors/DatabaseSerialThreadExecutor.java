package com.damianogiusti.realmobjectserverdemo.domain.executors;

import java.util.concurrent.Executor;

/**
 * Created by Damiano Giusti on 09/05/17.
 */
public interface DatabaseSerialThreadExecutor extends Executor, RxExecutor {
}
