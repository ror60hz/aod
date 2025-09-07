AOD Fix LSPosed Module - Source Project
======================================

What this is:
- An Android Studio project (source) that implements an Xposed/LSPosed module which hooks SharedPreferences.getInt("standby")
  in package com.transsion.aod and forces it to return 0, effectively removing the AOD timeout.

Important:
- This environment cannot compile APKs. You must build the APK on your machine (Android Studio) or a CI that supports Gradle builds.

Build steps (quick):
1. Install Android Studio (Arctic Fox or newer recommended).
2. Open the project folder: AodFixModule
3. Let Gradle sync and download dependencies (internet required).
4. Build -> Build Bundle(s) / APK(s) -> Build APK(s).
5. Locate the APK at app/build/outputs/apk/debug/app-debug.apk
6. Install the APK on device (adb install or transfer and tap).
7. In LSPosed Manager: enable the module for target package com.transsion.aod and SystemUI; enable Zygisk if required; reboot.

If Gradle cannot resolve 'de.robv.android.xposed:api:82', you can instead add the Xposed API jar manually:
- Download xposed_api.jar and put it into app/libs/
- Add 'implementation files("libs/xposed_api.jar")' in app/build.gradle instead of compileOnly dependency.

Troubleshooting:
- If 'android.app.SharedPreferencesImpl' class is different on your ROM, the hook target may need adjustment. Provide logcat and we will refine.
- If LSPosed uses Zygisk, ensure LSPosed is installed and enabled in Magisk/KSUNext and the module is activated in LSPosed Manager.

Security & ethics:
- You are responsible for testing on your device. Make backups before trying custom modules.
