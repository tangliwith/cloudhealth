package com.ecuca.cloudhealth.activity.doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 慢病保养 医生简介
 * Created by tuhualong on 2018/1/1.
 */

public class KeepFitActivity extends BaseActivity {


    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_keep_fit);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("慢病保养");
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


        tvBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }



}
