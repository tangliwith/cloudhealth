package com.ecuca.cloudhealth.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public abstract class MyBasePagerAdapter<T> extends PagerAdapter {
	public Context mContext;
	public ArrayList<T> mDataList;

	public MyBasePagerAdapter(Context ctx) {
		this.mContext = ctx;
	}

	public void initData(List<T> list) {
		if (mDataList == null) {
			mDataList = new ArrayList<T>();
		}
		mDataList.clear();
		mDataList.addAll(list);
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}
}
