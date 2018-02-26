package com.ecuca.cloudhealth.View;

import android.content.Context;

import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ecuca.cloudhealth.R;


/**
 * Created by Theron on 2017/11/15.
 */

public class AllCallTitleButton extends LinearLayout{



    TextView btn1,btn2,btn3;
    AllCallTitleButtonListener allCallTitleButtonListener;

    public void setAllCallTitleButtonListener(AllCallTitleButtonListener allCallTitleButtonListener) {
        this.allCallTitleButtonListener = allCallTitleButtonListener;
    }

    public AllCallTitleButton(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.view_my_call_title_buttom, this);

        btn1= (TextView) view.findViewById(R.id.tv_btn_1);
        btn2= (TextView) view.findViewById(R.id.tv_btn_2);
        btn3= (TextView) view.findViewById(R.id.tv_btn_3);

        btn1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                btn1.setTextColor(getResources().getColor(R.color.app_title_top));
                btn1.setBackgroundResource(R.drawable.shape_my_call_title_btn_null_bg);

                btn2.setTextColor(getResources().getColor(R.color.white));
                btn2.setBackgroundResource(R.drawable.shape_my_call_title_btn2_ed_bg);

                btn3.setTextColor(getResources().getColor(R.color.white));
                btn3.setBackgroundResource(R.drawable.shape_my_call_title_btn3_ed_bg);

                if(allCallTitleButtonListener!=null){
                    allCallTitleButtonListener.TagChange(0);
                }
            }
        });
        btn2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                btn2.setTextColor(getResources().getColor(R.color.app_title_top));
                btn2.setBackgroundResource(R.drawable.shape_my_call_title_btn_null_bg);

                btn1.setTextColor(getResources().getColor(R.color.white));
                btn1.setBackgroundResource(R.drawable.shape_my_call_title_btn1_ed_bg);

                btn3.setTextColor(getResources().getColor(R.color.white));
                btn3.setBackgroundResource(R.drawable.shape_my_call_title_btn3_ed_bg);
                if(allCallTitleButtonListener!=null){
                    allCallTitleButtonListener.TagChange(1);
                }
            }
        });
        btn3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                btn3.setTextColor(getResources().getColor(R.color.app_title_top));
                btn3.setBackgroundResource(R.drawable.shape_my_call_title_btn_null_bg);

                btn1.setTextColor(getResources().getColor(R.color.white));
                btn1.setBackgroundResource(R.drawable.shape_my_call_title_btn1_ed_bg);

                btn2.setTextColor(getResources().getColor(R.color.white));
                btn2.setBackgroundResource(R.drawable.shape_my_call_title_btn2_ed_bg);
                if(allCallTitleButtonListener!=null){
                    allCallTitleButtonListener.TagChange(2);
                }

            }
        });

    }

    public interface  AllCallTitleButtonListener{
        void TagChange(int position);
    }
}
