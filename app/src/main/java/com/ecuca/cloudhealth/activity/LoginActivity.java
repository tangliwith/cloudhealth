package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.LoginEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Theron on 2017/11/16.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.img_change_pwd_status)
    ImageView imgChangePwdStatus;
    @BindView(R.id.lin_forget_pwd)
    LinearLayout linForgetPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    boolean isShow;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_login);
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



        linForgetPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,ForGetPwdActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                LoginActivity.this.startActivity(intent);
            }
        });

        imgChangePwdStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(isShow){
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    imgChangePwdStatus.setImageResource(R.mipmap.icon_login_pwd_no_see);
                    isShow=false;

                }
                else{
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    imgChangePwdStatus.setImageResource(R.mipmap.icon_login_pwd_see);
                    isShow=true;
                }

            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone=etPhone.getText().toString().trim();
                String pwd=etPwd.getText().toString().trim();
                if(!MyApplication.getInstance().isMobileNO(phone)){
                    ToastMessage("请输入正确的手机号码");
                    return;
                }
                if(TextUtils.isEmpty(pwd)){

                    ToastMessage("请输入密码");
                    return;
                }
                doLogin(phone,pwd);
            }
        });
    }

    /**
     * 登录
     * @param phone
     * @param pwd
     */
    private void doLogin(String phone,String pwd){

        Map<String,String> m=new HashMap<>();
        m.put("user_name",phone);
        m.put("password",pwd);
        HttpUtils.getInstance().post(m, "user/login", new AllCallback<LoginEntity>(LoginEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(LoginEntity response) {

                if(response!=null){
                    if(response.getCode()==200){

                        MyApplication.getInstance().saveUserInfo(response.getData());
                        ToastMessage(response.getMsg());
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        LoginActivity.this.startActivity(intent);

                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("服务器异常");
                }
            }
        });
    }

}
