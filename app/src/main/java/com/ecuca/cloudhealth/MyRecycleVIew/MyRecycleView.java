package com.ecuca.cloudhealth.MyRecycleVIew;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

import com.ecuca.cloudhealth.R;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by Administrator on 2016/6/15.
 */
public class MyRecycleView extends XRecyclerView {
    public MyRecycleView(Context context) {
        super(context);
        init(context);
    }

    public MyRecycleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyRecycleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }
    private  void init(Context context){
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        this.setLayoutManager(layoutManager);
        this.setRefreshProgressStyle(ProgressStyle.BallPulse);
        this.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        this.setArrowImageView(R.drawable.iconfont_downgrey);
    }
}
