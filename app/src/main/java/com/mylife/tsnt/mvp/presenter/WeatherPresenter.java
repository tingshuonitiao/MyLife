package com.mylife.tsnt.mvp.presenter;

import com.mylife.tsnt.bean.JuheWeatherBean;
import com.mylife.tsnt.mvp.model.IWeatherModel;
import com.mylife.tsnt.mvp.model.WeatherModel;
import com.mylife.tsnt.mvp.view.IWeatherView;

;

/**
 * Created by Joinwe on 2017/3/13.
 */

public class WeatherPresenter implements IWeatherPresenter {
    private IWeatherView mIWeatherView;
    private IWeatherModel mWeatherModel;

    public WeatherPresenter(IWeatherView weatherView) {
        mIWeatherView = weatherView;
        mWeatherModel = new WeatherModel(this);
    }

    @Override
    public void loadWeather(String city) {
        mWeatherModel.loadWeather(city);
    }

    @Override
    public void Succeed(JuheWeatherBean juheWeatherBean) {
        mIWeatherView.showWeather(juheWeatherBean);
    }

    @Override
    public void Fail(String error) {
        mIWeatherView.showFailToast(error);
    }
}
