package com.brilliantbear.zhihupaper.mvp.presenter;

import android.content.Context;

import com.brilliantbear.zhihupaper.mvp.model.IListModel;
import com.brilliantbear.zhihupaper.mvp.model.OnLoadDataListener;
import com.brilliantbear.zhihupaper.mvp.model.ZhihuListModel;
import com.brilliantbear.zhihupaper.mvp.view.IListView;

/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuListPresenter implements IListPresenter, OnLoadDataListener {

    public static final int REFRESH_DATE = 1;
    public static final int LOAD_MORE_DATA = 2;

    private IListView mView;
    private IListModel mModel;
    private Context mContext;

    public ZhihuListPresenter(Context context, IListView view) {
        this.mContext = context;
        this.mView = view;
        mModel = new ZhihuListModel(context);
    }

    @Override
    public void refresh() {
        mView.showProgressBar();
        mModel.refresh(this);
    }

    @Override
    public void loadMore(String str) {
        mView.showProgressBar();
        mModel.loadMore(str, this);
    }

    @Override
    public void onSuccess(int type) {
        if (type == REFRESH_DATE) {
            mView.dataRefresh();
        } else if (type == LOAD_MORE_DATA) {
            mView.dataLoadMore();
        }
        mView.hideProgressBar();
    }

    @Override
    public void onFailed(String msg) {
        mView.onFailed(msg);
        mView.hideProgressBar();
    }
}
