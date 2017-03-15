package com.mylife.tsnt.translate.view;

import com.mylife.tsnt.base.IBaseView;
import com.mylife.tsnt.translate.model.TranslateBean;

/**
 * Created by ting说你跳 on 2017/3/13.
 */

public interface ITranslateView extends IBaseView{
    void loadSucceed(TranslateBean translateBean);

    void loadFail(String errorMessage);
}
