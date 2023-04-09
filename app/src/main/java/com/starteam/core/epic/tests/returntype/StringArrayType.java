package com.starteam.core.epic.tests.returntype;

import de.robv.android.xposed.DexposedBridge;

import com.starteam.core.epic.tests.LogMethodHook;
import com.starteam.core.epic.tests.TestCase;

/**
 * Created by weishu on 17/11/13.
 */
public class StringArrayType extends TestCase {

    final String[] returnType = new String[]{"123", "456"};
    final String[] returnTypeModified = new String[]{"123", "456", "678"};

    public StringArrayType() {
        super("StringArray");
    }

    @Override
    public void test() {


        DexposedBridge.findAndHookMethod(ReturnTypeTarget.class, "returnStringArray", String[].class, new LogMethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                param.setResult(returnTypeModified);
                super.beforeHookedMethod(param);
            }
        });
    }

    @Override
    public boolean predicate() {
        return ReturnTypeTarget.returnStringArray(returnType) == returnTypeModified;
    }
}
