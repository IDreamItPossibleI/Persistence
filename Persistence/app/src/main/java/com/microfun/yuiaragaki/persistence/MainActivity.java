package com.microfun.yuiaragaki.persistence;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.Snackbar;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.microfun.yuiaragaki.persistence.activity.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.drawer_layout)
    public DrawerLayout mDrawLayout;
    @BindView(R.id.left_drawer)
    public RelativeLayout mLeftDrawer;
    @BindView(R.id.drawer_top_frame)
    public FrameLayout mTopFrame;
    @BindView(R.id.content_frame_top_navigation)
    public RelativeLayout mTopNavigation;


    private ActionBarDrawerToggle mDrawerToggle;
    private View mDrawerLeftLogin;
    private View mDrawerLeftunLogin;
    //标识是否需要在关闭抽屉后打开新页面
    private boolean isIntent = false;
    private Intent currentIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //隐藏ActionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        //透明状态栏(整体效果仿网易云音乐)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
        {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            );
            View statusBarView = createStatusView(this, getResources().getColor(R.color.colorPrimary));
            ViewGroup contentFrame = (ViewGroup) mDrawLayout.getChildAt(0);
            contentFrame.addView(statusBarView, 0);
            ViewGroup drawer = (ViewGroup) mDrawLayout.getChildAt(1);
            mDrawLayout.setFitsSystemWindows(false);
            contentFrame.setFitsSystemWindows(false);
            contentFrame.setClipToPadding(true);
            drawer.setFitsSystemWindows(false);
        }

        mDrawerToggle = new ActionBarDrawerToggle(this,
                mDrawLayout,
                R.string.drawer_open,
                R.string.drawer_close){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(isIntent && currentIntent != null)
                {
                    startActivity(currentIntent);
                    isIntent = false;
                    currentIntent = null;
                }
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
            params.width = width/5*4;
            params.height = height;
            mLeftDrawer.setLayoutParams(params);
            mDrawLayout.setDrawerListener(mDrawerToggle);
            mDrawLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

            mDrawerLeftLogin = View.inflate(this, R.layout.drawer_left_login_layout, null);
            TextView tvUsername = (TextView) mDrawerLeftLogin.findViewById(R.id.tv_username);
            tvUsername.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(MainActivity.this, "测试点击事件", Toast.LENGTH_SHORT).show();
                    Snackbar.make(mDrawLayout, "测试点击事件", Snackbar.LENGTH_SHORT).show();
                    mTopFrame.removeAllViews();
                    mTopFrame.addView(mDrawerLeftunLogin);
                }
            });
            mTopFrame.addView(mDrawerLeftLogin);
            mDrawerLeftunLogin = View.inflate(this, R.layout.drawer_left_unlogin_layout, null);
            Button btnLogin = (Button) mDrawerLeftunLogin.findViewById(R.id.btn_login_in);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivityAfterDrawerClosed(LoginActivity.class);
                }
            });
        }

        //添加导航栏事件
        RelativeLayout mRlytNavigationList = (RelativeLayout) mTopNavigation.findViewById(R.id.rlyt_navigation_list);
        mRlytNavigationList.setOnClickListener(this);

    }

    private void startActivityAfterDrawerClosed(Class clazz)
    {
        if(mDrawLayout.isDrawerOpen(mLeftDrawer))
        {
            mDrawLayout.closeDrawer(mLeftDrawer);
            isIntent = true;
            Intent intent = new Intent(MainActivity.this, clazz);
            currentIntent = intent;
        }
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     * @param activity
     * @param color
     * @return
     */
    private static View createStatusView(Activity activity, int color)
    {
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);
        //绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.rlyt_navigation_list:
                if(!mDrawLayout.isDrawerOpen(mLeftDrawer))
                {
                    mDrawLayout.openDrawer(mLeftDrawer);
                }
                break;
        }
    }
}
