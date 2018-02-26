package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/17.
 */

public class ChangePwdActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_1)
    EditText etPwd1;
    @BindView(R.id.tv_next)
    TextView tvNext;

    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_change_pwd);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("修改密码");
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
                String mobile=etPhone.getText().toString().trim();
                if(!MyApplication.getInstance().isMobileNO(mobile)){
                    ToastMessage("请输入正确的手机号");
                    return;
                }
                String pwd=etPwd.getText().toString().trim();
                if(TextUtils.isEmpty(pwd)){
                    ToastMessage("请输入新密码");
                    return;
                }
                if(pwd.length()<6){
                    ToastMessage("密码长度必须大于六位");
                    return;
                }
                String pwd1=etPwd1.getText().toString().trim();
                if(TextUtils.isEmpty(pwd1)){
                    ToastMessage("请确认新密码");
                    return;
                }

                if(!pwd.equals(pwd1)){
                    ToastMessage("两次密码输入不一致");
                    return;
                }

//                Intent intent=new Intent(ChangePwdActivity.this,MsgCodeResultActivity.class);
//                intent.putExtra("mobile",mobile);
//                intent.putExtra("pwd",pwd);
//                intent.putExtra("type",2);
//                ChangePwdActivity.this.startActivity(intent);
            }
        });
    }

}
