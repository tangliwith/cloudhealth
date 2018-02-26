package com.ecuca.cloudhealth.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/29.
 */

public class FeedBackActivity extends BaseActivity {
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_qq)
    EditText etQq;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_feed_back);
        showTitleBack();
        setTitleText("意见反馈");

    }

    @Override
    protected void initUI() {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
