package com.brilliantbear.zhihupaper.mvp.model;

/**
 * Created by cx.lian on 2016/4/12.
 */
public interface IListModel {
    void refresh(OnLoadDataListener listener);

    void loadMore(String str, OnLoadDataListener listener);
}
