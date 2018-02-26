package com.ecuca.cloudhealth.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.MyRecycleVIew.RecycleViewDivider;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.View.DragFloatActionButton;
import com.ecuca.cloudhealth.activity.topic.PublishTopicActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 话题界面
 * Created by tuhualong on 2017/12/18.
 */

public class TopicFragment extends BaseFragment {


    MyRecycleView listView;

    MyAdapter adapter;

    DragFloatActionButton moveButton;
    @Override
    protected void setContentView() {

        setContentView(R.layout.frg_topic);
        setTitleText("话题");
    }

    @Override
    protected void initUI() {

        moveButton=getID(R.id.btn_move);
        listView = getID(R.id.list_view);

        listView.addItemDecoration(new RecycleViewDivider(getContext(), LinearLayoutManager.VERTICAL, 15, getResources().getColor(R.color.view_line)));
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
        moveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), PublishTopicActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_topic, parent, false);
            return new ViewHolder(v);
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
