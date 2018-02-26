package com.ecuca.cloudhealth.activity.select;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Constant;
import com.ecuca.cloudhealth.Entity.SectionListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/** 选择科室
 * Created by tuhualong on 2018/1/20.
 */

public class SelectSectionActivity extends BaseActivity{


    @BindView(R.id.list_view_left)
    RecyclerView listViewLeft;
    @BindView(R.id.list_view_right)
    RecyclerView listViewRight;
    private List<SectionListEntity.DataBean> leftList=new ArrayList<>();
    private List<SectionListEntity.DataBean> rightList=new ArrayList<>();
    private int select_index=0;

    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_select_section);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("选择科室");
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

        leftAdapter=new LeftAdapter(leftList);
        listViewLeft.setAdapter(leftAdapter);

        rightAdapter=new RightAdapter(rightList);
        listViewRight.setAdapter(rightAdapter);

        getLeftData();


    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

    }


    class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

        List<SectionListEntity.DataBean> list;

        public LeftAdapter(List<SectionListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public LeftAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_left, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LeftAdapter.ViewHolder holder, final int position) {
            final SectionListEntity.DataBean bean=list.get(position);

            if(select_index==position){

                holder.tvName.setBackgroundColor(getResources().getColor(R.color.white));

            }
            else{
                holder.tvName.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
            holder.tvName.setText(bean.getTitle());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select_index=position;
                    notifyDataSetChanged();

                    getRightData(bean.getId());
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



        List<SectionListEntity.DataBean> list;

        public RightAdapter(List<SectionListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public RightAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_office_right, parent, false);
            return new RightAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(RightAdapter.ViewHolder holder, final int position) {

            final SectionListEntity.DataBean bean=list.get(position);
            holder.tvName.setText(bean.getTitle());


            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent();
                    intent.putExtra("section",bean);

                    setResult(Constant.SELECT_SECTION_RESULTCODE,intent);
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


    /**
     * 获取一级分类
     */
    private void getLeftData(){


        HttpUtils.getInstance().post(null, "main/more_section", new AllCallback<SectionListEntity>(SectionListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(SectionListEntity response) {

                if(response!=null){

                    if(response.getCode()==200){

                        leftList.clear();
                        leftList.addAll(response.getData());
                        leftAdapter.notifyDataSetChanged();
                        if(leftList.size()>0){
                            getRightData(leftList.get(0).getId());
                        }

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


    /**
     * 获取一级分类
     */
    private void getRightData(int id){

        Map<String,String> m=new HashMap<>();
        m.put("id",String.valueOf(id));
        HttpUtils.getInstance().post(m, "main/get_second_section", new AllCallback<SectionListEntity>(SectionListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(SectionListEntity response) {

                if(response!=null){

                    if(response.getCode()==200){

                        rightList.clear();
                        rightList.addAll(response.getData());
                        rightAdapter.notifyDataSetChanged();

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
