package com.brilliantbear.zhihupaper.ui;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.Slide;

import com.brilliantbear.zhihupaper.R;


public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar toolbar;

    public abstract int getLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setEnterTransition(new Slide());
//            getWindow().setExitTransition(new Slide());
        }
        setContentView(getLayoutId());
        initView(savedInstanceState);
        initData(savedInstanceState);
    }

    protected void initView(Bundle savedInstanceState) {

        initAppBar();
    }

    private void initAppBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (null != toolbar) {
            setSupportActionBar(toolbar);
        }
    }

    protected void initData(Bundle savedInstanceState) {
    }
}
