package com.starteam.core.epic.tests.arguments;

/**
 * @author weishu
 * @date 17/11/14.
 */

public class ArgStatic44 extends AbsArgStaticCase {

    @Override
    protected void makeCall(long... args) {
        ArgumentTarget.arg2((int) args[0], (int) args[1]);
    }
}
