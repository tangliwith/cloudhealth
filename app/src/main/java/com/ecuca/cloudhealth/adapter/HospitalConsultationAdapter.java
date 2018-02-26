package com.ecuca.cloudhealth.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.Entity.DoctorListEntity;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.Utils.GlideUtils;
import com.ecuca.cloudhealth.View.CircleImageView;
import com.ecuca.cloudhealth.activity.DoctorHomePageActivity;
import com.ecuca.cloudhealth.activity.doctor.ImageTextCallActivity;
import com.ecuca.cloudhealth.activity.doctor.VideoCallActivity;
import com.ecuca.cloudhealth.activity.doctor.VoiceCallActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2017/12/21.
 */

public class HospitalConsultationAdapter extends RecyclerView.Adapter <HospitalConsultationAdapter.ViewHolder>{

    private Context context;
    List<DoctorListEntity.DataBean> list;

    public HospitalConsultationAdapter(Context context, List<DoctorListEntity.DataBean> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public HospitalConsultationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consultation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HospitalConsultationAdapter.ViewHolder holder, int position) {






        final DoctorListEntity.DataBean bean=list.get(position);
        if(bean!=null){
            holder.tvName.setText(bean.getNick_name());
            if(bean.getCate()!=null){
                holder.tvLevel.setText(bean.getCate().getTitle());
            }
            String content="";
            if(bean.getHospital()!=null){
                content=bean.getHospital().getHospital_name();

            }

            if(bean.getSection()!=null){
                content=content+" "+bean.getSection().getTitle();
            }
            holder.tvContent.setText(content);

            holder.tvSkill.setText("擅长："+(bean.getProfession()==null?"":bean.getProfession()));
            GlideUtils.LoadImg(holder.imgHead,bean.getAvatar_url());
            holder.tvCounsellingNum.setText(String.valueOf(bean.getInvite_count()));

            if(bean.getEvaluate()>0){
                holder.tvEvaluateNum.setText(String.valueOf(bean.getEvaluate()));
            }
            else{
                holder.tvEvaluateNum.setText("0");
            }



            if(bean.getImg_price()>0){

                holder.tvBtn1.setText(bean.getImg_price()+"元/次");
            }
            else{
                holder.tvBtn1.setText("未开通");
            }

            if(bean.getVoice_price()>0){
                holder.imgBtn2.setImageResource(R.mipmap.icon_con_doctor_type_2);
                holder.tvBtn2.setText(bean.getVoice_price()+"元/次");
            }
            else{
                holder.imgBtn2.setImageResource(R.mipmap.icon_con_doctor_type_2_no);
                holder.tvBtn2.setText("未开通");
            }
            if(bean.getVideo_price()>0){
                holder.imgBtn3.setImageResource(R.mipmap.icon_con_doctor_type_3);
                holder.tvBtn3.setText(bean.getVideo_price()+"元/次");
            }
            else{
                holder.imgBtn3.setImageResource(R.mipmap.icon_con_doctor_type_3_no);
                holder.tvBtn3.setText("未开通");
            }


        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DoctorHomePageActivity.class);
                intent.putExtra("doctor_id",bean.getUid());
                context.startActivity(intent);

            }
        });
//        holder.linBtn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,ImageTextCallActivity.class);
//                context.startActivity(intent);
//            }
//        });
//        holder.linBtn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,VoiceCallActivity.class);
//                context.startActivity(intent);
//            }
//        });
//        holder.linBtn3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(context,VideoCallActivity.class);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_head)
        CircleImageView imgHead;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_level)
        TextView tvLevel;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_skill)
        TextView tvSkill;
        @BindView(R.id.tv_counselling_num)
        TextView tvCounsellingNum;
        @BindView(R.id.tv_evaluate_num)
        TextView tvEvaluateNum;
        @BindView(R.id.img_btn_1)
        ImageView imgBtn1;
        @BindView(R.id.tv_btn_1)
        TextView tvBtn1;
        @BindView(R.id.lin_btn1)
        LinearLayout linBtn1;
        @BindView(R.id.img_btn_2)
        ImageView imgBtn2;
        @BindView(R.id.tv_btn_2)
        TextView tvBtn2;
        @BindView(R.id.lin_btn2)
        LinearLayout linBtn2;
        @BindView(R.id.img_btn_3)
        ImageView imgBtn3;
        @BindView(R.id.tv_btn_3)
        TextView tvBtn3;
        @BindView(R.id.lin_btn_3)
        LinearLayout linBtn3;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
