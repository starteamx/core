package com.starteam.core.epic.tests.returntype;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.TestCase;

/**
 * Created by weishu on 17/11/13.
 */
public class ShortType extends TestCase {

    final short returnType = Short.MAX_VALUE / 2;
    final short returnTypeModified = returnType - 1;

    public ShortType() {
        super("Short");
    }

    @Override
    public void test() {

        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnShort", short.class, new LogMethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(returnTypeModified);
                super.beforeHookedMethod(param);
            }
        });
    }

    @Override
    public boolean predicate() {
        return ReturnTypeTarget.returnShort(returnType) == returnTypeModified;
    }
}
