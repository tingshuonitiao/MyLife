package com.mylife.tsnt.mvp.model;

import com.mylife.tsnt.base.IBaseModel;

/**
 * Created by Joinwe on 2017/3/13.
 */

public interface IWeatherModel extends IBaseModel {
    void loadWeather(String city);
}
