package com.starteam.core.epic.tests.arguments;


/**
 * Created by weishu on 17/11/14.
 */
public class ArgStatic888 extends AbsArgStaticCase {
    @Override
    protected void makeCall(long... args) {
        ArgumentTarget.arg3(args[0], args[1], args[2]);
    }
}
