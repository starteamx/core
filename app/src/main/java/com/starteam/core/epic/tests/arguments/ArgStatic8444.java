package com.starteam.core.epic.tests.arguments;


/**
 * Created by weishu on 17/11/14.
 */
public class ArgStatic8444 extends AbsArgStaticCase {
    @Override
    protected void makeCall(long... args) {
        ArgumentTarget.arg4(args[0], (int)args[1], (int)args[2], (int)args[3]);
    }
}
