package com.brilliantbear.zhihupaper.net;

import com.brilliantbear.zhihupaper.bean.ZhihuSplash;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by cx.lian on 2016/4/14.
 */
public interface ZhihuSplashService {
    @GET("start-image/1080*1776")
    Call<ZhihuSplash> getResult();
}
