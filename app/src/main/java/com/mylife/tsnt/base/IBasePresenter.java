package com.mylife.tsnt.base;

/**
 * Created by Joinwe on 2017/3/15.
 */

public interface IBasePresenter<V extends IBaseView> {
    void attachView(V view);

    void detachView();

    boolean isViewAttached();
}
