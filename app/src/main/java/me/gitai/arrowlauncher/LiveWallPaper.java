package me.gitai.arrowlauncher;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import java.util.List;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XSharedPreferences;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import android.graphics.drawable.ColorDrawable;
import android.graphics.Color;
/**
 * Created by gitai on 16-04-25.
 */
public class LiveWallPaper implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if ("com.microsoft.launcher".equals(lpparam.packageName)) {
            try {
			String className = "com.microsoft.launcher.wallpaper.b.b"; //类名
			String param1Type = "com.microsoft.launcher.wallpaper.a.k";
			Class<?> param2Type = ImageView.class;
			Class<?> param3Type = ImageView.class;
			Class<?> param4Type = View.class;
			Class<?> param5Type = View.class;
			Class<?> param6Type = ImageView.class;
			Class<?> param7Type = TextView.class;

        		XposedBridge.log("com.microsoft.launcher.wallpaper.b.b");
			XposedHelpers.findAndHookConstructor(className, lpparam.classLoader,
				param1Type, param2Type, param3Type, param4Type, param5Type, param6Type, param7Type, new XC_MethodHook(){
			        @Override
			        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
			        		((ImageView)param.args[1]).setAlpha(0.1f);
			        		XposedBridge.log("done");
			        }
			    });
            } catch (Throwable e) {
                XposedBridge.log("Failed to hook Xposed module status checker" + "\n" + Log.getStackTraceString(e));
                throw e;
            }
        }
    }
}
