package com.brilliantbear.zhihupaper.net;

import com.brilliantbear.zhihupaper.bean.ZhihuDetailJson;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cx.lian on 2016/4/14.
 */
public interface ZhihuDetailService {
    @GET("news/{id}")
    Call<ZhihuDetailJson> getResult(@Path("id") String id);
}
