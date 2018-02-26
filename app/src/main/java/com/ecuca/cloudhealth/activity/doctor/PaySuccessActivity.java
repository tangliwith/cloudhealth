package com.ecuca.cloudhealth.activity.doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.DoctorHomePageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class PaySuccessActivity extends BaseActivity {
    @BindView(R.id.tv_do_call)
    TextView tvDoCall;
    int type=0;
    int doctor_id;
    int order_id;
    @Override
    protected void setContentView() {
        order_id=getIntent().getIntExtra("order_id",0);
        doctor_id=getIntent().getIntExtra("doctor_id",0);
        type=getIntent().getIntExtra("type",0);
        setContentView(R.layout.aty_pay_success);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("支付成功");
    }
    protected void showTitleBack() {
        LinearLayout mLinBack = getID(R.id.lin_title_lelft);
        ImageView mImgBack = getID(R.id.img_title_left);
        mImgBack.setVisibility(View.VISIBLE);
        mLinBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        showAlertDialogMessage("提示", "是否要放弃咨询?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
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

    }

    @Override
    protected void startFunction() {

        tvDoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (type == 1) {

                    Intent intent = new Intent(PaySuccessActivity.this, ImageTextCallActivity.class);
                    intent.putExtra("doctor_id", doctor_id);
                    intent.putExtra("order_id",order_id);
                    PaySuccessActivity.this.startActivity(intent);
                    finish();
                } else if (type == 2) {
                    Intent intent = new Intent(PaySuccessActivity.this, VoiceCallActivity.class);
                    intent.putExtra("doctor_id", doctor_id);
                    intent.putExtra("order_id",order_id);
                    PaySuccessActivity.this.startActivity(intent);
                    finish();


                } else if (type == 3) {
                    Intent intent = new Intent(PaySuccessActivity.this, VideoCallActivity.class);
                    intent.putExtra("doctor_id", doctor_id);
                    intent.putExtra("order_id",order_id);
                    PaySuccessActivity.this.startActivity(intent);
                    finish();
                }
                finish();
            }
        });
    }

}
