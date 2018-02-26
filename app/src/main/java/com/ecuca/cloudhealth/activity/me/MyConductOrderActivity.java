package com.ecuca.cloudhealth.activity.me;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.MyConductOrderEntity;
import com.ecuca.cloudhealth.Entity.OrderListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
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
 * Created by tuhualong on 2018/1/26.
 */

public class MyConductOrderActivity extends BaseActivity  implements XRecyclerView.LoadingListener{
    @BindView(R.id.rb_1)
    RadioButton rb1;
    @BindView(R.id.rb_2)
    RadioButton rb2;
    @BindView(R.id.rb_3)
    RadioButton rb3;
    @BindView(R.id.rb_4)
    RadioButton rb4;
    @BindView(R.id.rb_5)
    RadioButton rb5;
    @BindView(R.id.rg_group)
    RadioGroup rgGroup;
    @BindView(R.id.list_view)
    MyRecycleView listView;


    MyAdapter adapter;
    List<MyConductOrderEntity.DataBean> list;
    int status = 1;
    int page = 1;
    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_my_conduct_order);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("我的陪诊");
    }

    @Override
    protected void initUI() {
        rb1.setChecked(true);
        listView.setLoadingListener(this);
        listView.setLoadingMoreEnabled(true);
        listView.setPullRefreshEnabled(true);
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

        rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {

                if (i == rb1.getId()) {
                    page = 1;
                    status = 1;
                    getData();
                } else if (i == rb2.getId()) {
                    page = 1;
                    status = 2;
                    getData();
                } else if (i == rb3.getId()) {
                    page = 1;
                    status = 3;
                    getData();
                } else if (i == rb4.getId()) {
                    page = 1;
                    status = 4;
                    getData();
                } else if (i == rb5.getId()) {
                    page = 1;
                    status = 5;
                    getData();
                }
            }
        });
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

        List<MyConductOrderEntity.DataBean> list;

        public MyAdapter(List<MyConductOrderEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_conduct_order, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            final MyConductOrderEntity.DataBean bean = list.get(position);
            holder.tvWithTime.setText("陪诊时间: "+(bean.getWith_times() == null ? "" : bean.getWith_times()));
            holder.tvHospitalName.setText(bean.getHospital_name() == null ? "" : bean.getHospital_name());
//            1 协助就医服务  2  全程陪诊服务
            if (bean.getDiagnosis_type() == 1) {
                holder.tvGoodsName.setText("协助就医服务");
            } else if (bean.getDiagnosis_type() == 2) {
                holder.tvGoodsName.setText("全程陪诊服务");
            }
            else{
                holder.tvGoodsName.setText("");
            }
            holder.tvGoodsPrice.setText("¥ "+(bean.getTotal_money()==null?"":bean.getTotal_money()));


            //已放弃
            if (bean.getStatus() == -1 || bean.getStatus() == 5) {
                holder.tvOrderStatus.setText("已放弃");
                holder.tvBottomLeft.setText("删除陪诊");
                holder.tvBottomRight.setText("");
                holder.tvBottomRight.setVisibility(View.GONE);
                holder.viewLine.setVisibility(View.GONE);

                holder.tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("left", 5);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
                holder.tvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("right", 5);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
            }
            //待支付
            else if (bean.getStatus() == 9) {
                holder.tvOrderStatus.setText("待支付");
                holder.tvBottomLeft.setText("放弃申请");
                holder.tvBottomRight.setText("立即付款");
                holder.tvBottomRight.setTextColor(getResources().getColor(R.color.app_title_top));
                holder.tvBottomRight.setVisibility(View.VISIBLE);
                holder.viewLine.setVisibility(View.VISIBLE);

                holder.tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("left", 2);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
                holder.tvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("right", 2);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });

            }
//               未开始
            else if (bean.getStatus() == 1 || bean.getStatus() == 4 || bean.getStatus() == 6) {

                holder.tvOrderStatus.setText("未开始");
                if (bean.getStatus() == 4) {
                    holder.tvBottomLeft.setText("退款审核中");
                } else {
                    holder.tvBottomLeft.setText("放弃申请");
                }


                holder.tvBottomRight.setText("确定完成");
                holder.tvBottomRight.setTextColor(getResources().getColor(R.color.text_gray333));
                holder.tvBottomRight.setVisibility(View.VISIBLE);
                holder.viewLine.setVisibility(View.VISIBLE);

                holder.tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (bean.getStatus() == 4) {

                            ToastMessage("正在审核退款中，请勿操作");
                            return;
                        }
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("left", 3);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
                holder.tvBottomRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("right", 3);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
            }
            //已完成
            else if (bean.getStatus() == 2 || bean.getStatus() == 3) {
                holder.tvOrderStatus.setText("已完成");
                holder.tvBottomLeft.setText("删除陪诊");
                holder.tvBottomRight.setVisibility(View.GONE);
                holder.viewLine.setVisibility(View.GONE);


                holder.tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        intent.putExtra("left", 4);
                        MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                    }
                });
            } else {
                holder.tvOrderStatus.setVisibility(View.GONE);
                holder.tvBottomLeft.setVisibility(View.GONE);
                holder.tvBottomRight.setVisibility(View.GONE);
                holder.viewLine.setVisibility(View.GONE);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MyConductOrderActivity.this, ConductOrderInfoActivity.class);
                    intent.putExtra("order_id", bean.getId());
                    MyConductOrderActivity.this.startActivityForResult(intent, 1000);
                }
            });

        }


        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_with_time)
            TextView tvWithTime;
            @BindView(R.id.tv_order_status)
            TextView tvOrderStatus;
            @BindView(R.id.tv_hospital_name)
            TextView tvHospitalName;
            @BindView(R.id.tv_goods_name)
            TextView tvGoodsName;
            @BindView(R.id.tv_goods_price)
            TextView tvGoodsPrice;
            @BindView(R.id.tv_bottom_left)
            TextView tvBottomLeft;
            @BindView(R.id.view_line)
            View viewLine;
            @BindView(R.id.tv_bottom_right)
            TextView tvBottomRight;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 获取数据
     */
    private void getData() {


        Map<String, String> m = new HashMap<>();
        m.put("page", String.valueOf(page));
        m.put("status", String.valueOf(status));
        HttpUtils.getInstance().post(m, "order/with_diagnosis", new AllCallback<MyConductOrderEntity>(MyConductOrderEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                listView.refreshComplete();
                listView.loadMoreComplete();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(MyConductOrderEntity response) {
                listView.refreshComplete();
                listView.loadMoreComplete();
                if (response != null) {

                    if (response.getCode() == 200) {

                        if (response.getData() != null) {

                            if (page == 1)
                                list.clear();
                            list.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            if (page == 1)
                                list.clear();
                            adapter.notifyDataSetChanged();
                            ToastMessage("暂无数据");
                        }
                    } else {
                        if (page == 1)
                            list.clear();
                        adapter.notifyDataSetChanged();
                        ToastMessage(response.getMsg());
                    }
                } else {
                    if (page == 1)
                        list.clear();
                    adapter.notifyDataSetChanged();
                    ToastMessage("数据异常");
                }
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getData();
    }
}
