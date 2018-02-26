package com.ecuca.cloudhealth.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.appmanager.AppManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Theron on 2017/11/17.
 */

public class SystemSettingActivity extends BaseActivity {
    @BindView(R.id.rel_change_pwd)
    RelativeLayout relChangePwd;
    @BindView(R.id.rel_change_mobile)
    RelativeLayout relChangeMobile;
    @BindView(R.id.rel_logout)
    TextView relLogout;
    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_system_setting);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("系统设置");
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


        relChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SystemSettingActivity.this,ChangePwdActivity.class);
                SystemSettingActivity.this.startActivity(intent);
            }
        });
        relLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                showAlertDialogMessage("提示", "是否要退出登录？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        MyApplication.getInstance().removeUserInfo();
                        AppManager.getAppManager().finishAllActivity();
                        Intent intent=new Intent(SystemSettingActivity.this,LoginActivity.class);
                        SystemSettingActivity.this.startActivity(intent);
                    }
                });

            }
        });
    }

}
