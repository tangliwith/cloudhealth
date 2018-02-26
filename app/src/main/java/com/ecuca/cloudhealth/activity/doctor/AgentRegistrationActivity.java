package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Constant;
import com.ecuca.cloudhealth.Entity.HospitalListEntity;
import com.ecuca.cloudhealth.Entity.SectionListEntity;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.select.SelectHospitalActivity;
import com.ecuca.cloudhealth.activity.select.SelectProvinceActivity;
import com.ecuca.cloudhealth.activity.select.SelectSectionActivity;

import butterknife.BindView;
import butterknife.ButterKnife;



/**
 * 代理挂号
 * Created by tuhualong on 2017/12/21.
 */

public class AgentRegistrationActivity extends BaseActivity {


    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rel_choose_address)
    RelativeLayout relChooseAddress;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.rel_choose_hospital)
    RelativeLayout relChooseHospital;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.rel_choose_department)
    RelativeLayout relChooseDepartment;
    @BindView(R.id.tv_next)
    TextView tvNext;


    int province_id,city_id;
    HospitalListEntity.DataBean hospitalBean;
    SectionListEntity.DataBean sectionBean;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_agent_registration);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("代理挂号");
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

        relChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgentRegistrationActivity.this,SelectProvinceActivity.class);

                AgentRegistrationActivity.this.startActivityForResult(intent,1000);
            }
        });

        relChooseHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city_id<=0){
                    ToastMessage("请先选择省市");
                    return;
                }
                Intent intent=new Intent(AgentRegistrationActivity.this,SelectHospitalActivity.class);
                intent.putExtra("city_id",city_id);
                AgentRegistrationActivity.this.startActivityForResult(intent,1000);

            }
        });
        relChooseDepartment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(AgentRegistrationActivity.this,SelectSectionActivity.class);
                AgentRegistrationActivity.this.startActivityForResult(intent,1000);
            }
        });
        tvNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(AgentRegistrationActivity.this,SearchDoctorByRegistrationActivity.class);
                intent.putExtra("city_id",city_id);
                intent.putExtra("province_id",province_id);
                if(hospitalBean!=null)
                intent.putExtra("hospital_id",hospitalBean.getId());
                if(sectionBean!=null)
                intent.putExtra("section_id",sectionBean.getId());
                AgentRegistrationActivity.this.startActivity(intent);
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== Constant.SELECT_CITY_RESULTCODE){
            String text=data.getStringExtra("text");
            city_id=data.getIntExtra("city_id",0);
            province_id=data.getIntExtra("province_id",0);
            tvAddress.setText(text);
        }
        else if(resultCode==Constant.SELECT_HOSPITAL_RESULTCODE){

            hospitalBean= (HospitalListEntity.DataBean) data.getSerializableExtra("hospital");
            if(hospitalBean!=null){
                tvHospital.setText(hospitalBean.getHospital_name());
            }
        }
        else if(resultCode==Constant.SELECT_SECTION_RESULTCODE){

            sectionBean= (SectionListEntity.DataBean) data.getSerializableExtra("section");
            if(sectionBean!=null){
                tvDepartment.setText(sectionBean.getTitle());
            }



        }

    }
}
