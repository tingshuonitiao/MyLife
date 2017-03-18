package com.mylife.tsnt.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mylife.tsnt.R;
import com.mylife.tsnt.manager.ThreadManager;
import com.mylife.tsnt.translate.view.TranslateActivity;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        gotoMain();
    }

    //自动跳转到主页
    private void gotoMain() {
        ThreadManager.runOnThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(1000);
                startActivity(new Intent(SplashActivity.this, TranslateActivity.class));
                finish();
            }
        });
    }
}
