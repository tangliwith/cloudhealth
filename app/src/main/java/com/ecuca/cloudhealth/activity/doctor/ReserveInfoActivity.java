package com.ecuca.cloudhealth.activity.doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.DoImageTextCallEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/1.
 */

public class ReserveInfoActivity extends BaseActivity {
    @BindView(R.id.tv_sub)
    TextView tvSub;
    @BindView(R.id.et_content)
    EditText etContent;
    int doctor_id;
    int order_id;
    int type;
    @Override
    protected void setContentView() {
        type = getIntent().getIntExtra("type", 0);
        doctor_id = getIntent().getIntExtra("doctor_id", 0);
        order_id = getIntent().getIntExtra("order_id", 0);
        setContentView(R.layout.aty_reserve_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("预留信息");
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



        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                subData();



            }
        });

    }



    /**
     * 提交数据
     */
    private void subData() {

        Map<String,String> strM=new HashMap<>();
        strM.put("order_id",String.valueOf(order_id));
        HashMap<String, File> m = new HashMap<>();

        String message=etContent.getText().toString().trim();
        if(TextUtils.isEmpty(message)){
            ToastMessage("请输入详细描述");
            return;
        }
        strM.put("message",message);

        showProgressDialog("提交中...");
        HttpUtils.getInstance().postFile(strM, m, "order/graphic_consultation", new AllCallback<DoImageTextCallEntity>(DoImageTextCallEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                disProgressDialog();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(DoImageTextCallEntity response) {
                disProgressDialog();
                if (response != null) {

                    if(response.getCode()==200){

                        Intent intent=new Intent(ReserveInfoActivity.this,DoImageTextCallTwoActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("log_id",response.getData());
                        ReserveInfoActivity.this.startActivity(intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("提交失败");
                }
            }
        });
    }
}
