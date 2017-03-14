package com.mylife.tsnt.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;

import com.mylife.tsnt.R;
import com.mylife.tsnt.base.BaseActivity;
import com.mylife.tsnt.manager.ThreadManager;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        super.onCreate(savedInstanceState);
        gotoMain();
    }

    @Override
    public void initData() {
        mContext = this;
    }

    //自动跳转到主页
    private void gotoMain() {
        ThreadManager.runOnThread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                startActivity(new Intent(mContext, TranslateActivity.class));
                finish();
            }
        });
    }
}
