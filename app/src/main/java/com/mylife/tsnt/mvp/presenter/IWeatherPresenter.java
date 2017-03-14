package com.mylife.tsnt.mvp.presenter;

import com.mylife.tsnt.bean.JuheWeatherBean;

/**
 * Created by Joinwe on 2017/3/13.
 */

public interface IWeatherPresenter {
    void loadWeather(String city);

    void Succeed(JuheWeatherBean juheWeatherBean);

    void Fail(String error);
}
