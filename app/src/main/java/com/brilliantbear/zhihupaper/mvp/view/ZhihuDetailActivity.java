package com.brilliantbear.zhihupaper.mvp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.db.DB;
import com.brilliantbear.zhihupaper.db.ZhihuDetail;
import com.brilliantbear.zhihupaper.mvp.presenter.IDetailPresenter;
import com.brilliantbear.zhihupaper.mvp.presenter.ZhihuDetailPresenter;
import com.brilliantbear.zhihupaper.ui.BaseActivity;
import com.brilliantbear.zhihupaper.utils.GlideUtils;

public class ZhihuDetailActivity extends BaseActivity implements IDetailView {

    private ProgressBar progress;
    private ImageView ivPic;
    private CollapsingToolbarLayout toolbarLayout;
    private String id;
    private WebView webDetail;

    @Override
    public int getLayoutId() {
        return R.layout.activity_zhihu_detail;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        ivPic = (ImageView) findViewById(R.id.iv_pic);
        progress = (ProgressBar) findViewById(R.id.progress);
        toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        webDetail = (WebView) findViewById(R.id.web_detail);
        initWebView();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        id = intent.getStringExtra("id");
        String url = intent.getStringExtra("url");
        GlideUtils.load(this, url, ivPic);

        IDetailPresenter presenter = new ZhihuDetailPresenter(this, this);
        final ZhihuDetail detail = DB.getInstance().getZhihuDetailById(id);
        if (null != detail) {
            showDetail(detail);
        } else {
            presenter.loadDetail(id);
        }
    }

    @Override
    public void onBackPressed() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(500);
        webDetail.startAnimation(alphaAnimation);

        super.onBackPressed();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        WebSettings settings = webDetail.getSettings();

        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        // 开启DOM storage API 功能
        settings.setDomStorageEnabled(true);
        // 开启database storage API功能
        settings.setDatabaseEnabled(true);
        // 开启Application Cache功能
        settings.setAppCacheEnabled(true);

    }


    private void showDetail(ZhihuDetail detail) {

        toolbarLayout.setTitle(detail.getTitle());
        toolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        toolbarLayout.setExpandedTitleColor(Color.WHITE);

        String css = "<link rel=\"stylesheet\" href=\"file:///android_asset/css/news.css\" type=\"text/css\">";
        String html = "<html><head>" + css + "</head><body>" + detail.getBody() + "</body></html>";
        html = html.replace("<div class=\"img-place-holder\">", "");
        webDetail.loadDataWithBaseURL("x-data://base", html, "text/html", "UTF-8", null);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        alphaAnimation.setDuration(500);
        webDetail.startAnimation(alphaAnimation);
        GlideUtils.load(this, detail.getImage(), ivPic);
    }

    @Override
    public void showProgressBar() {
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void LoadDetail() {
        ZhihuDetail detail = DB.getInstance().getZhihuDetailById(id);
        if (null != detail) {
            showDetail(detail);
        }
    }

    @Override
    public void onFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
