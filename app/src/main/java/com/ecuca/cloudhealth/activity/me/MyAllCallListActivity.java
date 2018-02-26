package com.ecuca.cloudhealth.activity.me;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.OrderListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.MyRecycleVIew.MyRecycleView;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.AllCallTitleButton;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.doctor.CreateOrderActivity;
import com.ecuca.cloudhealth.activity.doctor.PaySuccessActivity;
import com.ecuca.cloudhealth.dialog.ChoosePayTypeDialog;
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

public class MyAllCallListActivity extends BaseActivity implements XRecyclerView.LoadingListener {

    @BindView(R.id.btn_title)
    AllCallTitleButton allCallTitleButton;
    @BindView(R.id.lin_title_left)
    LinearLayout linTitleLeft;
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
    List<OrderListEntity.DataBean> list;
    int viewType = 1;
    int status = 1;
    int page = 1;


    @Override
    protected void setContentView() {


        setContentView(R.layout.aty_my_all_call_list);
        ButterKnife.bind(this);
        linTitleLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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


        allCallTitleButton.setAllCallTitleButtonListener(new AllCallTitleButton.AllCallTitleButtonListener() {
            @Override
            public void TagChange(int position) {

                if (position < 1) {
                    viewType = 1;
                } else if (position == 1) {
                    viewType = 2;
                } else if (position == 2) {
                    viewType = 3;
                }
                rb1.setChecked(true);
                page = 1;
                status = 1;
                getData();

            }
        });


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


    class MyAdapter extends RecyclerView.Adapter {


        List<OrderListEntity.DataBean> list;

        public MyAdapter(List<OrderListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            if (viewType == 1) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_all_call_list_1, parent, false);
                return new ViewHolderType1(v);
            } else {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_all_call_list_2, parent, false);
                return new ViewHolderType2(v);
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            final OrderListEntity.DataBean bean = list.get(position);

            //图文
            if (getItemViewType(position) == 1) {


                ((ViewHolderType1) holder).tvOrderNo.setText("订单号: " + (bean.getOrder_sn() == null ? "" : bean.getOrder_sn()));
                // -1=已取消 9=未支付 1=已支付 2=完成  3=已评价 4=申请退款 5=已退款 6=拒绝退款
                //已放弃
                if (bean.getStatus() == -1 || bean.getStatus() == 5) {
                    ((ViewHolderType1) holder).tvOrderStatus.setText("已放弃");
                    ((ViewHolderType1) holder).tvBottomLeft.setText("删除订单");
                    ((ViewHolderType1) holder).tvBottomRight.setText("");
                    ((ViewHolderType1) holder).tvBottomRight.setVisibility(View.GONE);
                    ((ViewHolderType1) holder).viewLine.setVisibility(View.GONE);

                    ((ViewHolderType1) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 5);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType1) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 5);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                }
                //待支付
                else if (bean.getStatus() == 9) {
                    ((ViewHolderType1) holder).tvOrderStatus.setText("待支付");
                    ((ViewHolderType1) holder).tvBottomLeft.setText("放弃咨询");

                    ((ViewHolderType1) holder).tvBottomRight.setText("立即付款");
                    ((ViewHolderType1) holder).tvBottomRight.setTextColor(getResources().getColor(R.color.app_title_top));
                    ((ViewHolderType1) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType1) holder).viewLine.setVisibility(View.VISIBLE);

                    ((ViewHolderType1) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 2);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType1) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 2);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });

                }
