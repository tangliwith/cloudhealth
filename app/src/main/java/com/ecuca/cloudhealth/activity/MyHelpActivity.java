package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/17.
 */

public class MyHelpActivity extends BaseActivity {

    @BindView(R.id.rel_about_us)
    RelativeLayout relAboutUs;
    @BindView(R.id.rel_question)
    RelativeLayout relQuestion;
    @BindView(R.id.rel_feed_back)
    RelativeLayout relFeedBack;

    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_my_help);
        ButterKnife.bind(this);


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



        relAboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyHelpActivity.this,AboutUsActivity.class);
                MyHelpActivity.this.startActivity(intent);
            }
        });
        relQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyHelpActivity.this,AboutUsActivity.class);
                MyHelpActivity.this.startActivity(intent);
            }
        });
        relFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MyHelpActivity.this,FeedBackActivity.class);
                MyHelpActivity.this.startActivity(intent);
            }
        });
    }


}
