package com.starteam.core.epic.tests.arguments;


/**
 * Created by weishu on 17/11/14.
 */
public class ArgStatic8888 extends AbsArgStaticCase {
    @Override
    protected void makeCall(long... args) {
        ArgumentTarget.arg4(args[0], args[1], args[2], args[3]);
    }
}
