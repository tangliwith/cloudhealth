package com.ecuca.cloudhealth.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Administrator on 2016/7/5.
 */
public class DateUtils {


    /**
     *  时间戳转换年月日时分秒
     *
     * @param time
     * @return
     */
    public static String data(String time) {
        long timestamp= Long.parseLong(time);
        return  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(timestamp*1000));

    }
    /**
     * 时间戳转换年月
     *
     * @param time
     * @return
     */
    public static String dataToYmd(String time) {
        long timestamp= Long.parseLong(time);
        return  new SimpleDateFormat("yyyy-MM-dd").format(new Date(timestamp*1000));

    }

    /**
     * 得到指定月的天数
     * */
    public static int getMonthLastDay(int year, int month)
    {
        Calendar a = Calendar.getInstance();
        a.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        a.set(Calendar.YEAR, year);
        a.set(Calendar.MONTH, month - 1);
        a.set(Calendar.DATE, 1);//把日期设置为当月第一天
        a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }


    //返回当前年份
    public static   int getYear()
    {
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        return Integer.parseInt(year);
    }

    //返回当前月份
    public static int getMonth()
    {
        Date date = new Date();
        String month = new SimpleDateFormat("MM").format(date);
        return Integer.parseInt(month);
    }
    //返回当前日
    public static int getDay()
    {
        Date date = new Date();
        String day = new SimpleDateFormat("dd").format(date);
        return Integer.parseInt(day);
    }

    /**
     * 1天
     * 2一
     * 3二
     * 。。。。
     * 7六
     * @return
     */
    public static int getWek(int year,int month,int day){

        Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));
        c.set(Calendar.YEAR, year);//指定年份
        c.set(Calendar.MONTH, month - 1);//指定月份 Java月份从0开始算
        //获取指定年份月份中指定某天是星期几
        c.set(Calendar.DAY_OF_MONTH, day);  //指定日
        return c.get(Calendar.DAY_OF_WEEK);
    }


    //字符串转时间戳
    public static String getTime(String timeString){
        String timeStamp = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date d;
        try{
            d = sdf.parse(timeString);
            long l = d.getTime();
            l=l/1000;
            timeStamp = String.valueOf(l);
        } catch(ParseException e){
            e.printStackTrace();
            return "";
        }
        return timeStamp;
    }

    /**
     * 时间戳转换年月
     *
     * @param time
     * @return
     */
    public static String dataToY(String time) {
        long timestamp= Long.parseLong(time);
        return  new SimpleDateFormat("yyyy").format(new Date(timestamp*1000));

    }
    /**
     * 时间戳转换年月
     *
     * @param time
     * @return
     */
    public static String dataToM(String time) {
        long timestamp= Long.parseLong(time);
        return  new SimpleDateFormat("MM").format(new Date(timestamp*1000));

    }
    /**
     * 时间戳转换年月
     *
     * @param time
     * @return
     */
    public static String dataToD(String time) {
        long timestamp= Long.parseLong(time);
        return  new SimpleDateFormat("dd").format(new Date(timestamp*1000));

    }
}
