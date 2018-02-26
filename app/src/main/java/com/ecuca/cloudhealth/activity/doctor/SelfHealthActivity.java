package com.ecuca.cloudhealth.activity.doctor;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.MyRecycleVIew.RecycleViewDivider;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.dialog.ChooseHealthBugDialog;
import com.ecuca.cloudhealth.fragment.TopicFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2018/1/9.
 */

public class SelfHealthActivity extends BaseActivity {
    @BindView(R.id.tv_bug_list)
    TextView tvBugList;
    @BindView(R.id.lin_choose_bug)
    LinearLayout linChooseBug;
    @BindView(R.id.list_view)
    MyRecycleView listView;

    ChooseHealthBugDialog chooseHealthBugDialog;
    MyAdapter adapter;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_self_health);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("健康自查");
    }

    @Override
    protected void initUI() {
        chooseHealthBugDialog =new ChooseHealthBugDialog(this, new ChooseHealthBugDialog.ChooseHealthBugDialogListener() {
            @Override
            public void onShow() {

            }

            @Override
            public void onDismiss() {

            }
        });

        listView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.view_line)));
        listView.setLoadingMoreEnabled(false);
        listView.setPullRefreshEnabled(false);
    }

    @Override
    protected void initData() {
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        linChooseBug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseHealthBugDialog.showAsDropDown(linChooseBug);
            }
        });
    }




    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
            return new MyAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.img_avatar)
            ImageView imgAvatar;
            @BindView(R.id.tv_user_name)
            TextView tvUserName;
            @BindView(R.id.tv_date)
            TextView tvDate;
            @BindView(R.id.tv_title)
            TextView tvTitle;
            @BindView(R.id.tv_content)
            TextView tvContent;
            @BindView(R.id.img_big)
            ImageView imgBig;
            @BindView(R.id.list_view)
            RecyclerView listView;
            @BindView(R.id.tv_look_num)
            TextView tvLookNum;
            @BindView(R.id.tv_comment_num)
            TextView tvCommentNum;
            @BindView(R.id.tv_collection_num)
            TextView tvCollectionNum;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

}
