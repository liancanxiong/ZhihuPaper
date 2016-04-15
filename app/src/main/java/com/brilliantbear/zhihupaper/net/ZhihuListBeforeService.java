package com.brilliantbear.zhihupaper.net;

import com.brilliantbear.zhihupaper.bean.ZhihuBefore;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cx.lian on 2016/4/14.
 */
public interface ZhihuListBeforeService {
    @GET("news/before/{date}")
    Call<ZhihuBefore> getResult(@Path("date") String date);
}
