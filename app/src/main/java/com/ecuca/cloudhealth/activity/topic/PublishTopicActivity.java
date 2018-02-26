package com.ecuca.cloudhealth.activity.topic;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.MyRecycleVIew.RecycleViewDivider;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/31.
 */

public class PublishTopicActivity extends BaseActivity {


    @BindView(R.id.list_view)
    MyRecycleView listView;

    MyAdapter adapter;
    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_publish_topic);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("发布话题");
    }

    @Override
    protected void initUI() {

        listView.setPullRefreshEnabled(false);
        listView.setLoadingMoreEnabled(false);

        View headView = LayoutInflater.from(this).inflate(R.layout.view_publish_topic_head, null);

        listView.addHeaderView(headView);
        listView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 4, getResources().getColor(R.color.view_line)) );


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

    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_publish_topic, null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 2;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.lin_add_img)
            LinearLayout linAddImg;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }


    }

}
