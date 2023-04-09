package com.starteam.core.epic.tests.custom;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.returntype.ReturnTypeTarget;

/**
 * Created by weishu on 17/12/13.
 */

public class Case14_GC implements Case {
    @Override
    public void hook() {
        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnString", String.class, new LogMethodHook());
        System.gc();
    }

    @Override
    public boolean validate(Object... args) {
        return "123".equals(ReturnTypeTarget.returnString(new String("123")));
    }
}
