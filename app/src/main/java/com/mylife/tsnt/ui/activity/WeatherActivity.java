package com.mylife.tsnt.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mylife.tsnt.R;
import com.mylife.tsnt.base.BaseActivity;
import com.mylife.tsnt.bean.JuheWeatherBean;
import com.mylife.tsnt.mvp.presenter.WeatherPresenter;
import com.mylife.tsnt.mvp.view.IWeatherView;
import com.mylife.tsnt.util.ToastUtil;

public class WeatherActivity extends BaseActivity implements IWeatherView, View.OnClickListener {

    private WeatherPresenter mWeatherPresenter;
    private TextView mWeather;
    private Button mQuery;
    private TextView mInput;

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
        mWeatherPresenter = new WeatherPresenter(this);
    }

    @Override
    public void showWeather(JuheWeatherBean juheWeatherBean) {
        mWeather.setText(juheWeatherBean.getResult().getToday().getTemperature());
    }

    @Override
    public void showFailToast(String error) {
        ToastUtil.showToast(mContext, error);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                mWeatherPresenter.loadWeather(mInput.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
