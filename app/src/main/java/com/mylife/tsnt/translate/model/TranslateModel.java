package com.mylife.tsnt.translate.model;

import com.mylife.tsnt.R;
import com.mylife.tsnt.MyLifeApplication;
import com.mylife.tsnt.translate.presenter.ITranslatePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslateModel implements ITranslateModel {
    private ITranslatePresenter mTranslatePresenter;

    public TranslateModel(ITranslatePresenter translatePresenter) {
        mTranslatePresenter = translatePresenter;
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
        TranslateService translateService = retrofit.create(TranslateService.class);
        Call<TranslateBean> call = translateService.queryTranslation(keyfrom, key, "data", "json", "1.1", city, null);
        call.enqueue(new Callback<TranslateBean>() {
            @Override
            public void onResponse(Call<TranslateBean> call, Response<TranslateBean> response) {
                if (response != null && response.body() != null) {
                    if (response.body().getErrorCode() == 0) {
                        if (checkData(response)) {
                            mTranslatePresenter.loadSucceed(response.body());
                        } else {
                            mTranslatePresenter.loadFail(MyLifeApplication.sContext.getString(R.string.getDataError));
                        }
                    } else {
                        mTranslatePresenter.loadFail(getErrorMessage(response.body().getErrorCode()));
                    }
                } else {
                    mTranslatePresenter.loadFail(MyLifeApplication.sContext.getString(R.string.getDataError));
                }
            }

            @Override
            public void onFailure(Call<TranslateBean> call, Throwable t) {
                mTranslatePresenter.loadFail(MyLifeApplication.sContext.getString(R.string.networkFailure));
            }
        });
    }

    public boolean checkData(Response<TranslateBean> response) {
        if (response.body().getTranslation() != null && response.body().getTranslation().size() != 0) {
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
