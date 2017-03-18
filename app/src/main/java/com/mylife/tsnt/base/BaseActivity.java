package com.mylife.tsnt.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mylife.tsnt.manager.ToastManager;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {
    protected Context mContext;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = setPresenter();
        mPresenter.attachView(this);
        mContext = this;
        initView();
        initData();
    }

    public abstract P setPresenter();

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onDestroy();
    }

    @Override
    protected void onDestroy() {
        mPresenter.detachView();
        ToastManager.cancelToast();
        super.onDestroy();
    }
}
