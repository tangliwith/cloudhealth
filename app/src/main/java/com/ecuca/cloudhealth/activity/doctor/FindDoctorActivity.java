package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.DoctorHomePageActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2018/1/2.
 */

public class FindDoctorActivity extends BaseActivity {
    @BindView(R.id.list_view)
    MyRecycleView listView;

    MyAdapter adapter;

    LinearLayout linBtn1,linBtn2,linBtn3,linBtn4;

    RelativeLayout relMore;
    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_find_doctor);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("找医生");
    }

    @Override
    protected void initUI() {

        listView.setPullRefreshEnabled(false);
        listView.setLoadingMoreEnabled(false);
        View v = LayoutInflater.from(this).inflate(R.layout.view_find_doctor_head, null);
        linBtn1= (LinearLayout) v.findViewById(R.id.find_doctor_btn_1);
        linBtn2= (LinearLayout) v.findViewById(R.id.find_doctor_btn_2);
        linBtn3= (LinearLayout) v.findViewById(R.id.find_doctor_btn_3);
        linBtn4= (LinearLayout) v.findViewById(R.id.find_doctor_btn_4);
        relMore= (RelativeLayout) v.findViewById(R.id.rel_more);
        listView.addHeaderView(v);

    }

    @Override
    protected void initData() {

        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        linBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //这个是陪诊的界面
                Intent intent = new Intent(FindDoctorActivity.this, HospitalDiagnosisActivity.class);
                FindDoctorActivity.this.startActivity(intent);
            }
        });
        linBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, FindDoctorByHospitalActivity.class);
                FindDoctorActivity.this.startActivity(intent);
            }
        });
        linBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, FindDoctorByOfficeActivity.class);
                FindDoctorActivity.this.startActivity(intent);
            }
        });
        linBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, FindDoctorByDiseaseActivity.class);
                FindDoctorActivity.this.startActivity(intent);
            }
        });
        relMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FindDoctorActivity.this, DoctorListActivity.class);
                FindDoctorActivity.this.startActivity(intent);
            }
        });

    }


    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_doctor, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(FindDoctorActivity.this, DoctorHomePageActivity.class);
                    FindDoctorActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 5;
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_head)
            CircleImageView imgHead;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_level)
            TextView tvLevel;
            @BindView(R.id.tv_content)
            TextView tvContent;
            @BindView(R.id.tv_skill)
            TextView tvSkill;
            @BindView(R.id.tv_counselling_num)
            TextView tvCounsellingNum;
            @BindView(R.id.tv_evaluate_num)
            TextView tvEvaluateNum;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
