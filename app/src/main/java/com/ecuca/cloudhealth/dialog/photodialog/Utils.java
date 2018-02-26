package com.ecuca.cloudhealth.dialog.photodialog;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 
 * com.mingzhi.samattendance.framework.Utils
 *
 *
 * @note:
 */
public class Utils {

	public static String getFunctionUrl(int functionId) {
		switch (functionId) {
		case 0x00:
			return "";

		default:
			return "";
		}
	}

	/**
	 * dp杞琾x
	 * 
	 * @param context
	 * @param dp
	 * @return
	 */
	public static int toPx(Context context, int dp) {
		final float scale = context.getResources().getDisplayMetrics().density;
		int px = (int) (dp * scale + 0.5f);
		return px;
	}

	/**
	 * 閫氳繃璧勬簮ID鑾峰緱Drawable瀵硅薄
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	public static Drawable getResourceImage(Context context, int id) {
		return context.getResources().getDrawable(id);
	}

	/**
	 * dip鍊艰浆px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);

	}

	/**
	 * px鍊艰浆dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 鑾峰緱璁惧灞忓箷瀹藉害 鍗曚綅鍍忕礌
	 * 
	 * @param context
	 * @return
	 */
	public static int getDeviceWidthPixels(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 鑾峰緱璁惧灞忓箷楂樺害 鍗曚綅鍍忕礌
	 * 
	 * @param context
	 * @return
	 */
	public static int getDeviceHeightPixels(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	/**
	 * 寰楀埌涓�釜杩涘害鎻愮ず瀵硅瘽妗�
	 * 
	 * @param context
	 * @param msgStr
	 * @return
	 */
	public static ProgressDialog getProgressDialog(Context context,
												   String msgStr) {
		ProgressDialog progressDialog = new ProgressDialog(context);
		progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);// 璁剧疆椋庢牸涓哄渾褰㈣繘搴︽潯
		progressDialog.setMessage(msgStr);// 杩涘害鏉℃樉绀哄唴?
		progressDialog.setIndeterminate(true);// 璁剧疆杩涘害鏉℃槸鍚︿负涓嶆槑?
		progressDialog.setCancelable(true);// 璁剧疆杩涘害鏉℃槸鍚﹀彲浠ユ寜锟�锟斤拷閿彇锟�
		return progressDialog;
	}



	/**
	 * 鑾峰緱涓�釜娑堟伅鎻愮ず妗�
	 * 
	 * @param context
	 * @param msg
	 * @param listener
	 * @return
	 */
	public static Builder getAlertDialog(Context context, String msg,
										 DialogInterface.OnClickListener listener) {
		Builder builder = new Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_info);
		builder.setTitle(msg);// 璁剧疆鏍囬

		builder.setPositiveButton("纭畾", listener);
		builder.setNegativeButton("鍙栨秷", listener);
		return builder;
	}



	/**
	 * 鑾峰緱涓�釜娑堟伅鎻愮ず妗�
	 * 
	 * @param context
	 * @param resId
	 *            鎻愮ず鍐呭浠ヨ祫婧愬舰寮忎紶鍏�
	 * @param listener
	 * @return
	 */
	public static Builder getAlertDialog(Context context, int resId,
										 DialogInterface.OnClickListener listener) {
		String msg = context.getResources().getString(resId);
		return getAlertDialog(context, msg, listener);
	}

	public static Builder getAlertDialog(Context context, int resId) {
		String msg = context.getResources().getString(resId);
		return getAlertDialog(context, msg,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing to do
					}
				});
	}

	public static Builder getAlertDialog(Context context, String msg) {
		return getAlertDialog(context, msg,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// nothing to do
					}
				});
	}

	/**
	 *            <p>
	 *            鏃ユ湡瀛楃涓茶浆鍖栨垚鏃ユ湡绫诲瀷
	 *            </p>
	 */
	public static Date stringToDate(String dateString) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date date = null;
		try {
			date = sdf.parse(dateString);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * @param date
	 *            <p>
	 *            鏃ユ湡杞寲鎴愬瓧绗︿覆
	 *            </p>
	 */
	public static String dateToString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String dateString = sdf.format(date);
		return dateString;

	}

	/**
	 * @param currentDate
	 *            褰撳墠鏃ユ湡
	 * @return <p>
	 *         褰撳墠鏃ユ湡鍓嶄竴澶╂椂鏈熷瓧绗︿覆
	 *         </p>
	 */
	public static String getPreviousDayString(String currentDate) {
		if (TextUtils.isEmpty(currentDate)) {
			return null;
		}
		return dateToString(getPreviousDay(stringToDate(currentDate)));

	}

	/**
	 * @param currentDate
	 *            褰撳墠鏃ユ湡
	 * @return <p>
	 *         褰撳墠鏃ユ湡鍚庝竴澶╂椂鏈熷瓧绗︿覆
	 *         </p>
	 */
	public static String getNextDayString(String currentDate) {
		if (TextUtils.isEmpty(currentDate)) {
			return null;
		}
		return dateToString(getNextDay(stringToDate(currentDate)));

	}

	/**
	 * 鍓嶄竴澶�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getPreviousDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 鍚庝竴澶�
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		date = calendar.getTime();
		return date;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(dm);
		return dm.widthPixels;
	}
	
}
