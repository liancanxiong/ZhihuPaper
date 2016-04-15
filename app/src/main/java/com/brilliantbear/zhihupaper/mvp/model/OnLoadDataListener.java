package com.brilliantbear.zhihupaper.mvp.model;

/**
 * Created by cx.lian on 2016/4/12.
 */
public interface OnLoadDataListener {
    void onSuccess(int type);

    void onFailed(String msg);
}
