package com.ecuca.cloudhealth.activity.me;

import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.FollowDoctorEntity;
import com.ecuca.cloudhealth.Entity.FollowDoctorListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/24.
 */

public class FollowDoctorListActivity extends BaseActivity implements XRecyclerView.LoadingListener {
    @BindView(R.id.list_view)
    MyRecycleView listView;
    List<FollowDoctorListEntity.DataBean> list;
    MyAdapter adapter;
    int page = 1;

    @Override
    protected void setContentView() {

        setContentView(R.layout.aty_doctor_follow_list);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("我的关注");
    }

    @Override
    protected void initUI() {

        listView.setLoadingListener(this);
        listView.setPullRefreshEnabled(true);
        listView.setLoadingMoreEnabled(true);
    }

    @Override
    protected void initData() {

        list = new ArrayList<>();
        adapter = new MyAdapter(list);
        listView.setAdapter(adapter);
        getData();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

    }

    @Override
    public void onRefresh() {

        page = 1;
        getData();

    }

    @Override
    public void onLoadMore() {

        page++;
        getData();
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {


        List<FollowDoctorListEntity.DataBean> list;

        public MyAdapter(List<FollowDoctorListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_follow_list, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            final FollowDoctorListEntity.DataBean bean = list.get(position);

            GlideUtils.LoadImg(holder.imgHead, bean.getAvatar_url());
            holder.tvName.setText(bean.getNick_name() == null ? "" : bean.getNick_name());
            holder.tvLevel.setText(bean.getCate_type() == null ? "" : bean.getCate_type());
            String content = "";
            if (bean.getHospital_name() != null) {
                content = bean.getHospital_name();

            }
            if (bean.getSection_info() != null) {
                content = content + " " + bean.getSection_info();
            }
            holder.tvDoctorContent.setText(content);
            holder.tvBeGoodsAt.setText(bean.getProfession());

            holder.tvCloseFollow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showAlertDialogMessage("提示", "是否要取消关注该医生?", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            closeFollow(bean.getUid());
                        }
                    });

                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img_head)
            CircleImageView imgHead;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_level)
            TextView tvLevel;
            @BindView(R.id.tv_doctor_content)
            TextView tvDoctorContent;
            @BindView(R.id.tv_be_good_at)
            TextView tvBeGoodsAt;
            @BindView(R.id.tv_close_follow)
            TextView tvCloseFollow;
            @BindView(R.id.tv_do_call)
            TextView tvDoCall;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


    private void getData() {


        Map<String, String> m = new HashMap<>();
        m.put("page", String.valueOf(page));
        HttpUtils.getInstance().post(m, "user/my_follower", new AllCallback<FollowDoctorListEntity>(FollowDoctorListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                listView.loadMoreComplete();
                listView.refreshComplete();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(FollowDoctorListEntity response) {
                listView.loadMoreComplete();
                listView.refreshComplete();
                if (response != null) {
                    if (response.getCode() == 200) {
                        if (response.getData() != null) {
                            if (page == 1) {
                                list.clear();
                            }
                            list.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastMessage("暂无数据");
                        }

                    } else {
                        ToastMessage("暂无数据");
                    }

                } else {
                    ToastMessage("数据异常");
                }
            }
        });
    }


    /**
     * 取消关注
     *
     * @param uid
     */
    private void closeFollow(int uid) {


        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", String.valueOf(uid));

        HttpUtils.getInstance().post(m, "doctor/cancle_following_doctor", new AllCallback<FollowDoctorEntity>(FollowDoctorEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(FollowDoctorEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        getData();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("数据异常");
                }
            }
        });
    }

}
