package com.ecuca.cloudhealth.activity.doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 挂号预付
 * Created by tuhualong on 2017/12/26.
 */

public class CreateRegisteredOrderActivity extends BaseActivity {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.rel_choose_time)
    RelativeLayout relChooseTime;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_goods_name)
    TextView tvGoodsName;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_pay_total_price)
    TextView tvPayTotalPrice;
    @BindView(R.id.tv_do_pay)
    TextView tvDoPay;


    TimeSelector timeSelector;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_create_registered_order);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("挂号预付");
    }

    @Override
    protected void initUI() {
        Calendar a = Calendar.getInstance();
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                time = time.substring(0, time.indexOf(" "));
                tvTime.setText(time);
            }
        }, String.valueOf(a.get(Calendar.YEAR)) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE) + 1) + " 10:34", String.valueOf(a.get(Calendar.YEAR)+20) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE)) + " 17:34");
        timeSelector.setMode(TimeSelector.MODE.YMD);//

    }

    @Override
    protected void initData() {


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        relChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSelector.show();
            }
        });
//        tvDoPay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ChoosePayTypeDialog dialog=new ChoosePayTypeDialog(CreateRegisteredOrderActivity.this, R.style.transparentFrameWindowStyle, new ChoosePayTypeDialog.OnChoosePayTypeListener() {
//                    @Override
//                    public void onChooseBalance() {
//
//                        Intent intent=new Intent(CreateRegisteredOrderActivity.this,PaySuccessActivity.class);
//                        intent.putExtra("type",type);
//                        CreateRegisteredOrderActivity.this.startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onChooseWeChat() {
//                        Intent intent=new Intent(CreateRegisteredOrderActivity.this,PaySuccessActivity.class);
//                        intent.putExtra("type",type);
//                        CreateRegisteredOrderActivity.this.startActivity(intent);
//                        finish();
//                    }
//
//                    @Override
//                    public void onChooseAli() {
//                        Intent intent=new Intent(CreateRegisteredOrderActivity.this,PaySuccessActivity.class);
//                        intent.putExtra("type",type);
//                        CreateRegisteredOrderActivity.this.startActivity(intent);
//                        finish();
//                    }
//                });
//                dialog.show();
//            }
//        });
    }



}
