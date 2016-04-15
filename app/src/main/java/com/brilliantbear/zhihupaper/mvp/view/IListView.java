package com.brilliantbear.zhihupaper.mvp.view;

/**
 * Created by cx.lian on 2016/4/12.
 */
public interface IListView {
    void dataRefresh();

    void dataLoadMore();

    void showProgressBar();

    void hideProgressBar();

    void onFailed(String msg);
}
