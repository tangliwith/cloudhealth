package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/29.
 */

public class AboutActivity extends BaseActivity {
    @BindView(R.id.tv_version)
    TextView tvVersion;
    @BindView(R.id.rel_about_us)
    RelativeLayout relAboutUs;
    @BindView(R.id.rel_question)
    RelativeLayout relQuestion;
    @BindView(R.id.rel_feedback)
    RelativeLayout relFeedback;
    @BindView(R.id.tv_tell)
    TextView tvTell;
    @BindView(R.id.img_more)
    ImageView imgMore;
    @BindView(R.id.rel_contact_us)
    RelativeLayout relContactUs;

    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_about);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("关于帮帮");
    }

    @Override
    protected void initUI() {

        tvVersion.setText(MyApplication.getInstance().getVersionName());
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        relAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, AboutUsActivity.class);
                AboutActivity.this.startActivity(intent);
            }
        });
        relFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, FeedBackActivity.class);
                AboutActivity.this.startActivity(intent);
            }
        });

    }


}
