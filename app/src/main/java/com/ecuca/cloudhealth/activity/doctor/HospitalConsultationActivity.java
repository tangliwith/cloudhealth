package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;

import com.ecuca.cloudhealth.Entity.DoctorListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.adapter.HospitalConsultationAdapter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 咨询医生
 * Created by tuhualong on 2017/12/21.
 */

public class HospitalConsultationActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.list_view)
    MyRecycleView listView;


    @BindView(R.id.rel_search)
    RelativeLayout relSearch;


    List<DoctorListEntity.DataBean> list;
    HospitalConsultationAdapter adapter;
    //筛选条件
    int city_id, province_id, hospital_id, section_id;

    int page=1;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_hospital_consultation);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("咨询医生");
        setTitleRightText("医生筛选", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HospitalConsultationActivity.this, DoctorScreenActivity.class);
                HospitalConsultationActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void initUI() {

        listView.setPullRefreshEnabled(true);
        listView.setLoadingMoreEnabled(true);
        listView.setLoadingListener(this);
    }

    @Override
    protected void initData() {


        list = new ArrayList<>();
        adapter = new HospitalConsultationAdapter(this, list);
        listView.setAdapter(adapter);
        getData();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        relSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(HospitalConsultationActivity.this, SearchDoctorActivity.class);
                HospitalConsultationActivity.this.startActivity(intent);
            }
        });

    }

    private void getData() {

        Map<String, String> m = new HashMap<>();

        if (city_id > 0)
            m.put("city_id", String.valueOf(city_id));
        if (province_id > 0)
            m.put("province_id", String.valueOf(province_id));
        if (hospital_id > 0)
            m.put("hospital_id", String.valueOf(hospital_id));
        if (section_id > 0)
            m.put("section_id", String.valueOf(section_id));


//        if (!TextUtils.isEmpty(key_word))
//            m.put("key_word", key_word);

        m.put("page",String.valueOf(page));
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
                if (response != null) {

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
                } else {
                    ToastMessage("数据异常");
                }
            }
        });
    }

    @Override
    public void onRefresh() {

        page=1;
        getData();
    }

    @Override
    public void onLoadMore() {
        page++;
        getData();
    }
}
