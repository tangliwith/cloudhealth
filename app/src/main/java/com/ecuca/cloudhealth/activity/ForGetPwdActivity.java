package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/16.
 */

public class ForGetPwdActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_1)
    EditText etPwd1;
    @BindView(R.id.tv_register)
    TextView tvNext;

    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_forget_pwd);
        ButterKnife.bind(this);
        setTitleText("忘记密码");
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

        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent intent=new Intent(ForGetPwdActivity.this,MsgCodeResultActivity.class);
//                intent.putExtra("type",1);
//                ForGetPwdActivity.this.startActivity(intent);
            }
        });
    }

}
