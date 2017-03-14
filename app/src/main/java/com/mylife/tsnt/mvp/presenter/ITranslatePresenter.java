package com.mylife.tsnt.mvp.presenter;

import com.mylife.tsnt.bean.YoudaoFanyiBean;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public interface ITranslatePresenter {
    void loadTranslation(String city);

    void Succeed(YoudaoFanyiBean youdaoFanyiBean);

    void Fail(String error);
}
