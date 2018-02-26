package com.ecuca.cloudhealth.adapter;

import android.content.Context;

import com.ecuca.cloudhealth.Entity.MeiTuanBean;
import com.ecuca.cloudhealth.R;

import java.util.List;


/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class SelectCityAdapter extends CommonAdapter<MeiTuanBean> {
    public SelectCityAdapter(Context context, int layoutId, List<MeiTuanBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final MeiTuanBean cityBean) {
        holder.setText(R.id.tvCity, cityBean.getCity());
    }
}