package com.ecuca.cloudhealth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.doctor.FindDoctorActivity;
import com.ecuca.cloudhealth.activity.doctor.HospitalActivity;
import com.ecuca.cloudhealth.activity.doctor.HospitalConsultationActivity;
import com.ecuca.cloudhealth.activity.doctor.HospitalDiagnosisActivity;
import com.ecuca.cloudhealth.activity.doctor.HospitalRegisterActivity;
import com.ecuca.cloudhealth.activity.doctor.KeepFitActivity;
import com.ecuca.cloudhealth.activity.doctor.SelfHealthActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by tuhualong on 2017/12/18.
 */

public class DoctorFragment extends BaseFragment {


    @BindView(R.id.btn_registration)
    CardView btnRegistration;
    @BindView(R.id.btn_consultation_doctor)
    CardView btnConsultationDoctor;
    @BindView(R.id.btn_find_doctor)
    CardView btnFindDoctor;
    @BindView(R.id.btn_keep_fit)
    CardView btnKeepFit;
    @BindView(R.id.btn_health_check)
    CardView btnHealthCheck;
    Unbinder unbinder;

    @Override
    protected void setContentView() {


        setContentView(R.layout.frg_doctor);
        setTitleText("健康服务");
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

        //预约挂号
        btnRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HospitalRegisterActivity.class);
                getActivity().startActivity(intent);
//                Intent intent = new Intent(getActivity(), HospitalActivity.class);
//                getActivity().startActivity(intent);
            }
        });
        //咨询医生
        btnConsultationDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HospitalConsultationActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //找医生
        btnFindDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), FindDoctorActivity.class);
                getActivity().startActivity(intent);
            }
        });
        // 慢病保养
        btnKeepFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), KeepFitActivity.class);
                getActivity().startActivity(intent);

            }
        });
        //健康自查
        btnHealthCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SelfHealthActivity.class);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
