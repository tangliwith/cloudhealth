package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.LoginEntity;
import com.ecuca.cloudhealth.Entity.MsgCodeEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.appmanager.AppManager;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by Theron on 2017/11/16.
 */

public class RegisterMsgCodeResultActivity extends BaseActivity {
    @BindView(R.id.tv_code_msg)
    TextView tvCodeMsg;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_ok)
    TextView tvOk;
    CountDownTimer countdownTimer;
    String phone,pwd;
    @Override
    protected void setContentView() {
        Intent intent=getIntent();
        if(!intent.hasExtra("phone")||!intent.hasExtra("pwd")){
            finish();
            return;
        }
        phone=intent.getStringExtra("phone");
        pwd=intent.getStringExtra("pwd");
        if(TextUtils.isEmpty(phone)||TextUtils.isEmpty(pwd)){
            finish();
            return;
        }
        setContentView(R.layout.aty_forget_pwd_result);
        showTitleBack();
        ButterKnife.bind(this);
        setTitleText("注册");
        setTitleRightText("已有账户", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegisterMsgCodeResultActivity.this,LoginActivity.class);
                RegisterMsgCodeResultActivity.this.startActivity(intent);
                finish();
            }
        });
        sendMsgCode(phone);

    }

    @Override
    protected void initUI() {

        tvPhone.setText(phone);
    }

    @Override
    protected void initData() {
        countdownTimer = new CountDownTimer((60) * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                tvTime.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {

                tvTime.setText("");
            }
        };
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String code=etCode.getText().toString().trim();
                if(TextUtils.isEmpty(code)){
                    ToastMessage("请输入验证码");
                    return;
                }

                doRegister(phone,pwd,code);
            }
        });
    }

    /**
     * 注册
     * @param phone
     * @param pwd
     * @param code
     */
    private void doRegister(String phone,String pwd,String code){

        Map<String,String> m=new HashMap<>();
        m.put("mobile",phone);
        m.put("password",pwd);
        m.put("code", code);//推荐码
        HttpUtils.getInstance().post(m, "user/register", new AllCallback<LoginEntity>(LoginEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(LoginEntity response) {

                if(response!=null){
                    if(response.getCode()==200){

                        ToastMessage(response.getMsg());
                        AppManager.getAppManager().finishAllActivity();
                        Intent intent=new Intent(RegisterMsgCodeResultActivity.this,LoginActivity.class);
                        RegisterMsgCodeResultActivity.this.startActivity(intent);

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

    /**
     * 获取短信验证码
     * @param mobile
     */
    private void sendMsgCode(String mobile){

        Map<String,String> m=new HashMap<>();
        m.put("mobile",mobile);
        m.put("from","register");
        HttpUtils.getInstance().post(m, "send_sms", new AllCallback<MsgCodeEntity>(MsgCodeEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(MsgCodeEntity response) {

                if(response!=null){

                    if(response.getCode()==200){
                        tvCodeMsg.setText(Html.fromHtml("我们已发送<font color='#3F42C6'>验证码</font>到您的手机"));
                        countdownTimer.start();
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
