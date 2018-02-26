package com.ecuca.cloudhealth.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.doctor.DoctorScreenActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class ChooseDoctorTypeDialog extends PopupWindow {


    @BindView(R.id.list_view_service_type)
    RecyclerView listViewServiceType;
    @BindView(R.id.list_view_doctor_level)
    RecyclerView listViewDoctorLevel;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;


    ServiceTypeAdapter serviceTypeAdapter;
    List<String> serviceTypeList=new ArrayList<>();
    int selectType;

    LevelAdapter levelAdapter;
    List<String> levelList=new ArrayList<>();

    ChooseDoctorTypeDialogListener chooseDoctorTypeDialogListener;
    public ChooseDoctorTypeDialog(Activity context,ChooseDoctorTypeDialogListener chooseDoctorTypeDialogListener) {
        super(context);
        this.chooseDoctorTypeDialogListener=chooseDoctorTypeDialogListener;
        initView(context);

    }

    private void initView(Activity context) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dia_choose_doctor_type, null);
        ButterKnife.bind(this, contentView);
        int h = context.getWindowManager().getDefaultDisplay().getHeight();
        int w = context.getWindowManager().getDefaultDisplay().getWidth();
        // 设置SelectPicPopupWindow的View
        this.setContentView(contentView);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(w);
        this.setHeight(h);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false);
        listViewServiceType.setLayoutManager(gridLayoutManager);


        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(context, 4, LinearLayoutManager.VERTICAL, false);
        listViewDoctorLevel.setLayoutManager(gridLayoutManager2);

        serviceTypeList.add("不限");
        serviceTypeList.add("图文");
        serviceTypeList.add("语音");
        serviceTypeList.add("视频");
        serviceTypeAdapter=new ServiceTypeAdapter(serviceTypeList,context);
        listViewServiceType.setAdapter(serviceTypeAdapter);



        levelList.add("不限");
        levelList.add("普通");
        levelList.add("专家");
        levelList.add("教授");
        levelList.add("知名教授");
        levelAdapter=new LevelAdapter(levelList,context);
        listViewDoctorLevel.setAdapter(levelAdapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectType=0;
                serviceTypeAdapter.notifyDataSetChanged();
                levelAdapter.notifyDataSetChanged();
            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }
    /**
     * 咨询服务
     */
    class ServiceTypeAdapter extends RecyclerView.Adapter<ServiceTypeAdapter.ViewHolder> {


        private List<String> list;
        private  Activity activity;


        public ServiceTypeAdapter(List<String> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }



        @Override
        public ServiceTypeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type, parent, false);
            return new ServiceTypeAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ServiceTypeAdapter.ViewHolder holder, final int position) {
            holder.tvText.setText(list.get(position));
            if(selectType==position){

                holder.tvText.setTextColor(activity.getResources().getColor(R.color.white));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);

            }
            else{
                holder.tvText.setTextColor(activity.getResources().getColor(R.color.app_title_top));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectType=position;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


    /**
     * 咨询服务
     */
    class LevelAdapter extends RecyclerView.Adapter<LevelAdapter.ViewHolder> {


        private List<String> list;
        private Activity activity;

        public LevelAdapter(List<String> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }



        @Override
        public LevelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_type, parent, false);
            return new LevelAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(LevelAdapter.ViewHolder holder, final int position) {
            holder.tvText.setText(list.get(position));
            if(selectType==position){

                holder.tvText.setTextColor(activity.getResources().getColor(R.color.white));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);

            }
            else{
                holder.tvText.setTextColor(activity.getResources().getColor(R.color.app_title_top));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectType=position;
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_text)
            TextView tvText;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }



    public void showAsDropDown(View anchor) {
        super.showAsDropDown(anchor);
        chooseDoctorTypeDialogListener.onShow();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        chooseDoctorTypeDialogListener.onDismiss();
    }

    public interface ChooseDoctorTypeDialogListener{

        void onShow();
        void onDismiss();
    }
}
