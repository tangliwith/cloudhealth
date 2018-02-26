package com.ecuca.cloudhealth.activity.me;

import android.view.View;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约挂号 1
 * Created by tuhualong on 2017/12/21.
 */

public class MeNullActivity extends BaseActivity {
    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    private String title;
    @Override
    protected void setContentView() {

        title=getIntent().getStringExtra("title");


        setContentView(R.layout.aty_me_null);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText(title==null?"":title);
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
