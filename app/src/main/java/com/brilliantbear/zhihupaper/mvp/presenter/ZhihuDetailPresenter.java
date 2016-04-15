package com.brilliantbear.zhihupaper.mvp.presenter;

import android.content.Context;

import com.brilliantbear.zhihupaper.mvp.model.IDetailModel;
import com.brilliantbear.zhihupaper.mvp.model.OnLoadDataListener;
import com.brilliantbear.zhihupaper.mvp.model.ZhihuDetailModel;
import com.brilliantbear.zhihupaper.mvp.view.IDetailView;

/**
 * Created by cx.lian on 2016/4/14.
 */
public class ZhihuDetailPresenter implements IDetailPresenter, OnLoadDataListener {

    private IDetailView mView;
    private IDetailModel mModel;

    public ZhihuDetailPresenter(Context context, IDetailView mView) {
        this.mView = mView;
        mModel = new ZhihuDetailModel(context);
    }

    @Override
    public void loadDetail(String id) {
        mView.showProgressBar();
        mModel.loadDetail(id, this);
    }

    @Override
    public void onSuccess(int type) {
        mView.LoadDetail();
        mView.hideProgressBar();
    }

    @Override
    public void onFailed(String msg) {
        mView.onFailed(msg);
        mView.hideProgressBar();
    }
}
