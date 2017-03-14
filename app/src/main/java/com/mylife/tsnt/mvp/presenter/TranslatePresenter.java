package com.mylife.tsnt.mvp.presenter;

import com.mylife.tsnt.bean.YoudaoFanyiBean;
import com.mylife.tsnt.mvp.model.ITranslateModel;
import com.mylife.tsnt.mvp.model.TranslateModel;
import com.mylife.tsnt.mvp.view.ITranslateView;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslatePresenter implements ITranslatePresenter {
    private ITranslateView  mITranslateView;
    private ITranslateModel mWeatherModel;

    public TranslatePresenter(ITranslateView weatherView) {
        mITranslateView = weatherView;
        mWeatherModel = new TranslateModel(this);
    }

    @Override
    public void loadTranslation(String city) {
        mWeatherModel.loadTranslation(city);
    }

    @Override
    public void Succeed(YoudaoFanyiBean youdaoFanyiBean) {
        mITranslateView.Succeed(youdaoFanyiBean);
    }

    @Override
    public void Fail(String error) {
        mITranslateView.Fail(error);
    }
}
