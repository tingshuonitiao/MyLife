package com.mylife.tsnt.mvp.view;

import com.mylife.tsnt.bean.JuheWeatherBean;

/**
 * Created by Joinwe on 2017/3/13.
 */

public interface IWeatherView {
    void showWeather(JuheWeatherBean juheWeatherBean);

    void showFailToast(String error);
}
