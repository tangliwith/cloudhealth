package com.ecuca.cloudhealth.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.UserBaseInfoEntity;
import com.ecuca.cloudhealth.Entity.UserInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.MyHelpActivity;
import com.ecuca.cloudhealth.activity.SystemSettingActivity;
import com.ecuca.cloudhealth.activity.me.ContactsListActivity;
import com.ecuca.cloudhealth.activity.me.FollowDoctorListActivity;
import com.ecuca.cloudhealth.activity.me.MeNullActivity;
import com.ecuca.cloudhealth.activity.me.MyAllCallListActivity;
import com.ecuca.cloudhealth.activity.me.MyConductOrderActivity;
import com.ecuca.cloudhealth.activity.me.UserInfoActivity;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/18.
 */

public class MeFragment extends BaseFragment {
    @BindView(R.id.tv_avatar)
    CircleImageView tvAvatar;
    @BindView(R.id.tv_user_level)
    TextView tvUserLevel;
    @BindView(R.id.lin_head)
    LinearLayout linHead;
    @BindView(R.id.rel_user_info)
    RelativeLayout relUserInfo;
    @BindView(R.id.tv_my_follow_num)
    TextView tvMyFollowNum;
    @BindView(R.id.lin_my_follow)
    LinearLayout linMyFollow;
    @BindView(R.id.tv_my_contact_num)
    TextView tvMyContactNum;
    @BindView(R.id.lin_my_contact)
    LinearLayout linMyContact;
    @BindView(R.id.tv_my_msg_num)
    TextView tvMyMsgNum;
    @BindView(R.id.lin_my_msg)
    LinearLayout linMyMsg;
    @BindView(R.id.tv_my_wallet)
    TextView tvMyWallet;
    @BindView(R.id.rel_my_wallet)
    RelativeLayout relMyWallet;
    @BindView(R.id.rel_my_user_center)
    RelativeLayout relMyUserCenter;
    @BindView(R.id.rel_my_distribution)
    RelativeLayout relMyDistribution;
    @BindView(R.id.lin_my_service_1)
    LinearLayout linMyService1;
    @BindView(R.id.lin_my_service_2)
    LinearLayout linMyService2;
    @BindView(R.id.lin_my_service_3)
    LinearLayout linMyService3;
    @BindView(R.id.lin_my_service_4)
    LinearLayout linMyService4;
    @BindView(R.id.lin_my_service_5)
    LinearLayout linMyService5;
    @BindView(R.id.lin_my_service_6)
    LinearLayout linMyService6;
    @BindView(R.id.rel_my_setting)
    RelativeLayout relMySetting;
    @BindView(R.id.rel_my_help)
    RelativeLayout relMyHelp;

    @BindView(R.id.tv_nick_name)
    TextView tvNickName;

    @Override
    protected void setContentView() {


        setContentView(R.layout.frg_me);
        setTitleText("个人中心");
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {

        relUserInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(getActivity(), UserInfoActivity.class);
                getActivity().startActivity(intent);
            }
        });

        relMySetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SystemSettingActivity.class);
                getActivity().startActivity(intent);
            }
        });
        relMyHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyHelpActivity.class);
                getActivity().startActivity(intent);
            }
        });

        //我的关注
        linMyFollow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), FollowDoctorListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //联系人列表
        linMyContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ContactsListActivity.class);
                getActivity().startActivity(intent);
            }
        });


        //我的挂号
        linMyService1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(getActivity(), MeNullActivity.class);
                intent.putExtra("title","我的挂号");
                getActivity().startActivity(intent);
            }
        });
        //我的咨询
        linMyService2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyAllCallListActivity.class);
                getActivity().startActivity(intent);
            }
        });
        //我的陪诊
        linMyService3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MyConductOrderActivity.class);
                getActivity().startActivity(intent);
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        getBaseInfo();
    }

    /**
     * 获取用户信息
     */
    private void getBaseInfo() {

        Map<String, String> m = new HashMap<>();

        HttpUtils.getInstance().post(m, "user/my_center", new AllCallback<UserBaseInfoEntity>(UserBaseInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");


            }

            @Override
            public void onResponse(UserBaseInfoEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        setUserInfo(response.getData());
                    }
                }
            }
        });

    }

    /**
     * 设置用户信息
     *
     * @param bean
     */
    private void setUserInfo(UserBaseInfoEntity.DataBean bean) {


        GlideUtils.LoadImg(tvAvatar, bean.getAvatar_url());

        tvMyFollowNum.setText(bean.getFollow_no()+"");
        tvMyContactNum.setText(bean.getChild_no()+"");
        tvMyMsgNum.setText(bean.getTotal_news_number()+"");
        tvMyWallet.setText("¥ "+bean.getMoney());
        if(TextUtils.isEmpty(bean.getNick_name())){
            tvNickName.setText(bean.getUser_name());
        }
        else{
            tvNickName.setText(bean.getNick_name());
        }

        tvUserLevel.setText(bean.getGroup());



    }
}
