package com.ecuca.cloudhealth.activity.doctor;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 科室找医生
 * Created by tuhualong on 2018/1/8.
 */

public class FindDoctorByDiseaseActivity extends BaseActivity {


    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lin_choose_address)
    LinearLayout linChooseAddress;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.list_view_left)
    RecyclerView listViewLeft;
    @BindView(R.id.list_view_right)
    RecyclerView listViewRight;
    private int select_index;

    private List<String> list;

    MyAdapter adapter;
    RightAdapter rightAdapter;
    @Override
    protected void setContentView() {
        setContentView(R.layout.aty_find_doctor_by_disease);

        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("按疾病找医生");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listViewLeft.setLayoutManager(manager);
        list = new ArrayList<>();
        list.add("妇产科");
        list.add("儿科");
        list.add("内科");
        list.add("外科");
        list.add("中医科");
        list.add("常见病");
        list.add("特色专病");
        list.add("中西医结合");
        adapter = new MyAdapter(list);
        listViewLeft.setAdapter(adapter);


        LinearLayoutManager manager1 = new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        listViewRight.setLayoutManager(manager1);
        rightAdapter=new RightAdapter();
        listViewRight.setAdapter(rightAdapter);
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


    private int parent_select_index = -1;

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<String> list;

        public MyAdapter(List<String> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_disease_left, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            if (position == parent_select_index) {
                holder.listView.setVisibility(View.VISIBLE);
                holder.imgChooseStatus.setImageResource(R.mipmap.icon_disease_left_ed);
                holder.listView.setAdapter(new BottomAdapter(list.get(position)));
            } else {
                holder.listView.setVisibility(View.GONE);
                holder.imgChooseStatus.setImageResource(R.mipmap.icon_disease_left_un);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    select_index = 0;
                    if (parent_select_index == position) {
                        parent_select_index = -1;
                    } else {
                        parent_select_index = position;

                    }

                    notifyDataSetChanged();
                }
            });
            holder.tvName.setText(list.get(position));


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.lin_top)
            LinearLayout linTop;
            @BindView(R.id.img_choose_status)
            ImageView imgChooseStatus;
            @BindView(R.id.list_view)
            RecyclerView listView;

            BottomAdapter bottomAdapter;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
                listView.setLayoutManager(new LinearLayoutManager(FindDoctorByDiseaseActivity.this));
                bottomAdapter = new BottomAdapter("");
                listView.setAdapter(bottomAdapter);

            }


        }




        class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder> {

            private String str;

            public BottomAdapter(String str) {
                this.str = str;
            }

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_left, parent, false);
                return new ViewHolder(v);
            }

            @Override
            public void onBindViewHolder(final ViewHolder holder, final int position) {


                if (select_index == position) {

                    holder.tvName.setBackgroundColor(getResources().getColor(R.color.white));

                } else {
                    holder.tvName.setBackgroundColor(Color.parseColor("#EEEEEE"));
                }
                rightAdapter.updateStr(str + (position + 1)+"_");
                holder.tvName.setText(str + (position + 1));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        select_index = position;
                        notifyDataSetChanged();


                    }
                });
            }

            @Override
            public int getItemCount() {
                return 5;
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

    class RightAdapter extends RecyclerView.Adapter<RightAdapter.ViewHolder> {




        private String str;
        @Override
        public RightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_right, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RightAdapter.ViewHolder holder, int position) {

            holder.tvName.setText(str+(position+1));


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(FindDoctorByDiseaseActivity.this,DoctorListActivity.class);
                    FindDoctorByDiseaseActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return TextUtils.isEmpty(str)?0:10;
        }


        public  void updateStr(String str){

            this.str=str;
            notifyDataSetChanged();
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_name)
            TextView tvName;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }



}
