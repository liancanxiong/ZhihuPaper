package com.brilliantbear.zhihupaper.mvp.view;

/**
 * Created by cx.lian on 2016/4/14.
 */
public interface IDetailView {

    void showProgressBar();
    void hideProgressBar();
    void LoadDetail();
    void onFailed(String msg);
}
