package com.brilliantbear.zhihupaper.ui;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;

import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.mvp.view.ZhihuListFragment;


public class MainActivity extends BaseActivity {

    DrawerLayout drawer;
    private CoordinatorLayout mCoordinator;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);

        initDrawer();
        if (null == savedInstanceState) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame_content, new ZhihuListFragment());
            ft.commit();
        }
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    private long lastClick = 0;

    @Override
    public void onBackPressed() {
        long newClick = System.currentTimeMillis();
        if (newClick - lastClick < 2000) {
            super.onBackPressed();
        } else {
            lastClick = newClick;
            Snackbar.make(mCoordinator, getString(R.string.exit), Snackbar.LENGTH_SHORT).show();
        }
    }
}
