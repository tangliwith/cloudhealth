package com.ecuca.cloudhealth.activity.me;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.ContactInfoEntity;
import com.ecuca.cloudhealth.Entity.UserInfoEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.DateUtils;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;

import org.feezu.liuli.timeselector.TimeSelector;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2018/1/23.
 */

public class ContactInfoActivity extends BaseActivity {
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.lin_choose_avatar)
    LinearLayout linChooseAvatar;
    @BindView(R.id.et_nick_name)
    TextView etNickName;
    @BindView(R.id.tv_id_card_type)
    TextView tvIdCardType;
    @BindView(R.id.lin_choose_id_card_type)
    LinearLayout linChooseIdCardType;
    @BindView(R.id.et_id_card)
    TextView etIdCard;
    @BindView(R.id.rb_sex_man)
    RadioButton rbSexMan;
    @BindView(R.id.rb_sex_woman)
    RadioButton rbSexWoman;
    @BindView(R.id.tv_birthday)
    TextView tvBirthday;
    @BindView(R.id.lin_choose_birthday)
    LinearLayout linChooseBirthday;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rel_choose_address)
    RelativeLayout relChooseAddress;
    @BindView(R.id.et_address_details)
    TextView etAddressDetails;
    @BindView(R.id.et_mobile)
    TextView etMobile;

    @BindView(R.id.sw_is_default)
    Switch swIsDefault;
    @BindView(R.id.tv_info_status)
    TextView tvInfoStatus;
    @BindView(R.id.lin_choose_info)
    LinearLayout linChooseInfo;
    @BindView(R.id.tv_del)
    TextView tvDel;


    int uid;
    @Override
    protected void setContentView() {

        uid=getIntent().getIntExtra("uid",0);
        setContentView(R.layout.aty_contact_info);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("成员信息");
    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initData() {

        getUserInfo();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {
        linChooseInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent=new Intent(ContactInfoActivity.this,ContactFullInfoActivity.class);
                intent.putExtra("uid",uid);
                ContactInfoActivity.this.startActivityForResult(intent,10000);
            }
        });

        tvDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showAlertDialogMessage("提示", "是否要删除此就诊人？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        delContact();
                    }
                });

            }
        });
    }

    /**
     * 获取用户信息
     */
    private void getUserInfo() {

        Map<String, String> m = new HashMap<>();
        m.put("uid",uid+"");

        HttpUtils.getInstance().post(m, "user/contact_user_info", new AllCallback<ContactInfoEntity>(ContactInfoEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
                finish();

            }

            @Override
            public void onResponse(ContactInfoEntity response) {

                if (response != null) {

                    if (response.getCode() == 200) {

                        setUserInfo(response.getData());
                    } else {
                        ToastMessage(response.getMsg());
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
     * 设置用户信息
     *
     * @param bean
     */
    private void setUserInfo(ContactInfoEntity.DataBean bean) {

        GlideUtils.LoadImg(imgHead, bean.getAvatar_url());
        etNickName.setText(bean.getNick_name() == null ? "" : bean.getNick_name());
        tvIdCardType.setText( bean.getCard_type()==1?"大陆身份证":"军官证");
       etIdCard.setText(bean.getCard_no()==null?"":bean.getCard_no());

        if (bean.getSex() == 1) {
            rbSexMan.setChecked(true);
            rbSexWoman.setChecked(false);
        } else {
            rbSexMan.setChecked(false);
            rbSexWoman.setChecked(true);
        }
        tvBirthday.setText(bean.getBirthday() == null ? "" : bean.getBirthday());


        String address = "";
        if (bean.getProvince() != null) {

            address += bean.getProvince().getName_chs() + " ";
        }
        if (bean.getCity() != null) {

            address += bean.getCity().getName_chs() + " ";
        }
        if (bean.getArea() != null) {

            address += bean.getArea().getName_chs() + " ";
        }
        tvAddress.setText(address);
        etAddressDetails.setText(bean.getAddress() == null ? "" : bean.getAddress());
        etMobile.setText(bean.getMobile()==null?"":bean.getMobile());
        swIsDefault.setChecked(bean.getIs_default()==1?true:false);
        if(bean.getFull_information()==1){
            tvInfoStatus.setText("已经完善");

        }
        else{
            tvInfoStatus.setText("未完善");
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getUserInfo();
    }

    /**
     * 删除联系人
     */
    private void delContact(){



        Map<String,String> m=new HashMap<>();
        m.put("uid",uid+"");
        HttpUtils.getInstance().post(m, "user/del_contact_user", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {
                if(response!=null){

                    if(response.getCode()==200){
                        Intent intent=new Intent();
                        setResult(5002,intent);
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("删除失败");
                }
            }
        });
    }
}
