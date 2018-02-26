package com.ecuca.cloudhealth.activity.doctor;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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
import com.ecuca.cloudhealth.View.ShapeImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/** 科室找医生
 * Created by tuhualong on 2018/1/8.
 */

public class FindDoctorByOfficeActivity extends BaseActivity {
    @BindView(R.id.lin_choose_address)
    LinearLayout linChooseAddress;
    @BindView(R.id.et_search)
    TextView etSearch;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.tv_more)
    TextView tvMore;
    MyAdapter adapter;


    private List<Entity> list=new ArrayList<>();
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_find_doctor_by_office);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("按科室找医生");
    }

    @Override
    protected void initUI() {

        GridLayoutManager manager=new GridLayoutManager(this,4,LinearLayoutManager.VERTICAL,false);

        listView.setLayoutManager(manager);
        adapter=new MyAdapter(list);
        listView.setAdapter(adapter);
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
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        tvMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorByOfficeActivity.this,FindDoctorByOfficeTwoActivity.class);
                FindDoctorByOfficeActivity.this.startActivity(intent);
            }
        });
        etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FindDoctorByOfficeActivity.this,SearchDoctorActivity.class);
                FindDoctorByOfficeActivity.this.startActivity(intent);
            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{

        private List<Entity> list;

        public MyAdapter(List<Entity> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_find_doctor_by_office, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            holder.tvName.setText(list.get(position).getName());
            holder.imgPic.setImageResource(list.get(position).getPic());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(FindDoctorByOfficeActivity.this,DoctorListActivity.class);
                    FindDoctorByOfficeActivity.this.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_pic)
            ImageView imgPic;
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
