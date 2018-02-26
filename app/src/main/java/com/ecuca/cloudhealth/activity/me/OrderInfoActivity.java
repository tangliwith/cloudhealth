package com.ecuca.cloudhealth.activity.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.OrderInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.ViewPagerActivity;
import com.ecuca.cloudhealth.activity.doctor.PaySuccessActivity;
import com.ecuca.cloudhealth.dialog.ChoosePayTypeDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/25.
 */

public class OrderInfoActivity extends BaseActivity {


    int order_id;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_doctor_content)
    TextView tvDoctorContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_doctor_name)
    TextView tvDoctorName;
    @BindView(R.id.tv_order_price)
    TextView tvOrderPrice;
    @BindView(R.id.lin_order_price)
    LinearLayout linOrderPrice;
    @BindView(R.id.tv_question)
    TextView tvQuestion;
    @BindView(R.id.img_list_view)
    RecyclerView imgListView;
    @BindView(R.id.tv_reply_content)
    TextView tvReplyContent;
    @BindView(R.id.lin_reply_content)
    LinearLayout linReplyContent;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_create_time)
    TextView tvCreateTime;
    @BindView(R.id.tv_pay_time)
    TextView tvPayTime;
    @BindView(R.id.lin_pay_time)
    LinearLayout linPayTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_bottom_left)
    TextView tvBottomLeft;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_bottom_right)
    TextView tvBottomRight;
    @BindView(R.id.lin_end_time)
    LinearLayout linEndTime;


    ImgAdapter imgAdapter;
    ArrayList<String> imgList;

    int btnLeft, btnLeft_1 = 0;
    int btnRight, btnRight_1 = 0;

    OrderInfoEntity.DataBean orderBean;

    @Override
    protected void setContentView() {
        btnLeft = getIntent().getIntExtra("left", 0);
        btnRight = getIntent().getIntExtra("right", 0);
        order_id = getIntent().getIntExtra("order_id", 0);
        setContentView(R.layout.aty_order_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("咨询详情");
    }

    @Override
    protected void initUI() {


        GridLayoutManager manager = new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false);
        imgListView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {

        imgList = new ArrayList<>();
        imgAdapter = new ImgAdapter(imgList);
        imgListView.setAdapter(imgAdapter);
        getData();
    }


    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {


        tvBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (btnLeft_1 > 1)
                    doLeftBtnOnClick(btnLeft_1, orderBean);
                

            }
        });
        tvBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (btnRight_1 > 1)
                    doRightBtnOnClick(btnRight_1, orderBean);
            }
        });
    }


    private void getData() {

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "user/my_order_info", new AllCallback<OrderInfoEntity>(OrderInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(OrderInfoEntity response) {

                if (response != null) {

                    if (response.getData() != null) {
                        orderBean = response.getData();
                        setData(orderBean);

                    } else {
                        ToastMessage("数据异常");
                        finish();
                    }

                } else {
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });
    }

    private void setData(OrderInfoEntity.DataBean bean) {

        if (bean.getDoctor() != null) {
            GlideUtils.LoadImg(imgHead, bean.getDoctor().getAvatar_url());
            tvName.setText(bean.getDoctor().getNick_name() == null ? "" : bean.getDoctor().getNick_name());
            tvDoctorName.setText(bean.getDoctor().getNick_name() == null ? "" : bean.getDoctor().getNick_name());
            tvLevel.setText(bean.getDoctor().getCate() == null ? "" : bean.getDoctor().getCate());
            String content = "";
            if (bean.getDoctor().getHospital() != null) {
                content += bean.getDoctor().getHospital();
            }
            if (bean.getDoctor().getSection() != null) {
                content += " " + bean.getDoctor().getSection();
            }
            tvDoctorContent.setText(content);
            if (bean.getTotal_money() != null)
                tvPrice.setText(bean.getTotal_money() + "元/次");
        }


        if (bean.getLog() != null) {
            tvUserName.setText(bean.getLog().getUsername());
            tvQuestion.setText(bean.getLog().getMessage() == null ? "" : bean.getLog().getMessage());


            if (bean.getLog().getGraphic() != null)
                imgList.clear();
            for (String s : bean.getLog().getGraphic()) {

                imgList.add(s);
            }

            imgAdapter.notifyDataSetChanged();
        }

        if (bean.getTotal_money() != null) {
            linOrderPrice.setVisibility(View.VISIBLE);
            tvOrderPrice.setText("¥ " + bean.getTotal_money());

        } else {
            linOrderPrice.setVisibility(View.GONE);
        }

        if (bean.getIs_reply() == 1) {
            linReplyContent.setVisibility(View.VISIBLE);
            if (bean.getLog() != null) {
                tvReplyContent.setText(bean.getLog().getReply_content() == null ? "" : bean.getLog().getReply_content());
            } else {
                tvReplyContent.setText("");
            }
        } else {
            linReplyContent.setVisibility(View.GONE);
        }


        //已放弃
        if (bean.getStatus() == -1 || bean.getStatus() == 5) {
            tvOrderStatus.setText("已放弃");
            tvBottomLeft.setText("删除订单");
            tvBottomRight.setText("");
            tvBottomRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLeft_1 = 5;
            btnRight_1 = 5;
        }
        //待支付
        else if (bean.getStatus() == 9) {
            tvOrderStatus.setText("待支付");
            tvBottomLeft.setText("放弃咨询");
            tvBottomRight.setText("立即付款");
            tvBottomRight.setTextColor(getResources().getColor(R.color.app_title_top));
            tvBottomRight.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLeft_1 = 2;
            btnRight_1 = 2;
        }
//               未结束
        else if (bean.getStatus() == 1 || bean.getStatus() == 4 || bean.getStatus() == 6) {

            if (bean.getStatus() == 4) {
                tvBottomLeft.setText("退款审核中");
            } else {
                tvBottomLeft.setText("放弃咨询");
            }
            tvOrderStatus.setText("未结束");

            tvBottomRight.setText("确定结束");
            tvBottomRight.setTextColor(getResources().getColor(R.color.text_gray333));
            tvBottomRight.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLeft_1 = 3;
            btnRight_1 = 3;
        }
//                已结束
        else if (bean.getStatus() == 2 || bean.getStatus() == 3) {
            tvOrderStatus.setText("已结束");
            tvBottomLeft.setText("删除订单");

            if (bean.getStatus() == 3) {
                tvBottomRight.setText("已评价");

            } else {
                tvBottomRight.setText("评价医生");
            }
            tvBottomRight.setTextColor(Color.parseColor("#FF2D9711"));
            tvBottomRight.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLeft_1 = 4;
            btnRight_1 = 4;
        } else {
            tvOrderStatus.setVisibility(View.GONE);
            tvBottomLeft.setVisibility(View.GONE);
            tvBottomRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLeft_1 = 0;
            btnRight_1 = 0;
        }


        tvOrderNo.setText(bean.getOrder_sn());
        tvCreateTime.setText(DateUtils.data(bean.getAdd_time()));
        if (bean.getPay_time() != null) {
            linPayTime.setVisibility(View.VISIBLE);
            tvPayTime.setText(DateUtils.data(bean.getPay_time()));
        } else {
            linPayTime.setVisibility(View.GONE);
        }

        if (bean.getFinish_time() != null) {
            linEndTime.setVisibility(View.VISIBLE);
            tvEndTime.setText(DateUtils.data(bean.getFinish_time()));
        } else {
            linEndTime.setVisibility(View.GONE);
        }


        if (btnLeft > 0) {
            doLeftBtnOnClick(btnLeft, bean);
        }

        if (btnRight > 0) {
            doRightBtnOnClick(btnRight, bean);
        }


    }


    class ImgAdapter extends RecyclerView.Adapter<ImgAdapter.ViewHolder> {


        ArrayList<String> list;

        public ImgAdapter(ArrayList<String> list) {
            this.list = list;
        }

        @Override
        public ImgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_img, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ImgAdapter.ViewHolder holder, final int position) {


            GlideUtils.LoadImg(holder.img, list.get(position));

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(OrderInfoActivity.this, ViewPagerActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("list", list);
                    OrderInfoActivity.this.startActivity(intent);

                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.img)
            ImageView img;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    /**
     * 左边按钮点击的动作
     *
     * @param status 2未支付，3 未结束，4已结束，5 已放弃。
     */
    private void doLeftBtnOnClick(int status, OrderInfoEntity.DataBean bean) {

        switch (status) {

            //没有付款，取消订单
            case 2:
                showAlertDialogMessage("提示", "确定要放弃本次咨询？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        cancleOrder();
                    }
                });

                break;
            //未结束, 申请退款
            case 3:
                if (bean.getStatus() == 4) {
                    ToastMessage("正在审核中，请勿操作");
                    return;
                }
                Intent intent = new Intent(OrderInfoActivity.this, OrderRefundActivity.class);
                intent.putExtra("order_id", order_id);
                OrderInfoActivity.this.startActivityForResult(intent, 1000);
                break;
            // 已结束 删除订单
            case 4:
                showAlertDialogMessage("提示", "确定要删除本订单信息？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delOrder();
                    }
                });

                break;
            // 已放弃 删除订单
            case 5:
                showAlertDialogMessage("提示", "确定要删除本订单信息？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delOrder();
                    }
                });

                break;
        }

    }

    /**
     * 左边按钮点击的动作
     *
     * @param status 2未支付，3 未结束，4已结束，5 已放弃。
     */
    private void doRightBtnOnClick(int status, OrderInfoEntity.DataBean bean) {

        switch (status) {

            //没有付款，跳到支付界面
            case 2:
                doPay(bean);
                break;
            //未结束, 结束订单
            case 3:

                if (bean.getStatus() == 4) {
                    ToastMessage("正在审核退款中，请勿操作");
                    return;
                }

                showAlertDialogMessage("提示", "确定要结束本次咨询？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finishOrder();
                    }
                });

                break;
            // 已结束 评价医生
            case 4:
                Intent intent = new Intent(OrderInfoActivity.this, OrderEvaluateActivity.class);
                intent.putExtra("order_id", order_id);
                OrderInfoActivity.this.startActivityForResult(intent, 1000);
                break;
            // 已放弃
            case 5:

                break;
        }

    }


    /**
     * 立即付款
     */
    private void doPay(final OrderInfoEntity.DataBean bean) {


        ChoosePayTypeDialog dialog = new ChoosePayTypeDialog(OrderInfoActivity.this, R.style.transparentFrameWindowStyle, new ChoosePayTypeDialog.OnChoosePayTypeListener() {
            @Override
            public void onChooseBalance() {

                doBalancePay(bean.getOrder_id(), bean.getType(), bean.getDoctor_id());
            }

            @Override
            public void onChooseWeChat() {
//                Intent intent=new Intent(MyAllCallListActivity.this,PaySuccessActivity.class);
//                intent.putExtra("type",viewType);
//                MyAllCallListActivity.this.startActivityForResult(intent,1000);
//                finish();
            }

            @Override
            public void onChooseAli() {
//                Intent intent=new Intent(MyAllCallListActivity.this,PaySuccessActivity.class);
//                intent.putExtra("type",viewType);
//               MyAllCallListActivity.this.startActivityForResult(intent,1000);
//                finish();
            }
        });
        dialog.show();
    }

    /**
     * 余额支付
     */
    private void doBalancePay(final int order_id, final int type, final int doctor_id) {

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "balance/pay_money_on_balance", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        Intent intent = new Intent(OrderInfoActivity.this, PaySuccessActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("doctor_id", doctor_id);
                        intent.putExtra("order_id", order_id);
                        OrderInfoActivity.this.startActivityForResult(intent, 1000);
                        finish();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("支付失败");
                }
            }
        });
    }


    /**
     * 取消订单
     */
    private void cancleOrder() {

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "order/cancle_order", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        finish();
                    } else {
                        ToastMessage(response.getMsg());

                    }
                } else {
                    ToastMessage("取消失败");
                }

            }
        });
    }


    /**
     * 删除订单
     */
    private void delOrder() {

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "order/delete_order", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        finish();
                    } else {
                        ToastMessage(response.getMsg());

                    }
                } else {
                    ToastMessage("删除失败");
                }

            }
        });
    }

    /**
     * 完成订单
     */
    private void finishOrder() {

        Map<String, String> m = new HashMap<>();
        m.put("order_id", String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "order/finish_order", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        finish();
                    } else {
                        ToastMessage(response.getMsg());

                    }
                } else {
                    ToastMessage("结束失败");
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 6001) {
            btnLeft = 0;
            btnLeft_1 = 0;
            getData();
        } else if (resultCode == 6002) {
            btnRight = 0;
            btnRight_1 = 0;
            getData();
        }
    }
}
