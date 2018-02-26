package com.ecuca.cloudhealth.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.CityListEntity;
import com.ecuca.cloudhealth.Entity.MeiTuanBean;
import com.ecuca.cloudhealth.Entity.ProvinceCityEntity;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.doctor.FindDoctorByOfficeTwoActivity;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class ChooseProvinceDialog extends PopupWindow {


    @BindView(R.id.list_view_left)
    RecyclerView listViewLeft;
    @BindView(R.id.list_view_right)
    RecyclerView listViewRight;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    int select_index=0;
    LeftAdapter leftAdapter;
    RightAdapter rightAdapter;
    List<ProvinceCityEntity.DataBean> leftList;
    List<ProvinceCityEntity.DataBean.CityListBean> rightList;
    ProvinceCityEntity  entity=null;
    ChooseProvinceDialogListener chooseProvinceDialogListener;
    public ChooseProvinceDialog(Activity context,ChooseProvinceDialogListener chooseProvinceDialogListener) {
        super(context);
        this.chooseProvinceDialogListener=chooseProvinceDialogListener;
        initView(context);
        initView(context);
    }

    private void initView(final Activity context) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dia_choose_province, null);
        ButterKnife.bind(this, contentView);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        this.setHeight(h);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(h);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);
        // mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        // 设置SelectPicPopupWindow弹出窗体动画效果
        //this.setAnimationStyle(R.style.AlertChooserAnimation);

        LinearLayoutManager manager1=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        listViewLeft.setLayoutManager(manager1);

        LinearLayoutManager manager2=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false);
        listViewRight.setLayoutManager(manager2);

        leftList=new ArrayList<>();
        leftAdapter=new LeftAdapter(leftList,context);
        listViewLeft.setAdapter(leftAdapter);

        rightList=new ArrayList<>();
        rightAdapter=new RightAdapter(rightList);
        listViewRight.setAdapter(rightAdapter);

        context.getWindow().getDecorView().postDelayed(new Runnable() {
            @Override
            public void run() {


                try {
                    InputStreamReader isr = new InputStreamReader(context.getAssets().open("city_json.json"), "UTF-8");
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
                    dismiss();
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

    class LeftAdapter extends RecyclerView.Adapter<LeftAdapter.ViewHolder> {

        private List<ProvinceCityEntity.DataBean> list;
        private  Activity activity;

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


            if(select_index==position){

                holder.tvName.setBackgroundColor(activity.getResources().getColor(R.color.white));

            }
            else{
                holder.tvName.setBackgroundColor(Color.parseColor("#EEEEEE"));
            }
            holder.tvName.setText(list.get(position).getName_full());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select_index=position;
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


        public void upDate(List<ProvinceCityEntity.DataBean.CityListBean> list){

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


                    dismiss(list.get(position).getName_full() );
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

    @Override
    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        chooseProvinceDialogListener.onShow();
    }


    public void dismiss(String address) {
        super.dismiss();
        chooseProvinceDialogListener.onDismiss(address);
    }

    public interface ChooseProvinceDialogListener{

        void onShow();
        void onDismiss(String address);
    }

}
