package com.ecuca.cloudhealth.activity.doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.CallDoctorDetailsEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/28.
 */

public class VideoCallActivity extends BaseActivity {
    @BindView(R.id.tv_do_call)
    TextView tvDoCall;
    @BindView(R.id.img_head)
    ImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_doctor_content)
    TextView tvDoctorContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_service_info)
    TextView tvServiceInfo;
    @BindView(R.id.tv_promise)
    TextView tvPromise;

    int doctor_id;
    int order_id;
    @Override
    protected void setContentView() {

        doctor_id = getIntent().getIntExtra("doctor_id", 0);
        order_id = getIntent().getIntExtra("order_id", 0);

        setContentView(R.layout.aty_video_call);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("视频咨询");
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

        getData();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        tvDoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(VideoCallActivity.this, ReserveInfoActivity.class);
                intent.putExtra("type", 3);
                intent.putExtra("doctor_id", doctor_id);
                intent.putExtra("order_id", order_id);
                VideoCallActivity.this.startActivity(intent);
                finish();
            }
        });
    }


    private void getData() {

        Map<String, String> m = new HashMap<>();
        m.put("uid", String.valueOf(doctor_id));
        m.put("type", "2");
        HttpUtils.getInstance().post(m, "doctor/get_doctors_detail", new AllCallback<CallDoctorDetailsEntity>(CallDoctorDetailsEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(CallDoctorDetailsEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        if (response.getData() != null) {

                            setData(response.getData());
                        } else {
                            ToastMessage("获取资料失败");
                            finish();
                        }
                    } else {
                        ToastMessage(response.getMsg());
                        finish();
                    }
                } else {
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });
    }

    /**
     * 设置数据
     *
     * @param bean
     */
    private void setData(CallDoctorDetailsEntity.DataBean bean) {

        if (bean != null) {
            GlideUtils.LoadImg(imgHead, bean.getAvatar_url());
            tvName.setText(bean.getNick_name() == null ? "" : bean.getNick_name());
            if (bean.getCate() != null)
                tvLevel.setText(bean.getCate().getTitle() == null ? "" : bean.getCate().getTitle());
            String content = "";
            if (bean.getHospital() != null) {
                content = bean.getHospital().getHospital_name();

            }
            if (bean.getSection() != null) {
                content = content + " " + bean.getSection().getTitle();
            }
            tvDoctorContent.setText(content);

            tvPrice.setText(bean.getPrice() + "元/次");
            tvServiceInfo.setText(Html.fromHtml(bean.getDescription() == null ? "" : bean.getDescription()));
            tvPromise.setText(Html.fromHtml(bean.getPromise() == null ? "" : bean.getPromise()));
        } else {
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
