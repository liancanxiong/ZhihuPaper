package com.brilliantbear.zhihupaper.mvp.model;

import android.content.Context;
import android.util.Log;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.bean.ZhihuBefore;
import com.brilliantbear.zhihupaper.bean.ZhihuNews;
import com.brilliantbear.zhihupaper.db.DB;
import com.brilliantbear.zhihupaper.db.ZhihuStory;
import com.brilliantbear.zhihupaper.db.ZhihuTop;
import com.brilliantbear.zhihupaper.mvp.presenter.ZhihuListPresenter;
import com.brilliantbear.zhihupaper.net.API;
import com.brilliantbear.zhihupaper.net.ZhihuLatestNewsService;
import com.brilliantbear.zhihupaper.net.ZhihuListBeforeService;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cx.lian on 2016/4/12.
 */
public class ZhihuListModel implements IListModel {

    private Context mContext;
    private final Retrofit retrofit;
    private final DB db;

    public ZhihuListModel(Context context) {
        this.mContext = context;
        db = DB.getInstance();

        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void refresh(final OnLoadDataListener listener) {
        ZhihuLatestNewsService service = retrofit.create(ZhihuLatestNewsService.class);
        Call<ZhihuNews> call = service.getResult();
        call.enqueue(new Callback<ZhihuNews>() {
            @Override
            public void onResponse(Call<ZhihuNews> call, Response<ZhihuNews> response) {
                if (response.isSuccessful()) {
                    ZhihuNews news = response.body();
                    Log.d("!@#", news.toString());

                    List<ZhihuNews.TopStoriesBean> topStories = news.getTop_stories();
                    List<ZhihuTop> tops = new ArrayList<>();
                    for (ZhihuNews.TopStoriesBean top : topStories) {
                        ZhihuTop zhihuTop = new ZhihuTop();
                        zhihuTop.setId(top.getId());
                        zhihuTop.setImage(top.getImage());
                        zhihuTop.setTitle(top.getTitle());
                        zhihuTop.setType(top.getType());
                        tops.add(zhihuTop);
                    }
                    db.saveZhihuTops(tops);

                    List<ZhihuStory> storyList = new ArrayList<>();
                    List<ZhihuNews.StoriesBean> stories = news.getStories();
                    for (ZhihuNews.StoriesBean story : stories) {
                        ZhihuStory zhihuStory = new ZhihuStory();
                        zhihuStory.setId(story.getId());
                        if (null != story.getImages() && story.getImages().size() > 0) {
                            zhihuStory.setImage(story.getImages().get(0));
                        }
                        zhihuStory.setTitle(story.getTitle());
                        zhihuStory.setType(story.getType());
                        zhihuStory.setDate(news.getDate());
                        storyList.add(zhihuStory);
                    }
                    db.deleteZhihuStory(news.getDate());
                    db.saveZhihuStories(storyList);

                    listener.onSuccess(ZhihuListPresenter.REFRESH_DATE);
                } else {
                    listener.onFailed(mContext.getApplicationContext().getString(R.string.refresh_failed));
                }
            }

            @Override
            public void onFailure(Call<ZhihuNews> call, Throwable t) {
                listener.onFailed(mContext.getApplicationContext().getString(R.string.refresh_failed));
            }
        });
    }

    @Override
    public void loadMore(String str, final OnLoadDataListener listener) {
        String date = String.valueOf(Integer.valueOf(str) - 1);
        List<ZhihuStory> stories = db.getStoriesByDate(date);
        if (null != stories && stories.size() > 0) {
            listener.onSuccess(ZhihuListPresenter.LOAD_MORE_DATA);
        } else {
            ZhihuListBeforeService service = retrofit.create(ZhihuListBeforeService.class);
            Call<ZhihuBefore> call = service.getResult(str);
            call.enqueue(new Callback<ZhihuBefore>() {
                @Override
                public void onResponse(Call<ZhihuBefore> call, Response<ZhihuBefore> response) {
                    if (response.isSuccessful()) {
                        ZhihuBefore before = response.body();
                        Log.d("!@#", before.toString());

                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        List<ZhihuBefore.StoriesBean> stories = before.getStories();
                        for (ZhihuBefore.StoriesBean story : stories) {
                            ZhihuStory zhihuStory = new ZhihuStory();
                            zhihuStory.setId(story.getId());
                            if (null != story.getImages() && story.getImages().size() > 0) {
                                zhihuStory.setImage(story.getImages().get(0));
                            }
                            zhihuStory.setTitle(story.getTitle());
                            zhihuStory.setType(story.getType());
                            zhihuStory.setDate(before.getDate());
                            realm.copyToRealmOrUpdate(zhihuStory);
                        }
                        realm.commitTransaction();
                        listener.onSuccess(ZhihuListPresenter.LOAD_MORE_DATA);
                    } else {
                        listener.onFailed(mContext.getApplicationContext().getString(R.string.load_more_failed));
                    }
                }

                @Override
                public void onFailure(Call<ZhihuBefore> call, Throwable t) {
                    listener.onFailed(mContext.getApplicationContext().getString(R.string.load_more_failed));
                }
            });
        }
    }
}
