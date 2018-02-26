package com.ecuca.cloudhealth.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.ecuca.cloudhealth.MyApplication;
import com.ecuca.cloudhealth.R;
import com.ecuca.cloudhealth.adapter.GuideViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tuhualong on 2018/1/5.
 */

public class WelcomeGuideActivity  extends BaseActivity implements View.OnClickListener{


    @BindView(R.id.vp_guide)
    ViewPager vpGuide;
    @BindView(R.id.activity_welcome_guide)
    RelativeLayout activityWelcomeGuide;

    private GuideViewPagerAdapter adapter;
    private List<View> views;

    Button startBtn;

    // 引导页图片资源
    private static final int[] pics = { R.layout.guide_view1,
            R.layout.guide_view2};

    // 底部小点图片
    private ImageView[] dots;

    // 记录当前选中位置
    private int currentIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_welcome_guide);
        ButterKnife.bind(this);
        views = new ArrayList<View>();

        // 初始化引导页视图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);

            if (i == pics.length - 1) {
//                startBtn = (Button) view.findViewById(R.id.btn_enter);
//                startBtn.setTag("enter");
//                startBtn.setOnClickListener(this);
            }
            views.add(view);
        }

        adapter = new GuideViewPagerAdapter(views);
        vpGuide.setAdapter(adapter);
        vpGuide.addOnPageChangeListener(new PageChangeListener());

    }

    /**
     * 设置当前view
     *
     * @param position
     */
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        vpGuide.setCurrentItem(position);
    }

    @Override
    public void onClick(View v) {
        if (v.getTag().equals("enter")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }else{
            int position = (Integer) v.getTag();
            setCurView(position);
        }
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrollStateChanged(int position) {
        }

        @Override
        public void onPageScrolled(int position, float arg1, int arg2) {

        }

        @Override
        public void onPageSelected(int position) {
            // 设置底部小点选中状态
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        MyApplication.getInstance().setSharedPreferences("is_first","yyyy");

    }

    @Override
    protected void setContentView() {

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

    }
}
