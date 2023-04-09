package com.starteam.core.epic.tests.custom;

/**
 * Created by weishu on 17/11/6.
 */

public interface Case {
    void hook();

    boolean validate(Object... args);
}
