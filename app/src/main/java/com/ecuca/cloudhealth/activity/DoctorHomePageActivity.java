package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.DoctorHomePageEntity;
import com.ecuca.cloudhealth.Entity.FollowDoctorEntity;
import com.ecuca.cloudhealth.Entity.InitOrderInfoEntity;
import com.ecuca.cloudhealth.Entity.UserFullInFoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.doctor.CreateOrderActivity;
import com.ecuca.cloudhealth.activity.doctor.DoctorInfoActivity;
import com.ecuca.cloudhealth.activity.doctor.ErrorCorrectionActivity;
import com.ecuca.cloudhealth.activity.doctor.ImageTextCallActivity;
import com.ecuca.cloudhealth.activity.doctor.VideoCallActivity;
import com.ecuca.cloudhealth.activity.doctor.VideoLectureActivity;
import com.ecuca.cloudhealth.activity.doctor.VoiceCallActivity;
import com.ecuca.cloudhealth.activity.me.UserInfoActivity;
import com.ecuca.cloudhealth.dialog.ChooseConsultationTypeDialog;
import com.ecuca.cloudhealth.dialog.MessageDialog;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/24.
 */

public class DoctorHomePageActivity extends BaseActivity {


    @BindView(R.id.id_flowlayout)
    TagFlowLayout tagFlowLayout;

    @BindView(R.id.list_view)
    RecyclerView mBottomListView;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_fix_error)
    TextView tvFixError;
    @BindView(R.id.tv_video)
    TextView tvVideo;
    @BindView(R.id.tv_reception_num)
    TextView tvReceptionNum;
    @BindView(R.id.tv_invite_num)
    TextView tvInviteNum;
    @BindView(R.id.tv_evaluate_num)
    TextView tvEvaluateNum;

    @BindView(R.id.tv_work_address)
    TextView tvWorkAddress;
    @BindView(R.id.tv_work_type)
    TextView tvWorkType;
    @BindView(R.id.tv_be_good_at)
    TextView tvBegoodAt;
    @BindView(R.id.rel_all_eve)
    RelativeLayout relAllEve;
    @BindView(R.id.pb_eve_1)
    ProgressBar pbEve1;
    @BindView(R.id.tv_eve_1)
    TextView tvEve1;
    @BindView(R.id.pb_eve_2)
    ProgressBar pbEve2;
    @BindView(R.id.tv_eve_2)
    TextView tvEve2;
    @BindView(R.id.tv_call_doctor)
    TextView tvCallDoctor;

    @BindView(R.id.tv_follow)
    TextView tvFollow;
    @BindView(R.id.lin_follow)
    LinearLayout linFollow;
    @BindView(R.id.img_follow)
    ImageView imgFollow;
    MessageDialog noAuthDialog;
    MessageDialog userInfoDialog;
    ChooseConsultationTypeDialog chooseConsultationTypeDialog;
    List<DoctorHomePageEntity.DataBean.EvaluateListBean> list;
    MyAdapter adapter;
    int doctor_id;

    int isFullInfo;

    @Override
    protected void setContentView() {

        doctor_id = getIntent().getIntExtra("doctor_id", 0);
        setContentView(R.layout.aty_doctor_home_page);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("医生主页");
    }

    @Override
    protected void initUI() {


        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mBottomListView.setLayoutManager(manager);
        list = new ArrayList<>();
        adapter = new MyAdapter(list);
        mBottomListView.setAdapter(adapter);
    }

    @Override
    protected void initData() {

        List<String> mVals = new ArrayList<>();
        final LayoutInflater mInflater = getLayoutInflater();
        mVals.add("全部(23)");
        mVals.add("直肠癌(5)");
        mVals.add("乳腺癌(3)");
        mVals.add("恶性肿瘤所致贫血(12)");
        mVals.add("淋巴瘤(1)");
        mVals.add("其他(1)");
        tagFlowLayout.setAdapter(new TagAdapter<String>(mVals) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {

                TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_str,
                        tagFlowLayout, false);
                tv.setText(s);
                if (position == 0) {
                    tv.setTextColor(getResources().getColor(R.color.white));
                    tv.setBackgroundResource(R.drawable.shape_eve_tv_en_bg);
                } else {
                    tv.setTextColor(getResources().getColor(R.color.app_title_top));
                    tv.setBackgroundResource(R.drawable.shape_eve_tv_un_bg);
                }

                return tv;
            }
        });
        tagFlowLayout.refreshDrawableState();
        tagFlowLayout.requestLayout();
        getIsFollow();
        getData();


    }

    @Override
    protected void initEvent() {

        noAuthDialog = new MessageDialog(DoctorHomePageActivity.this, R.style.transparentFrameWindowStyle, new MessageDialog.OnMessageListener() {
            @Override
            public void onClickOk() {
                //去个人中心完成VIP充值

            }

            @Override
            public void onClickClose() {


            }
        });

        userInfoDialog = new MessageDialog(this, R.style.transparentFrameWindowStyle, new MessageDialog.OnMessageListener() {
            @Override
            public void onClickOk() {

                Intent intent = new Intent(DoctorHomePageActivity.this, UserInfoActivity.class);
                intent.putExtra("is_full",true);
                DoctorHomePageActivity.this.startActivity(intent);


            }

            @Override
            public void onClickClose() {

                finish();
            }
        });
        //选择了咨询类型
        chooseConsultationTypeDialog = new ChooseConsultationTypeDialog(DoctorHomePageActivity.this, R.style.transparentFrameWindowStyle1, new ChooseConsultationTypeDialog.OnChooseConsultationTypeDialogListener() {
            @Override
            public void onClick(int type) {

                createOrderId(type);

            }
        });



    }

    @Override
    protected void startFunction() {

        //医生简介
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHomePageActivity.this, DoctorInfoActivity.class);
                intent.putExtra("doctor_id", doctor_id);
                DoctorHomePageActivity.this.startActivity(intent);
            }
        });
        //纠错
        tvFixError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHomePageActivity.this, ErrorCorrectionActivity.class);
                intent.putExtra("doctor_id", doctor_id);
                DoctorHomePageActivity.this.startActivity(intent);
            }
        });

        //视频讲座
        tvVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoctorHomePageActivity.this, VideoLectureActivity.class);
                DoctorHomePageActivity.this.startActivity(intent);
            }
        });
        tvCallDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isFullInfo == 0) {
                    ToastMessage("请完善个人信息");
                    return;
                }

                chooseConsultationTypeDialog.show();
