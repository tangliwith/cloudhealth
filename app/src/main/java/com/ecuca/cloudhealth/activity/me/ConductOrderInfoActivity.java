package com.ecuca.cloudhealth.activity.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.ConductOrderInfoEntity;
import com.ecuca.cloudhealth.Entity.OrderInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.doctor.PaySuccessActivity;
import com.ecuca.cloudhealth.dialog.ChoosePayTypeDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 我的陪诊订单详情界面
 * Created by tuhualong on 2017/12/25.
 */

public class ConductOrderInfoActivity extends BaseActivity {


    int order_id;


    int btnLeft, btnLeft_1 = 0;
    int btnRight, btnRight_1 = 0;

    ConductOrderInfoEntity.DataBean orderBean;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.tv_hospital_name)
    TextView tvHospitalName;
    @BindView(R.id.tv_with_time)
    TextView tvWithTime;
    @BindView(R.id.lin_order_price)
    LinearLayout linOrderPrice;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_mobile)
    TextView tvUserMobile;
    @BindView(R.id.tv_with_type)
    TextView tvWithType;
    @BindView(R.id.tv_with_money)
    TextView tvWithMoney;
    @BindView(R.id.tv_bottom_left)
    TextView tvBottomLeft;
    @BindView(R.id.view_line)
    View viewLine;
    @BindView(R.id.tv_bottom_right)
    TextView tvBottomRight;

    @Override
    protected void setContentView() {
        btnLeft = getIntent().getIntExtra("left", 0);
        btnRight = getIntent().getIntExtra("right", 0);
        order_id = getIntent().getIntExtra("order_id", 0);
        setContentView(R.layout.aty_conduct_order_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("陪诊详情");
    }

    @Override
    protected void initUI() {


    }

    @Override
    protected void initData() {


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
        HttpUtils.getInstance().post(m, "order/with_diagnosis_detail", new AllCallback<ConductOrderInfoEntity>(ConductOrderInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(ConductOrderInfoEntity response) {

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

    private void setData(ConductOrderInfoEntity.DataBean bean) {


        tvUserAddress.setText(bean.getProvince_name()+" "+bean.getCity_name());
        tvHospitalName.setText(bean.getHospital_name()==null?"":bean.getHospital_name());
        tvWithTime.setText(DateUtils.data(bean.getWith_time()));
        tvUserName.setText(bean.getUser_name()==null?"":bean.getUser_name());
        tvUserMobile.setText(bean.getMobile()==null?"":bean.getMobile());
        if (bean.getDiagnosis_type() == 1) {
            tvWithType.setText("协助就医服务");
        } else if (bean.getDiagnosis_type() == 2) {
            tvWithType.setText("全程陪诊服务");
        }
        else{
            tvWithType.setText("");
        }
        tvWithMoney.setText("¥ "+(bean.getTotal_money()==null?"":bean.getTotal_money()));



        //已放弃
        if (bean.getStatus() == -1 || bean.getStatus() == 5) {

            tvBottomLeft.setText("删除陪诊");
            tvBottomRight.setText("");
            tvBottomRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLeft_1 = 5;
            btnRight_1 = 5;
        }
        //待支付
        else if (bean.getStatus() == 9) {

            tvBottomLeft.setText("放弃申请");
            tvBottomRight.setText("立即付款");
            tvBottomRight.setTextColor(getResources().getColor(R.color.app_title_top));
            tvBottomRight.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLeft_1 = 2;
            btnRight_1 = 2;
        }
        //未开始
        else if (bean.getStatus() == 1 || bean.getStatus() == 4 || bean.getStatus() == 6) {
            if (bean.getStatus() == 4) {
                tvBottomLeft.setText("退款审核中");
            } else {
                tvBottomLeft.setText("放弃申请");
            }
            tvBottomRight.setText("确定结束");
            tvBottomRight.setTextColor(getResources().getColor(R.color.text_gray333));
            tvBottomRight.setVisibility(View.VISIBLE);
            viewLine.setVisibility(View.VISIBLE);
            btnLeft_1 = 3;
            btnRight_1 = 3;
        }
        //已完成
        else if (bean.getStatus() == 2 || bean.getStatus() == 3) {

            tvBottomLeft.setText("删除订单");
            tvBottomRight.setTextColor(Color.parseColor("#FF2D9711"));
            tvBottomRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLeft_1 = 4;
            btnRight_1 = 4;
        } else {

            tvBottomLeft.setVisibility(View.GONE);
            tvBottomRight.setVisibility(View.GONE);
            viewLine.setVisibility(View.GONE);
            btnLeft_1 = 0;
            btnRight_1 = 0;
        }

        if (btnLeft > 0) {
            doLeftBtnOnClick(btnLeft, bean);
        }

        if (btnRight > 0) {
            doRightBtnOnClick(btnRight, bean);
        }


    }


    /**
     * 左边按钮点击的动作
     *
     * @param status 2未支付，3 未结束，4已结束，5 已放弃。
     */
    private void doLeftBtnOnClick(int status, ConductOrderInfoEntity.DataBean bean) {

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
                Intent intent = new Intent(ConductOrderInfoActivity.this, OrderRefundActivity.class);
                intent.putExtra("order_id", order_id);
                ConductOrderInfoActivity.this.startActivityForResult(intent, 1000);
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
    private void doRightBtnOnClick(int status, ConductOrderInfoEntity.DataBean bean) {

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

                break;
            // 已放弃
            case 5:

                break;
        }

    }


    /**
     * 立即付款
     */
    private void doPay(final ConductOrderInfoEntity.DataBean bean) {


        ChoosePayTypeDialog dialog = new ChoosePayTypeDialog(ConductOrderInfoActivity.this, R.style.transparentFrameWindowStyle, new ChoosePayTypeDialog.OnChoosePayTypeListener() {
            @Override
            public void onChooseBalance() {

               // doBalancePay(bean.getOrder_id(), bean.getType(), bean.getDoctor_id());
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
                        Intent intent = new Intent(ConductOrderInfoActivity.this, PaySuccessActivity.class);
                        intent.putExtra("type", type);
                        intent.putExtra("doctor_id", doctor_id);
                        intent.putExtra("order_id", order_id);
                        ConductOrderInfoActivity.this.startActivityForResult(intent, 1000);
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
