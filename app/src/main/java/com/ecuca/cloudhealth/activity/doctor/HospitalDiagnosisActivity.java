package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Constant;
import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.HospitalListEntity;
import com.ecuca.cloudhealth.Entity.SectionListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.select.SelectHospitalActivity;
import com.ecuca.cloudhealth.activity.select.SelectProvinceActivity;
import com.ecuca.cloudhealth.dialog.ChoosePayTypeDialog;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/21.
 */

public class HospitalDiagnosisActivity extends BaseActivity {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tel_choose_address)
    RelativeLayout telChooseAddress;
    @BindView(R.id.img_2)
    ImageView img2;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.tel_choose_hospital)
    RelativeLayout telChooseHospital;
    @BindView(R.id.tv_date)
    TextView tvDate;
    @BindView(R.id.tel_choose_date)
    RelativeLayout telChooseDate;
    @BindView(R.id.tv_name)
    EditText tvName;
    @BindView(R.id.tv_mobile)
    EditText tvMobile;
    @BindView(R.id.rb_service_1)
    RadioButton rbService1;
    @BindView(R.id.img_service_1)
    ImageView imgService1;
    @BindView(R.id.tv_service_1_des)
    TextView tvService1Des;
    @BindView(R.id.rb_service_2)
    RadioButton rbService2;
    @BindView(R.id.img_service_2)
    ImageView imgService2;
    @BindView(R.id.tv_service_2_des)
    TextView tvService2Des;
    @BindView(R.id.rb_is_agree)
    RadioButton rbIsAgree;
    @BindView(R.id.tv_agree_des)
    TextView tvAgreeDes;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @BindView(R.id.rel_service_1)
    RelativeLayout relService1;
    @BindView(R.id.rel_service_2)
    RelativeLayout relService2;

    ChoosePayTypeDialog choosePayTypeDialog;


    int province_id, city_id;
    HospitalListEntity.DataBean hospitalBean;

    TimeSelector timeSelector;

    String selectTime;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_hospital_diagnosis);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("特惠陪诊");
    }

    @Override
    protected void initUI() {

        choosePayTypeDialog = new ChoosePayTypeDialog(this, R.style.transparentFrameWindowStyle, new ChoosePayTypeDialog.OnChoosePayTypeListener() {
            @Override
            public void onChooseBalance() {
                Intent intent = new Intent(HospitalDiagnosisActivity.this, SubSuccessActivity.class);
                HospitalDiagnosisActivity.this.startActivity(intent);
                finish();
            }

            @Override
            public void onChooseWeChat() {
                Intent intent = new Intent(HospitalDiagnosisActivity.this, SubSuccessActivity.class);
                HospitalDiagnosisActivity.this.startActivity(intent);
                finish();
            }

            @Override
            public void onChooseAli() {
                Intent intent = new Intent(HospitalDiagnosisActivity.this, SubSuccessActivity.class);
                HospitalDiagnosisActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Calendar a = Calendar.getInstance();
        timeSelector = new TimeSelector(this, new TimeSelector.ResultHandler() {
            @Override
            public void handle(String time) {
                selectTime = time;
                tvDate.setText(selectTime);
            }
        }, String.valueOf(a.get(Calendar.YEAR)) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE) + 1) + " 00:00",
                String.valueOf(a.get(Calendar.YEAR) + 1) + "-" + String.valueOf(a.get(Calendar.MONTH) + 1) + "-" + String.valueOf(a.get(Calendar.DATE)) + " 23:59");
        timeSelector.setMode(TimeSelector.MODE.YMDHM);//
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        telChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalDiagnosisActivity.this, SelectProvinceActivity.class);

                HospitalDiagnosisActivity.this.startActivityForResult(intent, 1000);
            }
        });

        telChooseHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (city_id <= 0) {
                    ToastMessage("请先选择省市");
                    return;
                }
                Intent intent = new Intent(HospitalDiagnosisActivity.this, SelectHospitalActivity.class);
                intent.putExtra("city_id", city_id);
                HospitalDiagnosisActivity.this.startActivityForResult(intent, 1000);

            }
        });
        telChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                timeSelector.show();
            }
        });


        rbService1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rbService2.setChecked(false);
                }

            }
        });

        rbService2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    rbService1.setChecked(false);
                }

            }
        });

        relService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvService1Des.getVisibility() == View.VISIBLE) {
                    tvService1Des.setVisibility(View.GONE);
                    imgService1.setImageResource(R.mipmap.icon_more);
                } else {
                    tvService1Des.setVisibility(View.VISIBLE);
                    imgService1.setImageResource(R.mipmap.icon_more_bottom);
                }
            }
        });

        relService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvService2Des.getVisibility() == View.VISIBLE) {
                    tvService2Des.setVisibility(View.GONE);
                    imgService2.setImageResource(R.mipmap.icon_more);
                } else {
                    tvService2Des.setVisibility(View.VISIBLE);
                    imgService2.setImageResource(R.mipmap.icon_more_bottom);
                }
            }
        });

        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //

                subData();
            }
        });

        tvAgreeDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalDiagnosisActivity.this, ApplyDesActivity.class);
                HospitalDiagnosisActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Constant.SELECT_CITY_RESULTCODE) {
            String text = data.getStringExtra("text");
            city_id = data.getIntExtra("city_id", 0);
            province_id = data.getIntExtra("province_id", 0);
            tvAddress.setText(text);
        } else if (resultCode == Constant.SELECT_HOSPITAL_RESULTCODE) {

            hospitalBean = (HospitalListEntity.DataBean) data.getSerializableExtra("hospital");
            if (hospitalBean != null) {
                tvHospital.setText(hospitalBean.getHospital_name());
            }
        }

    }


    /**
     * 提交数据
     */
    private void subData() {

        if (city_id <= 0) {
            ToastMessage("请选择省市");
            return;
        }
        if (hospitalBean == null) {
            ToastMessage("请选择医院");
            return;
        }

        if (TextUtils.isEmpty(selectTime)) {

            ToastMessage("请选择陪诊时间");
            return;
        }
        String userName = tvName.getText().toString().trim();
        if (TextUtils.isEmpty(userName)) {
            ToastMessage("请输入联系人");
            return;
        }
        String mobile = tvMobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            ToastMessage("请输入联系电话");
            return;
        }

        String type = rbService1.isChecked() ? "1" : "2";


        Map<String, String> m = new HashMap<>();
        m.put("province", String.valueOf(province_id));
        m.put("city", String.valueOf(city_id));
        m.put("hospital", String.valueOf(hospitalBean.getId()));
        m.put("with_time", DateUtils.getTime(selectTime+":00"));
        m.put("username", userName);
        m.put("mobile", mobile);
        m.put("type", type);
        m.put("money", "99");
        HttpUtils.getInstance().post(m, "order/add_visits_order", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        choosePayTypeDialog.show();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("数据异常");
                }
            }
        });
    }
}