//               未结束
                else if (bean.getStatus() == 1 || bean.getStatus() == 4 || bean.getStatus() == 6) {

                    ((ViewHolderType1) holder).tvOrderStatus.setText("未结束");
                    if (bean.getStatus() == 4) {
                        ((ViewHolderType1) holder).tvBottomLeft.setText("退款审核中");
                    } else {
                        ((ViewHolderType1) holder).tvBottomLeft.setText("放弃咨询");
                    }


                    ((ViewHolderType1) holder).tvBottomRight.setText("确定结束");
                    ((ViewHolderType1) holder).tvBottomRight.setTextColor(getResources().getColor(R.color.text_gray333));
                    ((ViewHolderType1) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType1) holder).viewLine.setVisibility(View.VISIBLE);

                    ((ViewHolderType1) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (bean.getStatus() == 4) {

                                ToastMessage("正在审核退款中，请勿操作");
                                return;
                            }
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 3);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType1) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 3);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                }
//                已结束
                else if (bean.getStatus() == 2 || bean.getStatus() == 3) {
                    ((ViewHolderType1) holder).tvOrderStatus.setText("已结束");
                    ((ViewHolderType1) holder).tvBottomLeft.setText("删除订单");
                    if (bean.getStatus() == 3) {
                        ((ViewHolderType1) holder).tvBottomRight.setText("已评价");

                    } else {
                        ((ViewHolderType1) holder).tvBottomRight.setText("评价医生");
                    }
                    ((ViewHolderType1) holder).tvBottomRight.setTextColor(Color.parseColor("#FF2D9711"));
                    ((ViewHolderType1) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType1) holder).viewLine.setVisibility(View.VISIBLE);


                    ((ViewHolderType1) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 4);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType1) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (bean.getStatus() == 3) {
                                ToastMessage("请勿重复评价");
                                return;
                            }
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 4);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                } else {
                    ((ViewHolderType1) holder).tvOrderStatus.setVisibility(View.GONE);
                    ((ViewHolderType1) holder).tvBottomLeft.setVisibility(View.GONE);
                    ((ViewHolderType1) holder).tvBottomRight.setVisibility(View.GONE);
                    ((ViewHolderType1) holder).viewLine.setVisibility(View.GONE);
                }

                if (bean.getLog() != null) {
                    ((ViewHolderType1) holder).tvUserName.setText(bean.getLog().getUsername() == null ? "" : bean.getLog().getUsername());
                    ((ViewHolderType1) holder).tvQuestion.setText(bean.getLog().getMessage() == null ? "" : bean.getLog().getMessage());
                }
                ((ViewHolderType1) holder).tvDoctorName.setText(bean.getDoctor_name() == null ? "" : bean.getDoctor_name());


                ((ViewHolderType1) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                    }
                });

            }
            //语音 视频
            else {


                ((ViewHolderType2) holder).tvOrderNo.setText("订单号: " + (bean.getOrder_sn() == null ? "" : bean.getOrder_sn()));
                // -1=已取消 9=未支付 1=已支付 2=完成  3=已评价 4=申请退款 5=已退款 6=拒绝退款
                //已放弃
                if (bean.getStatus() == -1 || bean.getStatus() == 5) {

                    ((ViewHolderType2) holder).tvOrderStatus.setText("已放弃");
                    ((ViewHolderType2) holder).tvBottomLeft.setText("删除订单");
                    ((ViewHolderType2) holder).tvBottomRight.setText("");
                    ((ViewHolderType2) holder).tvBottomRight.setVisibility(View.GONE);
                    ((ViewHolderType2) holder).viewLine.setVisibility(View.GONE);

                    ((ViewHolderType2) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 5);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType2) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 5);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                }
                //待支付
                else if (bean.getStatus() == 9) {
                    ((ViewHolderType2) holder).tvOrderStatus.setText("待支付");
                    ((ViewHolderType2) holder).tvBottomLeft.setText("放弃咨询");

                    ((ViewHolderType2) holder).tvBottomRight.setText("立即付款");
                    ((ViewHolderType2) holder).tvBottomRight.setTextColor(getResources().getColor(R.color.app_title_top));
                    ((ViewHolderType2) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType2) holder).viewLine.setVisibility(View.VISIBLE);

                    ((ViewHolderType2) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 2);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType2) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 2);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                }
//               未结束
                else if (bean.getStatus() == 1 || bean.getStatus() == 4 || bean.getStatus() == 6) {

                    ((ViewHolderType2) holder).tvOrderStatus.setText("未结束");
                    if (bean.getStatus() == 4) {
                        ((ViewHolderType2) holder).tvBottomLeft.setText("退款审核中");
                    } else {
                        ((ViewHolderType2) holder).tvBottomLeft.setText("放弃咨询");
                    }

                    ((ViewHolderType2) holder).tvBottomRight.setText("确定结束");
                    ((ViewHolderType2) holder).tvBottomRight.setTextColor(getResources().getColor(R.color.text_gray333));
                    ((ViewHolderType2) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType2) holder).viewLine.setVisibility(View.VISIBLE);


                    ((ViewHolderType2) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (bean.getStatus() == 4) {

                                ToastMessage("正在审核退款中，请勿操作");
                                return;
                            }
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 3);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType2) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 3);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });

                }
