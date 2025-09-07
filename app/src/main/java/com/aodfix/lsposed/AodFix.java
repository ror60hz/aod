package com.aodfix.lsposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import android.content.SharedPreferences;

public class AodFix implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        // Target only the AOD package
        if (!lpparam.packageName.equals("com.transsion.aod")) return;

        try {
            // Hook SharedPreferences.getInt(String, int) to return 0 when key == "standby"
            XposedHelpers.findAndHookMethod("android.app.SharedPreferencesImpl", lpparam.classLoader,
                    "getInt", String.class, int.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            String key = (String) param.args[0];
                            if ("standby".equals(key)) {
                                param.setResult(0);
                            }
                        }
                    });
        } catch (Throwable t) {
            // Fallback: hook android.content.SharedPreferences (interface) implementations
            try {
                XposedHelpers.findAndHookMethod(SharedPreferences.class, "getInt", String.class, int.class,
                        new XC_MethodHook() {
                            @Override
                            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                String key = (String) param.args[0];
                                if ("standby".equals(key)) {
                                    param.setResult(0);
                                }
                            }
                        });
            } catch (Throwable ignored) { }
        }
    }
}
