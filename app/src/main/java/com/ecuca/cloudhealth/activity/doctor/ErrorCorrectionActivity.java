package com.ecuca.cloudhealth.activity.doctor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.BaseEntity;
import com.ecuca.cloudhealth.Entity.DoctorDetailsEntity;
import com.ecuca.cloudhealth.HttpUtils.AllCallback;
import com.ecuca.cloudhealth.HttpUtils.HttpUtils;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.BaseActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;

/**
 * Created by tuhualong on 2017/12/25.
 */

public class ErrorCorrectionActivity extends BaseActivity {

    @BindView(R.id.id_flowlayout)
    TagFlowLayout tagFlowLayout;
    @BindView(R.id.img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_level)
    TextView tvLevel;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.et_mobile)
    EditText etMobile;
    @BindView(R.id.tv_sub)
    TextView tvSub;


    int doctor_id;
    @Override
    protected void setContentView() {

        doctor_id=getIntent().getIntExtra("doctor_id",0);
        setContentView(R.layout.aty_error_correction);
        ButterKnife.bind(this);
        showTitleBack();
        setTitleText("纠错");
    }

    @Override
    protected void initUI() {

    }

    List<SelectStrEntity> mVals = new ArrayList<>();
    LayoutInflater mInflater;
    MyAdapter adapter;

    @Override
    protected void initData() {
        mInflater=getLayoutInflater();

        mVals.add(new SelectStrEntity("科室错误", false));
        mVals.add(new SelectStrEntity("职称错误", false));
        mVals.add(new SelectStrEntity("已不再此医院就诊", false));
        mVals.add(new SelectStrEntity("擅长简介出错", false));
        mVals.add(new SelectStrEntity("医生姓名错别字", false));
        mVals.add(new SelectStrEntity("医生照片错误", false));
        adapter = new MyAdapter(mVals);
        tagFlowLayout.setAdapter(adapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {



                mVals.get(position).setCheck(!mVals.get(position).isCheck);
                adapter.notifyDataChanged();
                return false;
            }
        });
        tagFlowLayout.refreshDrawableState();
        tagFlowLayout.requestLayout();
        getData();
    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void startFunction() {



        tvSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                subData();
            }
        });
    }


    class SelectStrEntity {
        private String title;
        private boolean isCheck;

        public SelectStrEntity(String title, boolean isCheck) {
            this.title = title;
            this.isCheck = isCheck;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }


    class MyAdapter extends TagAdapter<SelectStrEntity> {


        public MyAdapter(List<SelectStrEntity> datas) {
            super(datas);
        }

        @Override
        public View getView(FlowLayout parent, int position, final SelectStrEntity s) {

            TextView tv = (TextView) mInflater.inflate(R.layout.item_tag_str,
                    tagFlowLayout, false);
            tv.setText(s.getTitle());
            if (s.isCheck) {
                tv.setTextColor(getResources().getColor(R.color.white));
                tv.setBackgroundResource(R.drawable.shape_choosr_tv_en_bg);
            } else {
                tv.setTextColor(getResources().getColor(R.color.app_title_top));
                tv.setBackgroundResource(R.drawable.shape_choosr_tv_un_bg);
            }

            parent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            return tv;
        }


    }


    /**
     * 提交数据
     */
    private void subData(){


        String error_type="";

        for (SelectStrEntity s:mVals){

            if(s.isCheck){
                error_type+=s.getTitle()+",";
            }
        }
        if(error_type.length()>0){
            error_type=error_type.substring(0,error_type.length()-1);
        }
        if(TextUtils.isEmpty(error_type)){
            ToastMessage("请选择错误类型");
            return;
        }

        String message=etContent.getText().toString().trim();


        String userName=etUserName.getText().toString().trim();
        if(TextUtils.isEmpty(userName)){
            ToastMessage("请输入联系人姓名");
            return;
        }
        String mobile=etMobile.getText().toString().trim();

        if(TextUtils.isEmpty(mobile)){
            ToastMessage("请输入联系人电话");
            return;
        }

        Map<String,String> m=new HashMap<>();
        m.put("doctor_id",doctor_id+"");
        m.put("error_type",error_type);
        m.put("message",message);
        m.put("username",userName);
        m.put("mobile",mobile);
        HttpUtils.getInstance().post(m, "doctor/add_error_correction_logs", new AllCallback<BaseEntity>(BaseEntity.class) {
            @Override
            public void onError(Call call, Exception e) {

                ToastMessage("网络异常");
            }

            @Override
            public void onResponse(BaseEntity response) {

                if(response!=null){

                    if(response.getCode()==200){
                        ToastMessage("提交成功");
                        finish();
                    }
                    else{
                        ToastMessage(response.getMsg());
                    }
                }
                else{
                    ToastMessage("提交失败");
                }
            }
        });
    }

    private void getData(){

        Map<String,String> m=new HashMap<>();
        m.put("doctor_id",String.valueOf(doctor_id));
        HttpUtils.getInstance().post(m, "doctor/doctor_detail", new AllCallback<DoctorDetailsEntity>(DoctorDetailsEntity.class) {
            @Override
            public void onError(Call call, Exception e) {
                ToastMessage("网络异常");
                finish();
            }

            @Override
            public void onResponse(DoctorDetailsEntity response) {

                if(response!=null){

                    if(response.getCode()==200){

                        if(response.getData()!=null){

                            setData(response.getData());
                        }
                        else{
                            ToastMessage("获取资料失败");
                            finish();
                        }
                    }
                    else{
                        ToastMessage(response.getMsg());
                        finish();
                    }
                }
                else{
                    ToastMessage("数据异常");
                    finish();
                }
            }
        });
    }
    /**
     * 设置数据
     * @param bean
     */
    private void setData(DoctorDetailsEntity.DataBean bean){

        if(bean!=null){
            GlideUtils.LoadImg(imgHead,bean.getAvatar_url());
            tvName.setText(bean.getNick_name()==null?"":bean.getNick_name());
            if(bean.getCate()!=null)
                tvLevel.setText(bean.getCate().getTitle()==null?"":bean.getCate().getTitle());
            String content="";
            if(bean.getHospital()!=null){
                content=bean.getHospital().getHospital_name();

            }
            if(bean.getSection()!=null){
                content=content+" "+bean.getSection().getTitle();
            }
            tvAddress.setText(content);
        }
        else{
            finish();
        }
    }

}
