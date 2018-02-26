package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.RegisterAgreementEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;



import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Administrator on 2016/7/4.
 */
public class RegisterActivity extends BaseActivity {


    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.et_pwd_1)
    EditText etPwd1;
    @BindView(R.id.tv_register)
    TextView tvRegister;





    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_register);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("注册");
        setTitleRightText("注册协议", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRegisterAgreement();
            }
        });
    }

    @Override
    protected void initUI() {
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initEvent() {


        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final String phone = etPhone.getText().toString().trim();

                final String pwd = etPwd.getText().toString().trim();
                String pwd1 = etPwd1.getText().toString().trim();
                if (!MyApplication.getInstance().isMobileNO(phone)) {
                    ToastMessage("请输入正确的手机号码");
                    return;
                }

                if (TextUtils.isEmpty(pwd)) {

                    ToastMessage("请输入密码");
                    return;
                }
                if (TextUtils.isEmpty(pwd1)) {
                    ToastMessage("请重复您的密码");
                    return;
                }
                if (!pwd.equals(pwd1)) {
                    ToastMessage("两次密码不相同");
                    return;
                }
                Intent intent = new Intent(RegisterActivity.this, RegisterMsgCodeResultActivity.class);
                intent.putExtra("phone", phone);
                intent.putExtra("pwd", pwd);
                RegisterActivity.this.startActivity(intent);
                RegisterActivity.this.finish();


            }
        });
    }

    @Override
    protected void startFunction() {


    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }


    private void getRegisterAgreement() {

        HttpUtils.getInstance().post(null, "main/get_regist_protocol", new AllCallback<RegisterAgreementEntity>(RegisterAgreementEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(RegisterAgreementEntity response) {


                if (response != null) {

                    if (response.getCode() == 200) {

                        Intent intent = new Intent(RegisterActivity.this, RegisterProtocolActivity.class);
                        intent.putExtra("content", response.getData());
                        RegisterActivity.this.startActivity(intent);
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("服务器异常");
                }
            }
        });
    }

}
