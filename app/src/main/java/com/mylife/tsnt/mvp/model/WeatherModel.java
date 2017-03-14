package com.mylife.tsnt.mvp.model;

import android.text.TextUtils;

import com.mylife.tsnt.R;
import com.mylife.tsnt.WeatherService;
import com.mylife.tsnt.bean.JuheWeatherBean;
import com.mylife.tsnt.common.MyLifeApplication;
import com.mylife.tsnt.mvp.presenter.IWeatherPresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Joinwe on 2017/3/13.
 */

public class WeatherModel implements IWeatherModel {
    private IWeatherPresenter mWeatherPresenter;

    public WeatherModel(IWeatherPresenter weatherPresenter) {
        mWeatherPresenter = weatherPresenter;
    }

    @Override
    public void loadWeather(String city) {
        /*try {
            city = java.net.URLEncoder.encode(city, "utf-8");
            Log.d("city", city);
        } catch (UnsupportedEncodingException e) {
            city = "";
        }*/
        String url = "http://v.juhe.cn";
        String key = "df4823d06235406f9a09908135804634";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);
        Call<JuheWeatherBean> call = weatherService.queryWeather(city, "", "");
        call.enqueue(new Callback<JuheWeatherBean>() {
            @Override
            public void onResponse(Call<JuheWeatherBean> call, Response<JuheWeatherBean> response) {
                if (response != null && response.body() != null) {
                    if (TextUtils.equals(response.body().getResultcode(), "200")) {
                        if (checkData(response)) {
                            mWeatherPresenter.Succeed(response.body());
                        } else {
                            mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.getDataError));
                        }
                    } else {
                        mWeatherPresenter.Fail(response.body().getReason());
                    }
                } else {
                    mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.getDataError));
                }
            }

            @Override
            public void onFailure(Call<JuheWeatherBean> call, Throwable t) {
                mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.networkFailure));
            }
        });
    }

    @Override
    public <T> boolean checkData(T response) {
        if (((Response<JuheWeatherBean>) response).body().getResult() == null) {
            return false;
        }
        if (((Response<JuheWeatherBean>) response).body().getResult().getToday() == null) {
            return false;
        }
        if (!TextUtils.isEmpty(((Response<JuheWeatherBean>) response).body().getResult().getToday().getTemperature())) {
            return true;
        }
        return false;
    }
}
