package com.mylife.tsnt.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class MyLifeApplication extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
    }
}
