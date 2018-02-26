package com.ecuca.cloudhealth.activity.doctor;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.ContactListEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.ecuca.cloudhealth.activity.me.ContactsListActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/28.
 */

public class DoImageTextCallTwoActivity extends BaseActivity {
    @BindView(R.id.list_view)
    RecyclerView listView;
    @BindView(R.id.tv_add)
    TextView tvAdd;

    @BindView(R.id.tv_sub)
    TextView tvSub;

    List<ContactListEntity.DataBean> list;
    MyAdapter adapter;
    private int select = 1;

    int type;

    int log_id;
    int selectUid;

    @Override
    protected void setContentView() {
        log_id = getIntent().getIntExtra("log_id", 0);
        type = getIntent().getIntExtra("type", 0);
        setContentView(R.layout.aty_do_image_text_call_two);
        ButterKnife.bind(this);
        showTitleBack();
        if (type <= 0) {
            finish();
        }
        if (type == 1) {
            setTitleText("图文咨询");
        } else if (type == 2) {
            setTitleText("语音咨询");
        } else if (type == 3) {
            setTitleText("视频咨询");
        }


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

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listView.setLayoutManager(manager);
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


        tvAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(DoImageTextCallTwoActivity.this, AddImageTextCallUserActivity.class);
                DoImageTextCallTwoActivity.this.startActivityForResult(intent, 1000);

            }
        });
        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subData();


            }
        });
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        List<ContactListEntity.DataBean> list;

        public MyAdapter(List<ContactListEntity.DataBean> list) {
            this.list = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_do_image_text_call_two, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {

            final ContactListEntity.DataBean bean = list.get(position);
            holder.tvName.setText(bean.getNick_name());
            holder.tvMobile.setText(bean.getUser_name());
            GlideUtils.LoadImg(holder.imgHead, bean.getAvatar_url());

            if (position == select) {
                selectUid = bean.getUid();
                holder.cbStatus.setChecked(true);
            } else {
                holder.cbStatus.setChecked(false);
            }

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    select = position;
                    notifyDataSetChanged();
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
            @BindView(R.id.tv_mobile)
            TextView tvMobile;
            @BindView(R.id.cb_status)
            RadioButton cbStatus;

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


        HttpUtils.getInstance().post(null, "user/general_contact", new AllCallback<ContactListEntity>(ContactListEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(ContactListEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        if (response.getData() != null) {

                            list.clear();
                            list.addAll(response.getData());
                            adapter.notifyDataSetChanged();
                        } else {
                            ToastMessage("暂无就诊人");
                        }
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("数据异常");
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 5002) {
            getData();
        }
    }

    /**
     * 提交数据
     */
    private void subData() {

        if (selectUid <= 0) {

            ToastMessage("请选择联系人");
        }
        Map<String, String> m = new HashMap<>();
        m.put("uid", String.valueOf(selectUid));
        m.put("log_id", String.valueOf(log_id));
        HttpUtils.getInstance().post(m, "order/submit_consultation", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {
                if (response != null) {
                    if (response.getCode() == 200) {
                        Intent intent = new Intent(DoImageTextCallTwoActivity.this, OrderSubSuccessActivity.class);
                        DoImageTextCallTwoActivity.this.startActivity(intent);
                        finish();
                    } else {
                        ToastMessage("数据异常");
                    }
                } else {

                    ToastMessage("数据异常");
                }
            }
        });
    }
}
