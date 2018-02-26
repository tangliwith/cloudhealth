package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/** 医生筛选
 * Created by tuhualong on 2017/12/24.
 */

public class DoctorScreenActivity extends BaseActivity {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rel_choose_address)
    RelativeLayout relChooseAddress;
    @BindView(R.id.tv_hospital)
    TextView tvHospital;
    @BindView(R.id.rel_choose_hospital)
    RelativeLayout relChooseHospital;
    @BindView(R.id.tv_subject)
    TextView tvSubject;
    @BindView(R.id.rel_choose_subject)
    RelativeLayout relChooseSubject;
    @BindView(R.id.list_view_service_type)
    RecyclerView listViewServiceType;
    @BindView(R.id.list_view_doctor_level)
    RecyclerView listViewDoctorLevel;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    ServiceTypeAdapter serviceTypeAdapter;
    List<String> serviceTypeList=new ArrayList<>();
    int selectType;

    LevelAdapter levelAdapter;
    List<String> levelList=new ArrayList<>();
    int selectLevel;


    int province_id,city_id;
    HospitalListEntity.DataBean hospitalBean;
    SectionListEntity.DataBean sectionBean;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_doctor_screen);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("医生筛选");
    }

    @Override
    protected void initUI() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        listViewServiceType.setLayoutManager(gridLayoutManager);


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 4, LinearLayoutManager.VERTICAL, false);
        listViewDoctorLevel.setLayoutManager(gridLayoutManager2);
    }

    @Override
    protected void initData() {

        serviceTypeList.add("不限");
        serviceTypeList.add("图文");
        serviceTypeList.add("语音");
        serviceTypeList.add("视频");
        serviceTypeAdapter=new ServiceTypeAdapter(serviceTypeList);
        listViewServiceType.setAdapter(serviceTypeAdapter);



        levelList.add("不限");
        levelList.add("普通");
        levelList.add("专家");
        levelList.add("教授");
        levelList.add("知名教授");
        levelAdapter=new LevelAdapter(levelList);
        listViewDoctorLevel.setAdapter(levelAdapter);

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        relChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DoctorScreenActivity.this,SelectProvinceActivity.class);

                DoctorScreenActivity.this.startActivityForResult(intent,1000);
            }
        });

        relChooseHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(city_id<=0){
                    ToastMessage("请先选择省市");
                    return;
                }
                Intent intent=new Intent(DoctorScreenActivity.this,SelectHospitalActivity.class);
                intent.putExtra("city_id",city_id);
                DoctorScreenActivity.this.startActivityForResult(intent,1000);

            }
        });
        relChooseSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DoctorScreenActivity.this,SelectSectionActivity.class);
                DoctorScreenActivity.this.startActivityForResult(intent,1000);
            }
        });
    }


    /**
     * 咨询服务
     */
    class ServiceTypeAdapter extends RecyclerView.Adapter<ServiceTypeAdapter.ViewHolder> {


        private List<String> list;

        public ServiceTypeAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public ServiceTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ServiceTypeAdapter.ViewHolder holder, final int position) {
            holder.tvText.setText(list.get(position));
            if(selectType==position){

                holder.tvText.setTextColor(getResources().getColor(R.color.white));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);

            }
            else{
                holder.tvText.setTextColor(getResources().getColor(R.color.app_title_top));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectType=position;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


    /**
     * 咨询服务
     */
    class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {


        private List<String> list;

        public LevelAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public LevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LevelAdapter.ViewHolder holder, final int position) {
            holder.tvText.setText(list.get(position));
            if(selectType==position){

                holder.tvText.setTextColor(getResources().getColor(R.color.white));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);

            }
            else{
                holder.tvText.setTextColor(getResources().getColor(R.color.app_title_top));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectType=position;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
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
                tvSubject.setText(sectionBean.getTitle());
            }



        }

    }

}
