package com.ecuca.cloudhealth.activity.select;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Constant;
import com.ecuca.cloudhealth.Entity.HospitalListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.ShapeImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/8.
 */

public class SelectHospitalActivity extends BaseActivity {

    @BindView(R.id.list_view)
    RecyclerView listView;

    int city_id;
    MyAdapter adapter;

    List<HospitalListEntity.DataBean> list;
    @Override
    protected void setContentView() {

        city_id=getIntent().getIntExtra("city_id",0);

        setContentView(R.layout.aty_select_hospital);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("选择医院");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        list=new ArrayList<>();
        adapter=new MyAdapter(list);
        listView.setAdapter(adapter);
        getData();
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
        List<HospitalListEntity.DataBean> list;


        public MyAdapter(List<HospitalListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_doctor_by_hospital, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            final HospitalListEntity.DataBean bean=list.get(position);
            if(bean!=null){
                holder.tvHospitalName.setText(bean.getHospital_name()==null?"":bean.getHospital_name());
                holder.tvHospitalAddress.setText(bean.getAddress()==null?"":bean.getAddress());
                GlideUtils.LoadImg(holder.imgPic,bean.getHospital_img());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra("hospital",bean);
                        setResult(Constant.SELECT_HOSPITAL_RESULTCODE,intent);
                        finish();

                    }
                });

            }
        }

        @Override
        public int getItemCount() {
            return list.size();
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


    private void getData(){


        Map<String,String> m=new HashMap<>();
        m.put("city_id",city_id+"");
        HttpUtils.getInstance().post(m, "main/get_hospital_by_city_id", new AllCallback<HospitalListEntity>(HospitalListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(HospitalListEntity response) {

                if(response!=null){

                    if(response.getCode()==200){

                        list.clear();
                        list.addAll(response.getData());
                        adapter.notifyDataSetChanged();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{

                    ToastMessage("数据异常");



                }
            }
        });
    }

}
