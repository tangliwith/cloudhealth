package com.ecuca.cloudhealth.dialog.photodialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


import com.ecuca.cloudhealth.R;

import java.util.ArrayList;


@SuppressLint("InflateParams")
public class AlertChooser extends Dialog {

	private Context context;

	protected AlertChooser(Context context) {
		super(context, R.style.AlertChooser);
		this.context = context;
	}

	protected AlertChooser(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Window window = this.getWindow();
		WindowManager.LayoutParams params = window.getAttributes();
		int marginBorder = Utils.dip2px(context, 10);
		params.width = Utils.getScreenWidth(context) - marginBorder * 2;
		params.gravity = Gravity.BOTTOM;
		params.y = 20;
		window.setAttributes(params);
	}

	public static class Builder {
		private Context context;
		private AlertChooser chooser;
		private CharSequence title;
		private CharSequence negativeItemText;
		private OnItemClickListener negativeItemListener;
		private ArrayList<CharSequence> itemTextList;
		private ArrayList<OnItemClickListener> itemClickListenerList;
		private boolean cancelable = true;
		private boolean canceledOnTouchOutside = true;

		public Builder(Context context) {
			chooser = new AlertChooser(context);
			this.context = context;
			init();
		}

		public Builder(Context context, int theme) {
			chooser = new AlertChooser(context, theme);
			this.context = context;
			init();
		}

		private void init() {
			itemTextList = new ArrayList<CharSequence>();
			itemClickListenerList = new ArrayList<OnItemClickListener>();
		}

		public Builder setTitle(CharSequence title) {
			this.title = title;
			return this;
		}

		public Builder setTitle(int titleRes) {
			this.title = context.getResources().getString(titleRes);
			return this;
		}

		public Builder addItem(CharSequence itemText, OnItemClickListener lis) {
			this.itemTextList.add(itemText);
			this.itemClickListenerList.add(lis);
			return this;
		}

		public Builder addItem(int itemTextRes, OnItemClickListener lis) {
			this.itemTextList
					.add(context.getResources().getString(itemTextRes));
			this.itemClickListenerList.add(lis);
			return this;
		}

		public Builder setNegativeItem(CharSequence text,
				OnItemClickListener lis) {
			this.negativeItemText = text;
			this.negativeItemListener = lis;
			return this;
		}

		public Builder setNegativeItem(int textRes, OnItemClickListener lis) {
			this.negativeItemText = context.getResources().getString(textRes);
			this.negativeItemListener = lis;
			return this;
		}

		public Builder setCancelable(boolean cancelable) {
			this.cancelable = cancelable;
			return this;
		}

		public Builder setCanceledOnTouchOutside(boolean canceled) {
			this.canceledOnTouchOutside = canceled;
			return this;
		}

		public AlertChooser create() {
			if (chooser == null)
				return null;

			View iPanel = LayoutInflater.from(context).inflate(
					R.layout.fynn_alertchooser_normal, null);
			TextView iTitle = (TextView) iPanel.findViewById(R.id.title);
			TextView iCancel = (TextView) iPanel.findViewById(R.id.cancel);
            LinearLayout iContent=(LinearLayout)iPanel.findViewById(R.id.content_layout);

			if (title != null) {
				iTitle.setVisibility(View.VISIBLE);
				iTitle.setText(title);
			} else {
				iTitle.setVisibility(View.GONE);
			}

			if (negativeItemText != null) {
				iCancel.setVisibility(View.VISIBLE);
				iCancel.setText(negativeItemText);
				iCancel.setTextSize(17);

				if (negativeItemListener != null) {
					iCancel.setOnClickListener(new View.OnClickListener() {

						@Override
						public void onClick(View v) {
							negativeItemListener.OnItemClick(chooser);
						}
					});
				}
			} else {
				iCancel.setVisibility(View.GONE);
			}

			if (!itemTextList.isEmpty()) {
				for (int i = 0; i < itemTextList.size(); i++) {
					CharSequence text = itemTextList.get(i);
					final OnItemClickListener lis = itemClickListenerList
							.get(i);

					View v = new View(context);
					v.setLayoutParams(new LayoutParams(
							LayoutParams.MATCH_PARENT, 1));
					v.setBackgroundColor(Color.parseColor("#CCDCDCDC"));

					TextView tv = new TextView(context);
					LayoutParams params = new LayoutParams(
							LayoutParams.MATCH_PARENT,
							Utils.dip2px(context, 40));
					tv.setLayoutParams(params);
					tv.setGravity(Gravity.CENTER);
					tv.setClickable(true);
					tv.setText(text);
					tv.setTextSize(17);
					tv.setTextColor(Color.parseColor("#007FFF"));
					if (lis != null) {
						tv.setOnClickListener(new View.OnClickListener() {

							@Override
							public void onClick(View v) {
								lis.OnItemClick(chooser);
							}
						});
					}

					setItemBackgroundResource(tv, i);

					int padding = Utils.dip2px(context, 10);
					tv.setPadding(padding, padding, padding, padding); // 必须放在setBackground方法后调用，否则无效

					if (title != null) {
						iContent.addView(v);
					} else if (i != 0) {
						iContent.addView(v);
					}

					iContent.addView(tv);
				}

			} else {
				iContent.setVisibility(View.GONE);
			}

			chooser.setCancelable(cancelable);
			chooser.setCanceledOnTouchOutside(canceledOnTouchOutside);

			chooser.setContentView(iPanel);
			return chooser;
		}

		private void setItemBackgroundResource(TextView textView, int index) {
			if (title == null) {
				if (itemTextList.size() == 1)
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_single_selector);
				else if (index == 0)
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_top_selector);
				else if (index == itemTextList.size() - 1)
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_bottom_selector);
				else
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_single_selector);

			} else {
				if (itemTextList.size() == 1)
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_bottom_selector);
				else if (index == itemTextList.size() - 1)
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_bottom_selector);
				else
					textView.setBackgroundResource(R.drawable.fynn_alertchooser_item_middle_selector);
			}
		}

		public AlertChooser show() {
			create().show();
			return chooser;
		}
	}

	public interface OnItemClickListener {
		   void OnItemClick(AlertChooser chooser);
	}

}