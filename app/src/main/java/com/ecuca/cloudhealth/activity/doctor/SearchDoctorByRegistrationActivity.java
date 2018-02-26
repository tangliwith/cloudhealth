package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.DoctorListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 代理挂号 搜索医生界面
 * Created by tuhualong on 2017/12/24.
 */

public class SearchDoctorByRegistrationActivity extends BaseActivity implements XRecyclerView.LoadingListener{


    @BindView(R.id.et_search)
    EditText etSearch;

    @BindView(R.id.list_view)
    MyRecycleView listView;
    List<DoctorListEntity.DataBean> list;
    MyAdapter adapter;



    int city_id,province_id,hospital_id,section_id;

    int page=1;
    @Override
    protected void setContentView() {
        city_id=  getIntent().getIntExtra("city_id",0);
        province_id= getIntent().getIntExtra("province_id",0);
        hospital_id=getIntent().getIntExtra("hospital_id",0);
        section_id=getIntent().getIntExtra("section_id",0);
        setContentView(R.layout.aty_search_doctor_for_registartion);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("搜索");
    }

    @Override
    protected void initUI() {


        listView.setPullRefreshEnabled(true);
        listView.setLoadingMoreEnabled(true);
        listView.setLoadingListener(this);


    }

    @Override
    protected void initData() {

        list=new ArrayList<>();
        adapter = new MyAdapter(list);
        listView.setAdapter(adapter);
        getData("");
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.e("Test",editable.toString().intern());
                getData(editable.toString().intern());

            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{


        List<DoctorListEntity.DataBean> list;

        public MyAdapter(List<DoctorListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_for_registrator, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {


            final DoctorListEntity.DataBean bean=list.get(position);
            if(bean!=null){
                holder.tvName.setText(bean.getNick_name());
                if(bean.getCate()!=null){
                    holder.tvLevel.setText(bean.getCate().getTitle());
                }
                String content="";
                if(bean.getHospital()!=null){
                    content=bean.getHospital().getHospital_name();

                }

                if(bean.getSection()!=null){
                    content=content+" "+bean.getSection().getTitle();
                }
                holder.tvContent.setText(content);

                holder.tvInfo.setText("医生简介："+(bean.getPersonal_profile()==null?"":bean.getPersonal_profile()));
                GlideUtils.LoadImg(holder.imgHead,bean.getAvatar_url());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(SearchDoctorByRegistrationActivity.this,DoctorRegistrationInfoActivity.class);
                        intent.putExtra("doctor_id",bean.getUid());
                        SearchDoctorByRegistrationActivity.this.startActivity(intent);
                    }
                });
            }




        }

        @Override
        public int getItemCount() {
            return list.size();
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
            @BindView(R.id.tv_info)
            TextView tvInfo;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    private void getData(String key_word){

        Map<String,String> m=new HashMap<>();

        if(city_id>0)
        m.put("city_id",String.valueOf(city_id));
        if(province_id>0)
        m.put("province_id",String.valueOf(province_id));
        if(hospital_id>0)
        m.put("hospital_id",String.valueOf(hospital_id));
        if(section_id>0)
        m.put("section_id",String.valueOf(section_id));
        if(!TextUtils.isEmpty(key_word))
        m.put("key_word",key_word);
        HttpUtils.getInstance().post(m, "doctor/search_doctor", new AllCallback<DoctorListEntity>(DoctorListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                listView.loadMoreComplete();
                listView.refreshComplete();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(DoctorListEntity response) {
                listView.loadMoreComplete();
                listView.refreshComplete();
                if(response!=null){

                    if (response.getCode() == 200) {
                        if(page==1)
                            list.clear();
                        list.addAll(response.getData());
                        adapter.notifyDataSetChanged();
                    } else {

                        if(page==1)
                            list.clear();
                        adapter.notifyDataSetChanged();
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("数据异常");
                }
            }
        });
    }

    @Override
    public void onRefresh() {

        page=1;
        getData(etSearch.getText().toString().trim());
    }

    @Override
    public void onLoadMore() {
        page++;
        getData(etSearch.getText().toString().trim());
    }
}