//                noAuthDialog.show("会员才能免费，\n" +
//                        "是否开通VIP会员?");


            }
        });

        linFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvFollow.getText().toString().trim().equals("关注")) {
                    doFollow();
                } else {
                    closeFollow();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        getUserFullInfo();
        super.onResume();
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        List<DoctorHomePageEntity.DataBean.EvaluateListBean> list;

        public MyAdapter(List<DoctorHomePageEntity.DataBean.EvaluateListBean> list) {
            this.list = list;
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctor_home_page, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
            DoctorHomePageEntity.DataBean.EvaluateListBean bean = list.get(position);

            if (bean.getUid() != null)
                holder.tvName.setText(bean.getUid().getNick());
            holder.tvDate.setText(DateUtils.data(bean.getAdd_time()));
            if (bean.getEffect() > 0) {
                holder.tvNum1.setText(bean.getEffect() + "");
            } else {
                holder.tvNum1.setText("0");
            }

            if (bean.getAttitude() > 0) {
                holder.tvNum2.setText(bean.getAttitude() + "");
            } else {
                holder.tvNum2.setText("0");
            }

            holder.tvContent.setText(bean.getSuggest() == null ? "" : bean.getSuggest());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.tv_type)
            TextView tvType;
            @BindView(R.id.tv_date)
            TextView tvDate;
            @BindView(R.id.tv_name)
            TextView tvName;
            @BindView(R.id.tv_num_1)
            TextView tvNum1;
            @BindView(R.id.tv_num_2)
            TextView tvNum2;
            @BindView(R.id.tv_content)
            TextView tvContent;

            ViewHolder(View view) {
                super(view);
                ButterKnife.bind(this, view);
            }
        }
    }

    private void getData() {

        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", String.valueOf(doctor_id));
        HttpUtils.getInstance().post(m, "doctor/doctors_homepage", new AllCallback<DoctorHomePageEntity>(DoctorHomePageEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(DoctorHomePageEntity response) {

                if (response != null) {

                    if (response.getData() != null) {
                        setData(response.getData());
                    } else {
                        ToastMessage("获取信息失败");
                        finish();
                    }
                } else {
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });
    }


    /**
     * 设置数据
     *
     * @param bean
     */
    private void setData(DoctorHomePageEntity.DataBean bean) {

        setTitleText("医生主页" + (bean.getNick_name() == null ? "" : ("-" + bean.getNick_name())));
        GlideUtils.LoadImg(imgHead, bean.getAvatar_url());
        tvName.setText(bean.getNick_name() == null ? "" : bean.getNick_name());
        if (bean.getCate() != null)
            tvLevel.setText(bean.getCate().getTitle() == null ? "" : bean.getCate().getTitle());


        tvReceptionNum.setText(bean.getReception() + "");
        tvInviteNum.setText(bean.getInvite_count() + "");
        if (bean.getEvaluate() > 0) {
            tvEvaluateNum.setText(bean.getEvaluate() + "");
        } else {
            tvEvaluateNum.setText("0");
        }


        if (bean.getHospital() != null)
            tvWorkAddress.setText(bean.getHospital().getHospital_name() == null ? "" : bean.getHospital().getHospital_name());

        if (bean.getSection() != null)
            tvWorkType.setText(bean.getSection().getTitle() == null ? "" : bean.getSection().getTitle());

        tvBegoodAt.setText(bean.getProfession() == null ? "" : bean.getProfession());


        pbEve1.setProgress((int) (bean.getEffect_score() * 20));
        pbEve2.setProgress((int) (bean.getAttitude_score() * 20));

        if (bean.getEffect_score() > 0) {
            tvEve1.setText("" + bean.getEffect_score());
        } else {
            tvEve1.setText("0");
        }
        if (bean.getAttitude_score() > 0) {
            tvEve2.setText("" + bean.getAttitude_score());
        } else {
            tvEve2.setText("0");
        }


        if (bean.getEvaluate_list() != null) {
            list.clear();
            list.addAll(bean.getEvaluate_list());
            adapter.notifyDataSetChanged();
        }

    }


    /**
     * 判断是否关注医生
     */
    private void getIsFollow() {

        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", String.valueOf(doctor_id));
        HttpUtils.getInstance().post(m, "doctor/is_following", new AllCallback<FollowDoctorEntity>(FollowDoctorEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(FollowDoctorEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        imgFollow.setVisibility(response.getData().getStatus() == 1 ? View.GONE : View.VISIBLE);
                        tvFollow.setText(response.getData().getStatus() == 1 ? "取消关注" : "关注");
                    }
                } else {
                    ToastMessage("数据异常");
                }
            }
        });

    }

    /**
     * 关注医生
     */
    private void doFollow() {

        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", String.valueOf(doctor_id));
        HttpUtils.getInstance().post(m, "doctor/following_doctor", new AllCallback<FollowDoctorEntity>(FollowDoctorEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(FollowDoctorEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        ToastMessage(response.getMsg());
                        getIsFollow();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("数据异常");
                }
            }
        });

    }

    /**
     * 取消关注医生
     */
    private void closeFollow() {

        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", String.valueOf(doctor_id));
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
                        getIsFollow();
                    } else {
                        ToastMessage(response.getMsg());
                    }
                } else {
                    ToastMessage("数据异常");
                }
            }
        });

    }

    /**
     * 检查用户是否完善个人信息
     */
    private void getUserFullInfo() {

        HttpUtils.getInstance().post(null, "user/is_full_file", new AllCallback<UserFullInFoEntity>(UserFullInFoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(UserFullInFoEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        //未完善信息
                        if (response.getData().getStatus() == 0) {
                            isFullInfo = 0;
                            userInfoDialog.show("需要完善个人信息后才能咨询", "不问啦", "去完善");
                        } else {
                            isFullInfo = 1;
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


    /**
     * 获取订单信息
     */
    private void createOrderId(final int type) {


        Map<String, String> m = new HashMap<>();
        m.put("doctor_id", doctor_id + "");
        m.put("type", String.valueOf(type));
        HttpUtils.getInstance().post(m, "order/consult_now", new AllCallback<InitOrderInfoEntity>(InitOrderInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(InitOrderInfoEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {
                        if (response.getData() != null) {
                            //不免费
                            if (response.getData().getIs_free() == 0) {
                                Intent intent = new Intent(DoctorHomePageActivity.this, CreateOrderActivity.class);
                                intent.putExtra("type", type);
                                intent.putExtra("doctor_id", doctor_id);
                                intent.putExtra("order_id", response.getData().getOrder_id());
                                DoctorHomePageActivity.this.startActivity(intent);
                            }
                            //免费
                            else {
                                if (type == 1) {
                                    Intent intent = new Intent(DoctorHomePageActivity.this, ImageTextCallActivity.class);
                                    intent.putExtra("doctor_id", doctor_id);
                                    intent.putExtra("order_id", response.getData().getOrder_id());
                                    DoctorHomePageActivity.this.startActivity(intent);
                                } else if (type == 2) {
                                    Intent intent = new Intent(DoctorHomePageActivity.this, VoiceCallActivity.class);
                                    intent.putExtra("doctor_id", doctor_id);
                                    intent.putExtra("order_id", response.getData().getOrder_id());
                                    DoctorHomePageActivity.this.startActivity(intent);
                                } else if (type == 3) {
                                    Intent intent = new Intent(DoctorHomePageActivity.this, VideoCallActivity.class);
                                    intent.putExtra("doctor_id", doctor_id);
                                    intent.putExtra("order_id", response.getData().getOrder_id());
                                    DoctorHomePageActivity.this.startActivity(intent);
                                }
                            }
                        } else {
                            ToastMessage("创建订单失败");
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
}
