package com.example.application;

import java.util.List;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String appPackageName = "com.fanshu.xiaozu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PackageManager packageManager = getPackageManager();
        if (checkPackInfo(appPackageName)) {
            Intent intent = packageManager.getLaunchIntentForPackage(appPackageName);
            startActivity(intent);
        } else {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setData(Uri.parse("http://baidu.com"));
            // 使用默认浏览器打开，为子APP添加
            if (isActivityExist("com.android.browser", "com.android.browser.BrowserActivity")) {
                intent.setComponent(new ComponentName("com.android.browser", "com.android.browser.BrowserActivity"));
            }
            startActivity(intent);
        }
        finish();
    }

    /**
     * 检查包是否存在
     *
     * @param packname
     *
     * @return
     */
    private boolean checkPackInfo(String packname) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(packname, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo != null;
    }

    /**
     * 判断是否存在Activity
     *
     * @return
     */
    public boolean isActivityExist(String packageName, String activityName) {
        boolean isActivityExist = false;
        Intent intents = new Intent();
        intents.setClassName(packageName, activityName);
        List<ResolveInfo> list = getPackageManager().queryIntentActivities(intents, 0);
        if (list.size() != 0) {
            isActivityExist = true;
        } else {
            isActivityExist = false;
        }
        return isActivityExist;
    }
}