//                已结束
                else if (bean.getStatus() == 2 || bean.getStatus() == 3) {
                    ((ViewHolderType2) holder).tvOrderStatus.setText("已结束");
                    ((ViewHolderType2) holder).tvBottomLeft.setText("删除订单");

                    if (bean.getStatus() == 3) {
                        ((ViewHolderType2) holder).tvBottomRight.setText("已评价");

                    } else {
                        ((ViewHolderType2) holder).tvBottomRight.setText("评价医生");
                    }
                    ((ViewHolderType2) holder).tvBottomRight.setTextColor(Color.parseColor("#FF2D9711"));
                    ((ViewHolderType2) holder).tvBottomRight.setVisibility(View.VISIBLE);
                    ((ViewHolderType2) holder).viewLine.setVisibility(View.VISIBLE);

                    ((ViewHolderType2) holder).tvBottomLeft.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("left", 4);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });
                    ((ViewHolderType2) holder).tvBottomRight.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (bean.getStatus() == 3) {
                                ToastMessage("请勿重复评价");
                                return;
                            }
                            Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                            intent.putExtra("order_id", bean.getId());
                            intent.putExtra("right", 4);
                            MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                        }
                    });

                } else {
                    ((ViewHolderType2) holder).tvOrderStatus.setVisibility(View.GONE);
                    ((ViewHolderType2) holder).tvBottomLeft.setVisibility(View.GONE);
                    ((ViewHolderType2) holder).tvBottomRight.setVisibility(View.GONE);
                    ((ViewHolderType2) holder).viewLine.setVisibility(View.GONE);
                }

                GlideUtils.LoadImg(((ViewHolderType2) holder).imgAvatar, bean.getAvatar_url());
                ((ViewHolderType2) holder).tvDoctorName.setText(bean.getDoctor_name() == null ? "" : bean.getDoctor_name());

                ((ViewHolderType2) holder).tvLevel.setText(bean.getCate_type() == null ? "" : bean.getCate_type());

                String doctor_content = "";
                if (bean.getHospital_name() != null) {
                    doctor_content += bean.getHospital_name();
                }
                if (bean.getSection_info() != null) {
                    doctor_content += "  " + bean.getSection_info();
                }
                ((ViewHolderType2) holder).tvDoctorContent.setText(doctor_content);

                if (bean.getLog() != null) {
                    ((ViewHolderType2) holder).tvContent.setText(bean.getLog().getMessage());
                }

                ((ViewHolderType2) holder).itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(MyAllCallListActivity.this, OrderInfoActivity.class);
                        intent.putExtra("order_id", bean.getId());
                        MyAllCallListActivity.this.startActivityForResult(intent, 1000);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public int getItemViewType(int position) {
            return viewType;
        }

        class ViewHolderType1 extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_order_no)
            TextView tvOrderNo;
            @BindView(R.id.tv_order_status)
            TextView tvOrderStatus;
            @BindView(R.id.tv_user_name)
            TextView tvUserName;
            @BindView(R.id.tv_doctor_name)
            TextView tvDoctorName;
            @BindView(R.id.tv_question)
            TextView tvQuestion;
            @BindView(R.id.tv_bottom_left)
            TextView tvBottomLeft;
            @BindView(R.id.tv_bottom_right)
            TextView tvBottomRight;
            @BindView(R.id.view_line)
            View viewLine;

            ViewHolderType1(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }

        class ViewHolderType2 extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_order_no)
            TextView tvOrderNo;
            @BindView(R.id.tv_order_status)
            TextView tvOrderStatus;
            @BindView(R.id.img_avatar)
            CircleImageView imgAvatar;
            @BindView(R.id.tv_doctor_name)
            TextView tvDoctorName;
            @BindView(R.id.tv_level)
            TextView tvLevel;
            @BindView(R.id.tv_doctor_content)
            TextView tvDoctorContent;
            @BindView(R.id.tv_content)
            TextView tvContent;
            @BindView(R.id.tv_bottom_left)
            TextView tvBottomLeft;
            @BindView(R.id.tv_bottom_right)
            TextView tvBottomRight;
            @BindView(R.id.view_line)
            View viewLine;

            ViewHolderType2(View view) {
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
        m.put("type", String.valueOf(viewType));
        m.put("status", String.valueOf(status));
        HttpUtils.getInstance().post(m, "user/my_order", new AllCallback<OrderListEntity>(OrderListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                listView.refreshComplete();
                listView.loadMoreComplete();
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(OrderListEntity response) {
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
