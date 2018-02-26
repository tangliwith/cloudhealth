package com.ecuca.cloudhealth.activity.doctor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.View.ShapeImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2018/1/8.
 */

public class FindDoctorByHospitalActivity extends BaseActivity {
    @BindView(R.id.lin_choose_address)
    LinearLayout linChooseAddress;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.list_view)
    RecyclerView listView;

    MyAdapter adapter;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_find_doctor_by_hospital);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("按医院找医生");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

    }


    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{


        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_doctor_by_hospital, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_pic)
            ShapeImageView imgPic;
            @BindView(R.id.tv_hospital_name)
            TextView tvHospitalName;
            @BindView(R.id.tv_hospital_address)
            TextView tvHospitalAddress;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
