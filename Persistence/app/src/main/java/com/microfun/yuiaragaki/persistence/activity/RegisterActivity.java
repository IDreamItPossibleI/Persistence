package com.microfun.yuiaragaki.persistence.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.microfun.yuiaragaki.persistence.R;
import com.microfun.yuiaragaki.persistence.util.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by yuiaragaki on 17/3/22.
 */
public class RegisterActivity extends AppCompatActivity {

//    @BindView(R.id.action_bar)
    View mActionBar;
//    @BindView(R.id.content_frame)
    LinearLayout mContentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        ButterKnife.bind(this);
        mActionBar = findViewById(R.id.action_bar);
        mContentFrame = (LinearLayout) findViewById(R.id.content_frame);
        UIUtils.setStatusBar(this, mContentFrame);
    }
}
