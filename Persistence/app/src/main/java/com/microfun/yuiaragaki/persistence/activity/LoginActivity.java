package com.microfun.yuiaragaki.persistence.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.microfun.yuiaragaki.persistence.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by yuiaragaki on 17/3/22.
 */
public class LoginActivity extends BaseCompatActivity {

    @BindView(R.id.img_weixin)
    ImageView mLoginByWeixin;
    @BindView(R.id.img_qq)
    ImageView mLoginByQQ;
    @BindView(R.id.img_sina)
    ImageView mLoginBySina;
    @BindView(R.id.btn_login_by_phone)
    Button mLoginByPhone;
    @BindView(R.id.btn_register)
    Button mRegister;

    @OnClick(R.id.btn_register)
    public void onRegisterClick()
    {
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
