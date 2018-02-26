package com.ecuca.cloudhealth.activity.me;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
import com.ecuca.cloudhealth.dialog.photodialog.AlertChooser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * 放弃咨询
 * Created by TuHuaLong on 2018/1/26.
 */

public class OrderRefundActivity extends BaseActivity {
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
    @BindView(R.id.tv_refund_money)
    TextView tvRefundMoney;
    @BindView(R.id.tv_refund_reason)
    TextView tvRefundReason;
    @BindView(R.id.et_refund_content)
    EditText etRefundContent;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @BindView(R.id.lin_refund_reason)
    LinearLayout linRefundReason;


    ImgAdapter imgAdapter;
    int order_id;
    ArrayList<String> imgList;
    @Override
    protected void setContentView() {

        order_id = getIntent().getIntExtra("order_id", 0);
        setContentView(R.layout.aty_order_refund);
        ButterKnife.bind(this);
        setTitleText("放弃咨询");
        showTitleBack();
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

        linRefundReason.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertChooser.Builder alertChooser = new AlertChooser.Builder(
                        getActivity(),R.style.AlertChooser);
                alertChooser.setNegativeItem("关闭",
                        new AlertChooser.OnItemClickListener() {

                            @Override
                            public void OnItemClick(AlertChooser chooser) {
                                // TODO Auto-generated method stub
                                chooser.dismiss();
                            }
                        });
                alertChooser.addItem("不想咨询", new AlertChooser.OnItemClickListener() {

                    @Override
                    public void OnItemClick(AlertChooser chooser) {
                        // TODO Auto-generated method stub

                        tvRefundReason.setText("不想咨询");
                        chooser.dismiss();
                    }
                });
                alertChooser.addItem("咨询错误", new AlertChooser.OnItemClickListener() {

                    @Override
                    public void OnItemClick(AlertChooser chooser) {
                        // TODO Auto-generated method stub
                        tvRefundReason.setText("咨询错误");
                        chooser.dismiss();
                    }
                });
                alertChooser.addItem("其他原因", new AlertChooser.OnItemClickListener() {

                    @Override
                    public void OnItemClick(AlertChooser chooser) {
                        // TODO Auto-generated method stub
                        tvRefundReason.setText("其他原因");
                        chooser.dismiss();
                    }
                });
                alertChooser.show();
            }
        });

        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subData();
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

                        setData(response.getData());

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



        tvRefundMoney.setText("¥ "+(bean.getTotal_money()==null?"":bean.getTotal_money()));


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
                    Intent intent = new Intent(OrderRefundActivity.this, ViewPagerActivity.class);
                    intent.putExtra("position", position);
                    intent.putExtra("list", list);
                    OrderRefundActivity.this.startActivity(intent);

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

    private void subData(){

        String back_reason=tvRefundReason.getText().toString().trim();
        if(back_reason.equals("请选择退款原因")){

            ToastMessage("请选择退款原因");
            return;
        }

        String back_message=etRefundContent.getText().toString().trim();

        Map<String,String> m=new HashMap<>();
        m.put("order_id",String.valueOf(order_id));
        m.put("back_reason",back_reason);
        m.put("back_message",back_message);
        HttpUtils.getInstance().post(m, "order/back_order_apply", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if(response!=null){

                    if(response.getCode()==200){
                        ToastMessage(response.getMsg());
                        Intent intent=new Intent();
                        setResult(6001,intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());

                    }
                }
                else{
                    ToastMessage("结束失败");
                }

            }
        });
    }
}
