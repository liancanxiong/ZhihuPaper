package com.brilliantbear.zhihupaper.mvp.view;

import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.adapter.ZhihuListAdapter;
import com.brilliantbear.zhihupaper.db.DB;
import com.brilliantbear.zhihupaper.db.ZhihuStory;
import com.brilliantbear.zhihupaper.mvp.presenter.IListPresenter;
import com.brilliantbear.zhihupaper.mvp.presenter.ZhihuListPresenter;
import com.brilliantbear.zhihupaper.mvp.view.IListView;
import com.brilliantbear.zhihupaper.ui.BaseFragment;
import com.brilliantbear.zhihupaper.utils.DateUtils;
import com.brilliantbear.zhihupaper.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuListFragment extends BaseFragment implements IListView, SwipeRefreshLayout.OnRefreshListener {

    RecyclerView rvNews;
    SwipeRefreshLayout mRefresh;

    IListPresenter mPresenter;
    private LinearLayoutManager mLayoutManager;
    private ZhihuListAdapter mAdapter;
    private List<ZhihuStory> mStories;
    private DB db;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_zhihu;
    }

    @Override
    protected void initView() {
        rvNews = (RecyclerView) rootView.findViewById(R.id.rv_news);
        mRefresh = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);

        mRefresh.setColorSchemeResources(R.color.colorAccent);
        mRefresh.setOnRefreshListener(this);

        mLayoutManager = new LinearLayoutManager(mContext);
        rvNews.setLayoutManager(mLayoutManager);
        rvNews.setItemAnimator(new DefaultItemAnimator());

        rvNews.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int count = recyclerView.getAdapter().getItemCount() - 1;
                if (count > 0 && newState == RecyclerView.SCROLL_STATE_IDLE &&
                        mLayoutManager.findLastVisibleItemPosition() == count) {
                    mPresenter.loadMore(mStories.get(count).getDate());
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter = new ZhihuListPresenter(mContext, this);
        mStories = new ArrayList<>();

        mAdapter = new ZhihuListAdapter(mContext, mStories);
        rvNews.setAdapter(mAdapter);

        db = DB.getInstance();
        List<ZhihuStory> leastStories = db.getLeastStories();
        if (leastStories != null) {
            mStories.addAll(leastStories);
            mAdapter.notifyItemRangeInserted(0, mStories.size());
        }
//        mPresenter.refresh();
    }

    private void showRefresh(boolean isShow) {
        if (null != mRefresh) {
            if (isShow) {
                mRefresh.setProgressViewOffset(false, 0, DensityUtils.dp2px(mContext, 24));
                mRefresh.setRefreshing(true);
            } else {
                mRefresh.setRefreshing(false);
            }
        }
    }

    @Override
    public void dataRefresh() {
        List<ZhihuStory> leastStories = db.getLeastStories();
        if (leastStories != null) {
            mStories.clear();
            mStories.addAll(leastStories);
            mAdapter.notifyDataSetChanged();
        }
        Snackbar.make(mRefresh, getString(R.string.refresh_success), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void dataLoadMore() {
        int position = mLayoutManager.findLastVisibleItemPosition();
        String date = String.valueOf(Integer.valueOf(mStories.get(position).getDate()) - 1);
        List<ZhihuStory> stories = db.getStoriesByDate(date);
        if (null != stories) {
            mStories.addAll(stories);
            mAdapter.notifyItemRangeInserted(position + 1, stories.size());
        }
    }

    @Override
    public void showProgressBar() {
        showRefresh(true);
    }

    @Override
    public void hideProgressBar() {
        showRefresh(false);
    }

    @Override
    public void onFailed(String msg) {
        Snackbar.make(mRefresh, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        mPresenter.refresh();
    }


}
