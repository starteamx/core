package com.starteam.core.epic.tests.arguments;

/**
 * Created by weishu on 17/11/14.
 */

public class ArgStatic848 extends AbsArgStaticCase {
    @Override
    protected void makeCall(long... args) {
        ArgumentTarget.arg3(args[0], (int) args[1], args[2]);
    }
}
