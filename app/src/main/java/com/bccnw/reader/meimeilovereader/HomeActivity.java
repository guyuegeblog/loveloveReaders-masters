package com.bccnw.reader.meimeilovereader;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.bccnw.reader.meimeilovereader.tool.CommonUtils;
import com.bccnw.reader.meimeilovereader.view.statusbar.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @Bind(R.id.view_status)
    View viewStatus;
    @Bind(R.id.iv_title_menu)
    ImageView ivTitleMenu;
    @Bind(R.id.ll_title_menu)
    FrameLayout llTitleMenu;
    @Bind(R.id.iv_title_gank)
    ImageView ivTitleGank;
    @Bind(R.id.iv_title_one)
    ImageView ivTitleOne;
    @Bind(R.id.iv_title_dou)
    ImageView ivTitleDou;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.vp_content)
    ViewPager vpContent;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navView;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    private HomeActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        mContext = this;
        initStatusView();
    }

    private void initStatusView() {
        ViewGroup.LayoutParams layoutParams = viewStatus.getLayoutParams();
        layoutParams.height = StatusBarUtil.getStatusBarHeight(this);
        viewStatus.setLayoutParams(layoutParams);
        StatusBarUtil.setColorNoTranslucentForDrawerLayout(mContext, drawerLayout,
                CommonUtils.getColor(R.color.colorTheme));
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //去除默认Title显示
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * inflateHeaderView 进来的布局要宽一些
     */
    private void initDrawerLayout() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case R.id.action_channel:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 不退出程序，进入后台
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({R.id.ll_title_menu,R.id.iv_title_dou,R.id.iv_title_gank,R.id.iv_title_one})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_title_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_title_gank:
                ivTitleGank.setSelected(true);
                ivTitleOne.setSelected(false);
                ivTitleDou.setSelected(false);
                break;
            case R.id.iv_title_one:
                ivTitleGank.setSelected(false);
                ivTitleOne.setSelected(true);
                ivTitleDou.setSelected(false);
                break;
            case R.id.iv_title_dou:
                ivTitleGank.setSelected(false);
                ivTitleOne.setSelected(false);
                ivTitleDou.setSelected(true);
                break;
        }
    }
}
