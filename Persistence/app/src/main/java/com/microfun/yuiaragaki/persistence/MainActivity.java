package com.microfun.yuiaragaki.persistence;

import android.os.Build;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawLayout;
    @BindView(R.id.left_drawer)
    public RelativeLayout mLeftDrawer;
    @BindView(R.id.drawer_top_frame)
    public FrameLayout mTopFrame;
    @BindView(R.id.tv_username)
    public TextView tvUserName;

    private ActionBarDrawerToggle mDrawerToggle;
    private View mDrawerLeftLogin;
    private View mDrawerLeftunLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //透明状态栏
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
        }
        ButterKnife.bind(this);

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawLayout,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        if(mDrawLayout != null)
        {
            //按比例设置抽屉宽度
            WindowManager windowManager = getWindowManager();
            int width = windowManager.getDefaultDisplay().getWidth();
            int height = windowManager.getDefaultDisplay().getHeight();
            ViewGroup.LayoutParams params = mLeftDrawer.getLayoutParams();
            params.width = width/3*2;
            params.height = height;
            mLeftDrawer.setLayoutParams(params);
            mDrawLayout.setDrawerListener(mDrawerToggle);
            mDrawLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

            mDrawerLeftLogin = View.inflate(this, R.layout.drawer_left_login_layout, null);
            mTopFrame.addView(mDrawerLeftLogin);
        }

    }
}
