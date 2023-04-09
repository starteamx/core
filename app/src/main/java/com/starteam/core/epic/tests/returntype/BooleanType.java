package com.starteam.core.epic.tests.returntype;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.TestCase;

/**
 * Created by weishu on 17/11/13.
 */
public class BooleanType extends TestCase {

    final boolean returnType = Boolean.FALSE;
    final boolean returnTypeModified = !returnType;

    public BooleanType() {
        super("Boolean");
    }

    @Override
    public void test() {

        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnBoolean", boolean.class, new LogMethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(returnTypeModified);
                super.beforeHookedMethod(param);
            }
        });
    }

    @Override
    public boolean predicate() {
        return ReturnTypeTarget.returnBoolean(returnType) == returnTypeModified;
    }
}
