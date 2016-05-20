package com.brilliantbear.zhihupaper.ui;

import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import com.brilliantbear.zhihupaper.Constant;
import com.brilliantbear.zhihupaper.R;
import com.brilliantbear.zhihupaper.db.DB;
import com.brilliantbear.zhihupaper.db.ZhihuDetail;
import com.brilliantbear.zhihupaper.db.ZhihuStory;
import com.brilliantbear.zhihupaper.db.ZhihuTop;
import com.brilliantbear.zhihupaper.mvp.view.ZhihuListFragment;

import io.realm.Realm;


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
                showDeleteCacheDialog();
                break;
            case R.id.menu_about:
                showAboutDialog();
                break;
        }
        showDrawer(false);
        return true;
    }

    private void showAboutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        TextView textView = new TextView(this);
        textView.setText(Html.fromHtml(getString(R.string.about)));
        builder.setView(textView, 50, 50, 50, 0);
        builder.setTitle(getString(R.string.menu_about));
        builder.setPositiveButton(getString(R.string.confirm), null);
        builder.show();
    }

    private void showDeleteCacheDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.warning);
        builder.setMessage(R.string.delete_cache);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                showProgressDialog();
            }
        });
        builder.show();
    }

    private void showProgressDialog() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.deleting));
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);

        DB.getInstance().getRealm().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(ZhihuStory.class).findAll().clear();
                realm.where(ZhihuDetail.class).findAll().clear();
                realm.where(ZhihuTop.class).findAll().clear();
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                progressDialog.dismiss();
                Snackbar.make(mCoordinator, getString(R.string.delete_cache_success), Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                progressDialog.dismiss();
                Snackbar.make(mCoordinator, getString(R.string.delete_cache_error), Snackbar.LENGTH_SHORT).show();
            }
        });

    }
}
