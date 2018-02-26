package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.DoctorHomePageActivity;
import com.ecuca.cloudhealth.dialog.ChooseDoctorTypeDialog;
import com.ecuca.cloudhealth.dialog.ChooseProvinceDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2018/1/8.
 */

public class DoctorListActivity extends BaseActivity {
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lin_choose_address)
    LinearLayout linChooseAddress;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.lin_choose_type)
    LinearLayout linChooseType;
    @BindView(R.id.list_view)
    RecyclerView listView;

    @BindView(R.id.img_choose_address)
    ImageView imgChooseAddress;
    @BindView(R.id.img_choose_type)
    ImageView imgChooseType;
    MyAdapter adapter;
    ChooseDoctorTypeDialog doctorTypeDialog;

    ChooseProvinceDialog chooseProvinceDialog;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_doctor_list);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("医生列表");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        doctorTypeDialog=new ChooseDoctorTypeDialog(DoctorListActivity.this, new ChooseDoctorTypeDialog.ChooseDoctorTypeDialogListener() {
            @Override
            public void onShow() {

                tvType.setTextColor(getResources().getColor(R.color.app_title_top));
                imgChooseType.setImageResource(R.mipmap.icon_choose_ed);
            }

            @Override
            public void onDismiss() {
                tvType.setTextColor(Color.parseColor("#ff666666"));
                imgChooseType.setImageResource(R.mipmap.icon_choose_un);
            }
        });

        chooseProvinceDialog=new ChooseProvinceDialog(DoctorListActivity.this, new ChooseProvinceDialog.ChooseProvinceDialogListener(){

            @Override
            public void onShow() {
                tvAddress.setTextColor(getResources().getColor(R.color.app_title_top));
                imgChooseAddress.setImageResource(R.mipmap.icon_choose_ed);
            }

            @Override
            public void onDismiss(String address) {
                tvAddress.setText(address);
                tvAddress.setTextColor(Color.parseColor("#ff666666"));
                imgChooseAddress.setImageResource(R.mipmap.icon_choose_un);
            }
        });
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


        //筛选
        linChooseType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                doctorTypeDialog.showAsDropDown(linChooseType);
            }
        });
        linChooseAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseProvinceDialog.showAsDropDown(linChooseAddress);
            }
        });
    }



    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_doctor, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(DoctorListActivity.this, DoctorHomePageActivity.class);
                    DoctorListActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 15;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
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
