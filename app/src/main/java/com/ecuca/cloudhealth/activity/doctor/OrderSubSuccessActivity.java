package com.ecuca.cloudhealth.activity.doctor;

import android.view.View;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 提交成功
 * Created by tuhualong on 2017/12/21.
 */

public class OrderSubSuccessActivity extends BaseActivity {
    @BindView(R.id.tv_bottom)
    TextView tvBottom;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_sub_success);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("提交成功");
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
