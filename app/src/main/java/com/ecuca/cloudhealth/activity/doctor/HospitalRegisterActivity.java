package com.ecuca.cloudhealth.activity.doctor;


import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 预约挂号
 * Created by tuhualong on 2017/12/21.
 */

public class HospitalRegisterActivity extends BaseActivity {


    @BindView(R.id.rel_register_1)
    RelativeLayout relRegister1;
    @BindView(R.id.rel_register_2)
    RelativeLayout relRegister2;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_hospital_register);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("预约挂号");
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

        //预约挂号
        relRegister1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(HospitalRegisterActivity.this,HospitalRegistrationActivity.class);
                HospitalRegisterActivity.this.startActivity(intent);
            }
        });
        //代理挂号
        relRegister2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HospitalRegisterActivity.this,AgentRegistrationActivity.class);
                HospitalRegisterActivity.this.startActivity(intent);
            }
        });

    }



}
