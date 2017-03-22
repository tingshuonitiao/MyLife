package com.mylife.tsnt.translate.model;

import com.mylife.tsnt.MyLifeApplication;
import com.mylife.tsnt.R;
import com.mylife.tsnt.common.Config;
import com.mylife.tsnt.network.Netwrok;
import com.mylife.tsnt.network.api.TranslateApi;
import com.mylife.tsnt.translate.presenter.ITranslatePresenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslateModel implements ITranslateModel {
    private ITranslatePresenter mTranslatePresenter;

    public TranslateModel(ITranslatePresenter translatePresenter) {
        mTranslatePresenter = translatePresenter;
    }

    @Override
    public void loadTranslation(String words) {
        TranslateApi translateApi = Netwrok.getTranslateApi();
        Call<TranslateBean> call = translateApi.queryTranslation(Config.YOUDAO_KEYFORM, Config.YOUDAO_KEY, "data", "json", "1.1", words, null);
        call.enqueue(new Callback<TranslateBean>() {
            @Override
            public void onResponse(Call<TranslateBean> call, Response<TranslateBean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mTranslatePresenter.checkData(response.body());
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
}
