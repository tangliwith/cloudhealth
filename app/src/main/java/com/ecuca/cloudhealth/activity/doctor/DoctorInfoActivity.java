package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.DoctorDetailsEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/25.
 */

public class DoctorInfoActivity extends BaseActivity{


    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_doctor_content)
    TextView tvDoctorContent;
    @BindView(R.id.tv_brief)
    TextView tvBrief;
    @BindView(R.id.tv_be_good_at)
    TextView tvBeGoodAt;
    @BindView(R.id.tv_registration_doctor)
    TextView tvRegistrationDoctor;

    int doctor_id;
    @Override
    protected void setContentView() {

        doctor_id=getIntent().getIntExtra("doctor_id",0);
        setContentView(R.layout.aty_doctor_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("医生简介");
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


        tvRegistrationDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorInfoActivity.this, CreateRegisteredOrderActivity.class);
                DoctorInfoActivity.this.startActivity(intent);
            }
        });
    }



    private void getData(){

        Map<String,String> m=new HashMap<>();
        m.put("doctor_id",String.valueOf(doctor_id));
        HttpUtils.getInstance().post(m, "doctor/doctor_detail", new AllCallback<DoctorDetailsEntity>(DoctorDetailsEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(DoctorDetailsEntity response) {

                if(response!=null){

                    if(response.getCode()==200){

                        if(response.getData()!=null){

                            setData(response.getData());
                        }
                        else{
                            ToastMessage("获取资料失败");
                            finish();
                        }
                    }
                    else{
                        ToastMessage(response.getMsg());
                        finish();
                    }
                }
                else{
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });
    }
    /**
     * 设置数据
     * @param bean
     */
    private void setData(DoctorDetailsEntity.DataBean bean){

        if(bean!=null){
            GlideUtils.LoadImg(imgHead,bean.getAvatar_url());
            tvName.setText(bean.getNick_name()==null?"":bean.getNick_name());
            if(bean.getCate()!=null)
                tvLevel.setText(bean.getCate().getTitle()==null?"":bean.getCate().getTitle());
            String content="";
            if(bean.getHospital()!=null){
                content=bean.getHospital().getHospital_name();

            }
            if(bean.getSection()!=null){
                content=content+" "+bean.getSection().getTitle();
            }
            tvDoctorContent.setText(content);



            tvBrief.setText(bean.getPersonal_profile()==null?"":bean.getPersonal_profile());
            tvBeGoodAt.setText(bean.getProfession()==null?"":bean.getProfession());
        }
        else{
            finish();
        }
    }

}
