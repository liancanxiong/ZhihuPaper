package com.brilliantbear.zhihupaper.mvp.model;

import android.content.Context;
import android.util.Log;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.bean.ZhihuDetailJson;
import com.brilliantbear.zhihupaper.db.DB;
import com.brilliantbear.zhihupaper.db.ZhihuDetail;
import com.brilliantbear.zhihupaper.net.API;
import com.brilliantbear.zhihupaper.net.ZhihuDetailService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cx.lian on 2016/4/14.
 */
public class ZhihuDetailModel implements IDetailModel {

    private Context context;
    private final Retrofit retrofit;
    private final DB db;

    public ZhihuDetailModel(Context context) {
        this.context = context;
        db = DB.getInstance();

        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void loadDetail(String id, final OnLoadDataListener listener) {
        ZhihuDetailService service = retrofit.create(ZhihuDetailService.class);
        Call<ZhihuDetailJson> call = service.getResult(id);
        call.enqueue(new Callback<ZhihuDetailJson>() {
            @Override
            public void onResponse(Call<ZhihuDetailJson> call, Response<ZhihuDetailJson> response) {
                if (response.isSuccessful()) {
                    ZhihuDetailJson detail = response.body();
                    Log.d("!@#", detail.toString());
                    ZhihuDetail zhihuDetail = new ZhihuDetail();
                    zhihuDetail.setTitle(detail.getTitle());
                    zhihuDetail.setImage(detail.getImage());
                    zhihuDetail.setId(detail.getId());
                    zhihuDetail.setBody(detail.getBody());
                    zhihuDetail.setImage_source(detail.getImage_source());
                    zhihuDetail.setShare_url(detail.getShare_url());
                    zhihuDetail.setType(detail.getType());
                    db.saveDetail(zhihuDetail);
                    listener.onSuccess(0);
                } else {
                    listener.onFailed(context.getApplicationContext().getString(R.string.load_more_failed));
                }
            }

            @Override
            public void onFailure(Call<ZhihuDetailJson> call, Throwable t) {
                listener.onFailed(context.getApplicationContext().getString(R.string.load_more_failed));
            }
        });
    }
}
