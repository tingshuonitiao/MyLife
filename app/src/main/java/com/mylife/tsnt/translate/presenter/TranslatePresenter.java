package com.mylife.tsnt.translate.presenter;

import com.mylife.tsnt.MyLifeApplication;
import com.mylife.tsnt.R;
import com.mylife.tsnt.base.IBasePresenter;
import com.mylife.tsnt.translate.model.ITranslateModel;
import com.mylife.tsnt.translate.model.TranslateBean;
import com.mylife.tsnt.translate.model.TranslateModel;
import com.mylife.tsnt.translate.view.ITranslateView;

import java.util.ArrayList;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public class TranslatePresenter implements ITranslatePresenter, IBasePresenter<ITranslateView> {
    private ITranslateView  mITranslateView;
    private ITranslateModel mTranslateModel;

    public TranslatePresenter(ITranslateView translateView) {
        attachView(translateView);
        mTranslateModel = new TranslateModel(this);
        ArrayList<Object> objects = new ArrayList<>();
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
    public boolean isViewAttached() {
        return mITranslateView != null;
    }

    @Override
    public void loadTranslation(String words) {
        if (isViewAttached()) {
            mTranslateModel.loadTranslation(words);
        }
    }

    @Override
    public void checkData(TranslateBean translateBean) {
        if (translateBean.getErrorCode() == 0) {
            if (checkNotNull(translateBean)) {
                loadSucceed(translateBean);
            } else {
                loadFail(MyLifeApplication.sContext.getString(R.string.getDataError));
            }
        } else {
            loadFail(getErrorMessage(translateBean.getErrorCode()));
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

    private boolean checkNotNull(TranslateBean translateBean) {
        if (translateBean.getTranslation() != null && translateBean.getTranslation().size() != 0) {
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
