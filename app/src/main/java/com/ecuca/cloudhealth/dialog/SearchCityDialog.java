package com.ecuca.cloudhealth.dialog;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.CityListEntity;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class SearchCityDialog extends PopupWindow {


    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.otv_do_search)
    TextView otvDoSearch;
    @BindView(R.id.list_view)
    RecyclerView listView;
    private List<CityListEntity.DataBean> list;
    MyAdapter adapter;

    CityListEntity entity=null;
    public SearchCityDialog(Activity context) {
        super(context);
        initView(context);

    }

    private void initView(Activity context) {


        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dia_search_city, null);
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
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                doSearch(editable.toString().trim());
            }
        });

        otvDoSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        LinearLayoutManager manager= new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
        list=new ArrayList<>();
        adapter=new MyAdapter(list);
        listView.setAdapter(adapter);
        listView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL_LIST));

    }


    public void showAsDropDown(View anchor,CityListEntity entity) {
        super.showAsDropDown(anchor);
        this.entity=entity;
    }

    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{


        private List<CityListEntity.DataBean> list;

        public MyAdapter(List<CityListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.meituan_item_select_city1, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

            holder.tvCity.setText(list.get(position).getName_full());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tvCity)
            TextView tvCity;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    private void doSearch(String searchText){


        if(entity!=null){
            list.clear();
            for (CityListEntity.DataBean bean:entity.getData())
            {

                if(bean.getName_full().indexOf(searchText)!=-1 && !TextUtils.isEmpty(searchText)){
                    list.add(bean);
                }

            }
            adapter.notifyDataSetChanged();
        }

    }
}
