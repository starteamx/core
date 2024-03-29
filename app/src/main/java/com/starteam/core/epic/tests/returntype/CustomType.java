package com.starteam.core.epic.tests.returntype;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.TestCase;

/**
 * Created by weishu on 17/11/13.
 */
public class CustomType extends TestCase {

    final ReturnTypeTarget returnType = new ReturnTypeTarget();
    final ReturnTypeTarget returnTypeModified = new ReturnTypeTarget();

    public CustomType() {
        super("Custom");
    }

    @Override
    public void test() {

        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnCustom", ReturnTypeTarget.class, new LogMethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(returnTypeModified);
                super.beforeHookedMethod(param);
            }
        });
    }

    @Override
    public boolean predicate() {
        return ReturnTypeTarget.returnCustom(returnType) == returnTypeModified;
    }
}
