package com.brilliantbear.zhihupaper.mvp.model;

/**
 * Created by cx.lian on 2016/4/14.
 */
public interface IDetailModel {
    void loadDetail(String id, OnLoadDataListener listener);
}
