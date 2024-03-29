package com.starteam.core.epic.tests.returntype;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.TestCase;

/**
 * Created by weishu on 17/11/13.
 */
public class StringType extends TestCase {

    final String returnType = "123455";
    final String returnTypeModified = "784fsgiulfodsg";

    public StringType() {
        super("String");
    }

    @Override
    public void test() {


        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnString", String.class, new LogMethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(returnTypeModified);
                super.beforeHookedMethod(param);
            }
        });
    }

    @Override
    public boolean predicate() {
        return ReturnTypeTarget.returnString(returnType).equals(returnTypeModified);
    }
}
