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
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class ChooseHealthBugDialog extends PopupWindow {


    ChooseHealthBugDialogListener chooseDoctorTypeDialogListener;

    List<MyEntity> list;
    MyAdapter adapter;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.tv_reset)
    TextView tvReset;
    @BindView(R.id.tv_ok)
    TextView tvOk;

    public ChooseHealthBugDialog(Activity context, ChooseHealthBugDialogListener chooseDoctorTypeDialogListener) {
        super(context);
        this.chooseDoctorTypeDialogListener = chooseDoctorTypeDialogListener;
        initView(context);

    }

    private void initView(Activity context) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dia_choose_health_bug, null);
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3, LinearLayoutManager.VERTICAL, false);
        listView.setLayoutManager(gridLayoutManager);

        list=new ArrayList<>();
        list.add(new MyEntity("头晕", false));
        list.add(new MyEntity("直肠癌", false));
        list.add(new MyEntity("乳腺癌", false));
        list.add(new MyEntity("恶性肿瘤", false));
        list.add(new MyEntity("感冒", false));
        list.add(new MyEntity("发烧", false));
        list.add(new MyEntity("头晕", false));
        list.add(new MyEntity("直肠癌", false));
        list.add(new MyEntity("头晕", false));
        list.add(new MyEntity("乳腺癌", false));
        list.add(new MyEntity("头晕", false));
        list.add(new MyEntity("发烧", false));
        list.add(new MyEntity("头晕", false));


        adapter = new MyAdapter(list,context);
        listView.setAdapter(adapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adapter.notifyDataSetChanged();

            }
        });
        tvOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        private List<MyEntity> list;
        private Activity activity;

        public MyAdapter(List<MyEntity> list, Activity activity) {
            this.list = list;
            this.activity = activity;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_choose_health_bug, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            holder.tvText.setText(list.get(position).getName());
            if (list.get(position).is_check) {

                holder.tvText.setTextColor(activity.getResources().getColor(R.color.white));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);

            } else {
                holder.tvText.setTextColor(activity.getResources().getColor(R.color.app_title_top));
                holder.tvText.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(list.get(position).is_check){
                        list.get(position).setIs_check(false);
                    }
                    else{
                        list.get(position).setIs_check(true);
                    }
                    notifyDataSetChanged();
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
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

    public interface ChooseHealthBugDialogListener {

        void onShow();

        void onDismiss();
    }

    class MyEntity {

        private String name;
        private boolean is_check;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean is_check() {
            return is_check;
        }

        public void setIs_check(boolean is_check) {
            this.is_check = is_check;
        }

        public MyEntity(String name, boolean is_check) {

            this.name = name;
            this.is_check = is_check;
        }
    }
}
