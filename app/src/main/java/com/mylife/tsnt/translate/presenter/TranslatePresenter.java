package com.mylife.tsnt.translate.presenter;

import com.mylife.tsnt.base.IBasePresenter;
import com.mylife.tsnt.translate.model.ITranslateModel;
import com.mylife.tsnt.translate.model.TranslateBean;
import com.mylife.tsnt.translate.model.TranslateModel;
import com.mylife.tsnt.translate.view.ITranslateView;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslatePresenter implements ITranslatePresenter, IBasePresenter<ITranslateView> {
    private ITranslateView mITranslateView;
    private ITranslateModel mTranslateModel;

    public TranslatePresenter(ITranslateView translateView) {
        attachView(translateView);
        mTranslateModel = new TranslateModel(this);
    }

    @Override
    public void attachView(ITranslateView view) {
        mITranslateView = view;
    }

    @Override
    public void detachView() {
        mITranslateView = null;
    }

    @Override
    public void loadTranslation(String city) {
        if (isViewAttached()) {
            mTranslateModel.loadTranslation(city);
        }
    }

    @Override
    public void loadSucceed(TranslateBean translateBean) {
        if (isViewAttached()) {
            mITranslateView.loadSucceed(translateBean);
        }
    }

    @Override
    public void loadFail(String errorMessage) {
        if (isViewAttached()) {
            mITranslateView.loadFail(errorMessage);
        }
    }

    private boolean isViewAttached() {
        return mITranslateView != null;
    }
}
