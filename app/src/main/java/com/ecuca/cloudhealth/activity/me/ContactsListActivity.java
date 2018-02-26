package com.ecuca.cloudhealth.activity.me;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.ContactListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.doctor.AddImageTextCallUserActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/22.
 */

public class ContactsListActivity extends BaseActivity {
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.tv_add)
    TextView tvAdd;



    List<ContactListEntity.DataBean> list;
    MyAdapter adapter;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_contact_list);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("常用就诊人");
    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
        list=new ArrayList<>();
        adapter=new MyAdapter(list);
        listView.setAdapter(adapter);
        getData();

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ContactsListActivity.this, AddImageTextCallUserActivity.class);
                ContactsListActivity.this.startActivityForResult(intent,1000);
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{


        List<ContactListEntity.DataBean> list;

        public MyAdapter(List<ContactListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            final ContactListEntity.DataBean bean=list.get(position);
            holder.tvName.setText(bean.getNick_name());
            holder.tvMobile.setText(bean.getUser_name());
            GlideUtils.LoadImg(holder.imgHead,bean.getAvatar_url());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(ContactsListActivity.this,ContactInfoActivity.class);
                    intent.putExtra("uid",bean.getUid());
                    ContactsListActivity.this.startActivityForResult(intent,1000);
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_head)
            CircleImageView imgHead;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_mobile)
            TextView tvMobile;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 获取数据
     */
 private void getData(){


     HttpUtils.getInstance().post(null, "user/general_contact", new AllCallback<ContactListEntity>(ContactListEntity.class) {
         @Override
         public void onError(Call call, Exception e) {

             ToastMessage("网络异常");
         }

         @Override
         public void onResponse(ContactListEntity response) {

             if(response!=null){

                 if(response.getCode()==200){

                     if(response.getData()!=null){

                         list.clear();
                         list.addAll(response.getData());
                         adapter.notifyDataSetChanged();
                     }
                     else{
                         ToastMessage("暂无就诊人");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==5002){
            getData();
        }
    }
}
