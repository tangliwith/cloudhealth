package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class FindDoctorByOfficeTwoActivity extends BaseActivity {


    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.lin_choose_address)
    LinearLayout linChooseAddress;
    @BindView(R.id.et_search)
    TextView etSearch;
    @BindView(R.id.list_view_left)
    RecyclerView listViewLeft;
    @BindView(R.id.list_view_right)
    RecyclerView listViewRight;
    private List<Entity> list=new ArrayList<>();
    private int select_index=0;

    LeftAdapter leftAdapter;

    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_find_doctor_by_office_two);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("按科室找医生");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listViewLeft.setLayoutManager(manager);


        LinearLayoutManager manager1=new LinearLayoutManager(this);
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        listViewRight.setLayoutManager(manager1);

    }

    @Override
    protected void initData() {
        list.add(new Entity(R.mipmap.icon_office_1,"妇科"));
        list.add(new Entity(R.mipmap.icon_office_2,"小儿内科"));
        list.add(new Entity(R.mipmap.icon_office_3,"产科"));
        list.add(new Entity(R.mipmap.icon_office_4,"消化内科"));
        list.add(new Entity(R.mipmap.icon_office_3,"妇产科"));
        list.add(new Entity(R.mipmap.icon_office_6,"皮肤科"));
        list.add(new Entity(R.mipmap.icon_office_7,"口腔科"));
        list.add(new Entity(R.mipmap.icon_office_8,"耳鼻喉科"));
        list.add(new Entity(R.mipmap.icon_office_9,"生殖中心"));
        list.add(new Entity(R.mipmap.icon_office_10,"不孕不育"));
        list.add(new Entity(R.mipmap.icon_office_11,"内分泌科"));
        list.add(new Entity(R.mipmap.icon_office_12,"肛肠外科"));
        list.add(new Entity(R.mipmap.icon_office_13,"眼科"));
        list.add(new Entity(R.mipmap.icon_office_14,"中医科"));
        list.add(new Entity(R.mipmap.icon_office_15,"骨科"));
        list.add(new Entity(R.mipmap.icon_office_16,"呼吸内科"));
        list.add(new Entity(R.mipmap.icon_office_3,"妇产科"));
        list.add(new Entity(R.mipmap.icon_office_3,"皮肤科"));
        leftAdapter=new LeftAdapter(list);
        listViewLeft.setAdapter(leftAdapter);
        listViewRight.setAdapter(new RightAdapter("妇科"));
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorByOfficeTwoActivity.this,SearchDoctorActivity.class);
                FindDoctorByOfficeTwoActivity.this.startActivity(intent);
            }
        });
    }


    class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

        private List<Entity> list;

        public LeftAdapter(List<Entity> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_left, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {


            if(select_index==position){

                holder.tvName.setBackgroundColor(getResources().getColor(R.color.white));

            }
            else{
                holder.tvName.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
            holder.tvName.setText(list.get(position).getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select_index=position;
                    notifyDataSetChanged();
                    listViewRight.setAdapter(new RightAdapter(list.get(position).getName()));
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

        private String title;

        public RightAdapter(String title) {
            this.title = title;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_right, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {



            holder.tvName.setText(title+position);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(FindDoctorByOfficeTwoActivity.this,DoctorListActivity.class);
                    FindDoctorByOfficeTwoActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 15;
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

    class Entity {

        private int pic;
        private String name;

        public Entity(int pic, String name) {
            this.pic = pic;
            this.name = name;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
