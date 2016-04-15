package com.brilliantbear.zhihupaper.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.bean.ZhihuSplash;
import com.brilliantbear.zhihupaper.net.API;
import com.brilliantbear.zhihupaper.net.ZhihuSplashService;
import com.brilliantbear.zhihupaper.utils.GlideUtils;
import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivSplash;
    private SharedPreferences sp;
    private static final int SPLASH_DURATION = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);

        ivSplash = (ImageView) findViewById(R.id.iv_splash);
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        String splashUrl = sp.getString("splash_url", null);
        if (null == splashUrl) {
            Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API.BASE_URL).build();
            ZhihuSplashService service = retrofit.create(ZhihuSplashService.class);
            Call<ZhihuSplash> call = service.getResult();
            call.enqueue(new Callback<ZhihuSplash>() {
                @Override
                public void onResponse(Call<ZhihuSplash> call, Response<ZhihuSplash> response) {
                    if (response.isSuccessful()) {
                        String url = response.body().getImg();
                        GlideUtils.load(getApplicationContext(), url, ivSplash);
                        sp.edit().putString("splash_url", url).apply();
                    }
                }

                @Override
                public void onFailure(Call<ZhihuSplash> call, Throwable t) {

                }
            });
            startApp(0);
        } else {
            Glide.with(this).load(splashUrl).crossFade(SPLASH_DURATION).into(ivSplash);
        }
        startApp(SPLASH_DURATION);
    }

    private void startApp(long delay) {
        ivSplash.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
//                overridePendingTransition(R.anim.transition_fade_in, R.anim.transition_fade_out);
                finish();
            }
        }, delay);
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
    }
}
