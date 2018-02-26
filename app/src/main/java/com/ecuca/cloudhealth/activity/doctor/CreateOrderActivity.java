package com.ecuca.cloudhealth.activity.doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.CreateOrderInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.dialog.ChoosePayTypeDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/26.
 */

public class CreateOrderActivity extends BaseActivity {
    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    @BindView(R.id.tv_do_pay)
    TextView tvDoPay;
    @BindView(R.id.tv_pay_total_price)
    TextView tvPayPrice;

    MyAdapter adapter;
    List<CreateOrderInfoEntity.DataBean.GoodsListBean> list;
    int order_id;
    int type=0;
    int doctor_id;
    @Override
    protected void setContentView() {
        doctor_id=getIntent().getIntExtra("doctor_id",0);
        order_id=getIntent().getIntExtra("order_id",0);
        type=getIntent().getIntExtra("type",0);
        setContentView(R.layout.aty_create_order);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("新建订单");
    }


    protected void showTitleBack() {
        LinearLayout mLinBack = getID(R.id.lin_title_lelft);
        ImageView mImgBack = getID(R.id.img_title_left);
        mImgBack.setVisibility(View.VISIBLE);
        mLinBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
        showAlertDialogMessage("提示", "是否要放弃咨询?", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                finish();
            }
        });

    }

    @Override
    protected void initUI() {

        LinearLayoutManager manager=new LinearLayoutManager(this);
        listView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {

        list=new ArrayList<>();
        adapter=new MyAdapter(list);
        listView.setAdapter(adapter);
        getOrderInfo();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        tvDoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChoosePayTypeDialog dialog=new ChoosePayTypeDialog(CreateOrderActivity.this, R.style.transparentFrameWindowStyle, new ChoosePayTypeDialog.OnChoosePayTypeListener() {
                    @Override
                    public void onChooseBalance() {

                      doBalancePay();
                    }

                    @Override
                    public void onChooseWeChat() {
                        Intent intent=new Intent(CreateOrderActivity.this,PaySuccessActivity.class);
                        intent.putExtra("type",type);
                        CreateOrderActivity.this.startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onChooseAli() {
                        Intent intent=new Intent(CreateOrderActivity.this,PaySuccessActivity.class);
                        intent.putExtra("type",type);
                        CreateOrderActivity.this.startActivity(intent);
                        finish();
                    }
                });
                dialog.show();
            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter <MyAdapter.ViewHolder>{


        List<CreateOrderInfoEntity.DataBean.GoodsListBean> list;

        public MyAdapter(List<CreateOrderInfoEntity.DataBean.GoodsListBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_create_order, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            CreateOrderInfoEntity.DataBean.GoodsListBean bean=list.get(position);
            holder.tvGoodsName.setText(bean.getGoods_name());
            holder.tvGoodsPrice.setText("¥ "+bean.getGoods_money());

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

         class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.tv_goods_name)
            TextView tvGoodsName;
            @BindView(R.id.tv_goods_price)
            TextView tvGoodsPrice;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }


    /**
     *  获取订单详情
     */
    private void getOrderInfo(){

        showProgressDialog("请求中...");

        Map<String,String> m=new HashMap<>();
        m.put("order_id",String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "order/get_order_info", new AllCallback<CreateOrderInfoEntity>(CreateOrderInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                disProgressDialog();
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(CreateOrderInfoEntity response) {
                disProgressDialog();
                if(response!=null){

                    if(response.getCode()==200){

                        if(response.getData()!=null){

                            tvOrderNo.setText("订单号: "+response.getData().getOrder_sn());
                            tvTotalPrice.setText("¥ "+response.getData().getTotal_money());
                            tvPayPrice.setText("¥ "+response.getData().getPay_money());
                            if(response.getData().getGoods_list()!=null){
                                list.clear();
                                list.addAll(response.getData().getGoods_list());
                                adapter.notifyDataSetChanged();
                            }


                        }
                        else{
                            ToastMessage("数据异常");
                            finish();
                        }
                    }
                    else{
                        ToastMessage(response.getMsg());
                        finish();
                    }
                }
                else{

                }
            }
        });
    }


    /**
     * 余额支付
     */
    private void doBalancePay(){

        Map<String,String> m=new HashMap<>();
        m.put("order_id",String.valueOf(order_id));
        HttpUtils.getInstance().post(m, "balance/pay_money_on_balance", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if(response!=null){

                    if(response.getCode()==200){
                        ToastMessage(response.getMsg());
                        Intent intent=new Intent(CreateOrderActivity.this,PaySuccessActivity.class);
                        intent.putExtra("type",type);
                        intent.putExtra("doctor_id",doctor_id);
                        intent.putExtra("order_id",order_id);
                        CreateOrderActivity.this.startActivity(intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("支付失败");
                }
            }
        });
    }
}
