package com.ecuca.cloudhealth.activity.select;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecuca.cloudhealth.Constant;
import com.ecuca.cloudhealth.Entity.ProvinceCityEntity;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *  选择省市
 * Created by tuhualong on 2018/1/19.
 */

public class SelectProvinceActivity extends BaseActivity {


    @BindView(R.id.list_view_left)
    RecyclerView listViewLeft;
    @BindView(R.id.list_view_right)
    RecyclerView listViewRight;


    int select_index = 0;
    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;
    List<ProvinceCityEntity.DataBean> leftList;
    List<ProvinceCityEntity.DataBean.CityListBean> rightList;
    ProvinceCityEntity entity = null;

    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_select_province);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("选择省市");
    }

    @Override
    protected void initUI() {
        LinearLayoutManager manager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listViewLeft.setLayoutManager(manager1);

        LinearLayoutManager manager2 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listViewRight.setLayoutManager(manager2);

        leftList = new ArrayList<>();
        leftAdapter = new LeftAdapter(leftList, this);
        listViewLeft.setAdapter(leftAdapter);

        rightList = new ArrayList<>();
        rightAdapter = new RightAdapter(rightList);
        listViewRight.setAdapter(rightAdapter);
    }

    @Override
    protected void initData() {
        getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {


                try {
                    InputStreamReader isr = new InputStreamReader(getAssets().open("city_json.json"), "UTF-8");
                    BufferedReader br = new BufferedReader(isr);
                    String line;
                    StringBuilder builder = new StringBuilder();
                    while ((line = br.readLine()) != null) {
                        builder.append(line);
                    }
                    br.close();
                    isr.close();
                    entity = new Gson().fromJson(builder.toString(), ProvinceCityEntity.class);//builder读取了JSON中的数据。

                } catch (Exception e) {
                    finish();
                    e.printStackTrace();
                }

                if (entity == null) {
                    return;
                }

                leftList.clear();
                leftList.addAll(entity.getData());
                leftAdapter.notifyDataSetChanged();

                rightList.clear();
                rightList.addAll(entity.getData().get(0).getCity_list());
                rightAdapter.notifyDataSetChanged();

            }
        }, 10);


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

    }

    class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

        private List<ProvinceCityEntity.DataBean> list;
        private Activity activity;

        public LeftAdapter(List<ProvinceCityEntity.DataBean> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }


        @Override
        public LeftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_left, parent, false);
            return new LeftAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LeftAdapter.ViewHolder holder, final int position) {


            if (select_index == position) {

                holder.tvName.setBackgroundColor(activity.getResources().getColor(R.color.white));

            } else {
                holder.tvName.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
            holder.tvName.setText(list.get(position).getName_full());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select_index = position;
                    notifyDataSetChanged();
                    rightAdapter.upDate(list.get(position).getCity_list());
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_name)
            TextView tvName;


            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }


    }

    class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {

        List<ProvinceCityEntity.DataBean.CityListBean> list;

        public RightAdapter(List<ProvinceCityEntity.DataBean.CityListBean> list) {
            this.list = list;
        }


        public void upDate(List<ProvinceCityEntity.DataBean.CityListBean> list) {

            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }

        @Override
        public RightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_right, parent, false);
            return new RightAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RightAdapter.ViewHolder holder, final int position) {


            holder.tvName.setText(list.get(position).getName_full());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    Intent intent=new Intent();
                    intent.putExtra("province_id",entity.getData().get(select_index).getId());
                    intent.putExtra("city_id",list.get(position).getId());
                    intent.putExtra("text",entity.getData().get(select_index).getName_full()+" "+entity.getData().get(select_index).getCity_list().get(position).getName_full());
                    setResult(Constant.SELECT_CITY_RESULTCODE,intent);
                    finish();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @BindView(R.id.tv_name)
            TextView tvName;


            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

    }
}
