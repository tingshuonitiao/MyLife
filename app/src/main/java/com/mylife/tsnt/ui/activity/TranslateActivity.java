package com.mylife.tsnt.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mylife.tsnt.R;
import com.mylife.tsnt.base.BaseActivity;
import com.mylife.tsnt.bean.YoudaoFanyiBean;
import com.mylife.tsnt.mvp.presenter.TranslatePresenter;
import com.mylife.tsnt.mvp.view.ITranslateView;
import com.mylife.tsnt.util.ToastUtil;

import java.util.List;

/**
 * Created by ting说你跳 on 2017/3/14.
 */

public class TranslateActivity extends BaseActivity implements ITranslateView, View.OnClickListener {

    private TranslatePresenter mWeatherPresenter;
    private TextView           mWeather;
    private Button             mQuery;
    private TextView           mInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_weather);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {
        mWeather = (TextView) findViewById(R.id.weather);
        mInput = (TextView) findViewById(R.id.input);
        mQuery = (Button) findViewById(R.id.query);
        mQuery.setOnClickListener(this);
    }

    @Override
    public void initData() {
        mContext = this;
        mWeatherPresenter = new TranslatePresenter(this);
    }

    @Override
    public void Succeed(YoudaoFanyiBean youdaoFanyiBean) {
        List<String> translations = youdaoFanyiBean.getTranslation();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < translations.size(); i++) {
            buffer.append(translations.get(i) + " ");
        }
        mWeather.setText(buffer);
    }

    @Override
    public void Fail(String error) {
        ToastUtil.showToast(mContext, error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                mWeatherPresenter.loadTranslation(mInput.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
