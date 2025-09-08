package com.example.lsposedmodule.;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;
public class AODFix implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.transsion.aod")) {
            try {
                findAndHookMethod(
                    "com.transsion.aod.AODManager",
                    lpparam.classLoader,
                    "getTimeout",
                    new XC_MethodReplacement() {
                        @Override
                        protected Object replaceHookedMethod(MethodHookParam param) throws Throwable {
                            return 60000; // 1 menit
                        }
                    }
                );
            } catch (Throwable t) { }
        }
    }
}
