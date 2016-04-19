package com.brilliantbear.zhihupaper.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;

import com.brilliantbear.zhihupaper.Constant;
import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.mvp.view.ZhihuListFragment;


public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout mDrawer;
    private CoordinatorLayout mCoordinator;
    private NavigationView mNav;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        mCoordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        mNav = (NavigationView) findViewById(R.id.nav);

        initNav();
        initDrawer();
        if (null == savedInstanceState) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.add(R.id.frame_content, new ZhihuListFragment());
            ft.commit();
        }
    }

    private void initNav() {
        mNav.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    private void initDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
        toggle.setHomeAsUpIndicator(R.mipmap.ic_launcher);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();
    }


    private void showDrawer(boolean isShow) {
        if (isShow) {
            mDrawer.openDrawer(GravityCompat.START);
        } else {
            mDrawer.closeDrawer(GravityCompat.START);
        }
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_night:
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                boolean isNight = sp.getBoolean(Constant.KEY_IS_NIGHT, false);
                sp.edit().putBoolean(Constant.KEY_IS_NIGHT, !isNight).apply();
                recreate();
                break;
            case R.id.menu_cache:

                break;
            case R.id.menu_about:

                break;
        }
        showDrawer(false);
        return true;
    }
}
