package com.brilliantbear.zhihupaper.net;

import com.brilliantbear.zhihupaper.bean.ZhihuNews;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cx.lian on 2016/4/12.
 */
public interface ZhihuLatestNewsService {
    @GET("news/latest")
    Call<ZhihuNews> getResult();
}
