package com.ecuca.cloudhealth.activity;

import android.text.Html;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/16.
 */

public class RegisterProtocolActivity extends BaseActivity{

    @BindView(R.id.tv_content)
    TextView tvContent;
    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_register_protocol);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("注册协议");
    }

    @Override
    protected void initUI() {

        tvContent.setText(Html.fromHtml(getIntent().getStringExtra("content")));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

    }
}
