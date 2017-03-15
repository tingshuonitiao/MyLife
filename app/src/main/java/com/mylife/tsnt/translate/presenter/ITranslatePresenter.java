package com.mylife.tsnt.translate.presenter;

import com.mylife.tsnt.translate.model.TranslateBean;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public interface ITranslatePresenter {
    void loadTranslation(String city);

    void loadSucceed(TranslateBean translateBean);

    void loadFail(String errorMessage);
}
