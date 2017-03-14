package com.mylife.tsnt.mvp.model;

import com.mylife.tsnt.R;
import com.mylife.tsnt.TranslationService;
import com.mylife.tsnt.bean.YoudaoFanyiBean;
import com.mylife.tsnt.common.MyLifeApplication;
import com.mylife.tsnt.mvp.presenter.ITranslatePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslateModel implements ITranslateModel {
    private ITranslatePresenter mWeatherPresenter;

    public TranslateModel(ITranslatePresenter weatherPresenter) {
        mWeatherPresenter = weatherPresenter;
    }

    @Override
    public void loadTranslation(String city) {
        //String s = "http://fanyi.youdao.com/openapi.do?keyfrom=MyLife&key=1976408344&type=data&doctype=json&version=1.1&q=good";
        String url = "http://fanyi.youdao.com";
        String keyfrom = "MyLife";
        String key = "1976408344";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        TranslationService weatherService = retrofit.create(TranslationService.class);
        Call<YoudaoFanyiBean> call = weatherService.queryWeather(keyfrom, key, "data", "json", "1.1", city, null);
        call.enqueue(new Callback<YoudaoFanyiBean>() {
            @Override
            public void onResponse(Call<YoudaoFanyiBean> call, Response<YoudaoFanyiBean> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getErrorCode() == 0) {
                        if (checkData(response)) {
                            mWeatherPresenter.Succeed(response.body());
                        } else {
                            mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.getDataError));
                        }
                    } else {
                        mWeatherPresenter.Fail(getErrorMessage(response.body().getErrorCode()));
                    }
                } else {
                    mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.getDataError));
                }
            }

            @Override
            public void onFailure(Call<YoudaoFanyiBean> call, Throwable t) {
                mWeatherPresenter.Fail(MyLifeApplication.sContext.getString(R.string.networkFailure));
            }
        });
    }

    @Override
    public <T> boolean checkData(T response) {
        if (((Response<YoudaoFanyiBean>) response).body().getTranslation() != null && ((Response<YoudaoFanyiBean>) response).body().getTranslation().size() != 0) {
            return true;
        }
        return false;
    }

    private String getErrorMessage(int errorCode) {
        switch (errorCode) {
            case 20:
                return "要翻译的文本过长";
            case 30:
                return "无法进行有效的翻译";
            case 40:
                return "不支持的语言类型";
            case 50:
                return "无效的key";
            case 60:
                return "无词典结果，仅在获取词典结果生效";
        }
        return MyLifeApplication.sContext.getString(R.string.getDataError);
    }
}
