package com.mylife.tsnt.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mylife.tsnt.util.ToastUtil;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class BaseActivity extends AppCompatActivity implements IBaseView {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ToastUtil.cancelToast();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ToastUtil.cancelToast();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
