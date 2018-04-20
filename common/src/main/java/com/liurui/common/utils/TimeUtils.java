package com.liurui.common.utils;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by LiuRui on 2018/4/17.
 */

public class TimeUtils {
    private static String CurrentTime;
    private static String CurrentDate;

    public static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat formatter_noYear = new SimpleDateFormat("MM-dd HH:mm:ss");
    public static SimpleDateFormat formatter_YearMonthDay = new SimpleDateFormat("yyyy-MM-dd");
    public static SimpleDateFormat formatter_onlyMonthDay = new SimpleDateFormat("MM-dd");
    public static SimpleDateFormat formatter_onlyHourMinutes = new SimpleDateFormat("HH:mm");

    public TimeUtils() {
    }

    public static String getCurrentYear() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        return formatter.format(NowDate);
    }

    public static String getCurrentMonth() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM");
        return formatter.format(NowDate);
    }

    public static String getCurrentDay() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd");
        return formatter.format(NowDate);
    }

    public static String getCurrentHoursMinutes() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = formatter_onlyHourMinutes;
        return formatter.format(NowDate);
    }

    public static String getCurrentTime() {
        Date NowDate = new Date();
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static String getCurrentTime2() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        CurrentTime = formatter.format(NowDate);
        return CurrentTime;
    }

    public static Date convertToDate(String date) {
        SimpleDateFormat formatter = formatter_YearMonthDay;

        try {
            return formatter.parse(date);
        } catch (Exception var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String convertToString(Date date) {
        SimpleDateFormat formatter = formatter_YearMonthDay;

        try {
            return formatter.format(date);
        } catch (Exception var3) {
            return null;
        }
    }

    public static String getCurrentTimeAddYear(int addyear) {
        String currentYear = "";
        Date NowDate = new Date();
        currentYear = getCurrentYear();
        currentYear = String.valueOf(Integer.parseInt(getCurrentYear()) + addyear);
        SimpleDateFormat formatter = new SimpleDateFormat("-MM-dd:HH:mm:ss");
        CurrentTime = formatter.format(NowDate);
        return currentYear + CurrentTime;
    }

    public static String getCurrentDate() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = formatter_YearMonthDay;
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    public static String getDate8Bit() {
        Date NowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        CurrentDate = formatter.format(NowDate);
        return CurrentDate;
    }

    public static String addDay(String currentdate, int add_day) {
        GregorianCalendar gc = null;
        SimpleDateFormat formatter = formatter_YearMonthDay;

        try {
            int year = Integer.parseInt(currentdate.substring(0, 4));
            int month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
            int day = Integer.parseInt(currentdate.substring(8, 10));
            gc = new GregorianCalendar(year, month, day);
            gc.add(GregorianCalendar.DAY_OF_MONTH, add_day);
            return formatter.format(gc.getTime());
        } catch (Exception var8) {
            var8.printStackTrace();
            return null;
        }
    }

    public static String getStartDateInPeriod(String period) {
        StringBuffer str = new StringBuffer(period);
        return str.append("01").toString();
    }

    public static String getEndDateInPeriod(String period) {
        String date = "";
        SimpleDateFormat df = formatter_YearMonthDay;
        int year = Integer.parseInt(period.substring(0, 4));
        int month = Integer.parseInt(period.substring(5, 7));
        System.err.println(month);
        Calendar cl = Calendar.getInstance();
        cl.set(year, month - 1, 1);
        cl.add(GregorianCalendar.MONTH, 1);
        cl.add(GregorianCalendar.DAY_OF_MONTH, -1);
        date = df.format(cl.getTime());
        return date;
    }

    public static String convertStr(String str1) {
        if(str1 != null && !str1.equals("")) {
            String result = "";
            result = result + str1.substring(0, 4) + "-";
            result = result + str1.substring(4, 6) + "-";
            result = result + str1.substring(6, 8);
            return result;
        } else {
            return "";
        }
    }

    public static String convert(String str1) {
        if(str1 != null && !str1.equals("")) {
            String[] temp = str1.split("-");
            String result = "";

            for(int i = 0; i < temp.length; ++i) {
                result = result + temp[i];
            }

            return result;
        } else {
            return "";
        }
    }

    public static String convert(long time) {
        Date date = new Date(time);
        return formatter.format(date);
    }

    public static String formatRecentTime(String time) {
        if(null != time && !"".equals(time)) {
            Date commentTime = null;
            Date currentTime = null;
            SimpleDateFormat dfs = formatter;

            try {
                commentTime = dfs.parse(time);
                currentTime = Calendar.getInstance().getTime();
            } catch (ParseException var21) {
                var21.printStackTrace();
            }

            long between = (currentTime.getTime() - commentTime.getTime()) / 1000L;
            long year = between / 31104000L;
            long month = between / 2592000L;
            long week = between / 604800L;
            long day = between / 86400L;
            long hour = between % 86400L / 3600L;
            long minute = between % 3600L / 60L;
            long second = between % 60L / 60L;
            StringBuffer sb = new StringBuffer();
            if(year != 0L) {
                sb.append(year + "年");
                return sb.toString() + "前";
            } else if(month != 0L) {
                sb.append(month + "个月");
                return sb.toString() + "前";
            } else if(week != 0L) {
                sb.append(week + "周");
                return sb.toString() + "前";
            } else if(day != 0L) {
                sb.append(day + "天");
                return sb.toString() + "前";
            } else if(hour != 0L) {
                sb.append(hour + "小时");
                return sb.toString() + "前";
            } else if(minute != 0L) {
                sb.append(minute + "分钟");
                return sb.toString() + "前";
            } else if(second != 0L) {
                sb.append(second + "秒");
                return sb.toString() + "前";
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static String getZhTimeString(String time) {
        String[] str = time.split(":");
        return str.length == 3?Integer.valueOf(str[0]) + "小时" + Integer.valueOf(str[1]) + "分" + Integer.valueOf(str[2]) + "秒":(str.length == 2?Integer.valueOf(str[0]) + "分" + Integer.valueOf(str[1]) + "秒":Integer.valueOf(str[0]) + "秒");
    }

    public static long getTimeMillis(String strTime) {
        SimpleDateFormat dfs = formatter;
        Date currentTime = null;
        long time = -1L;

        try {
            currentTime = dfs.parse(strTime);
            time = currentTime.getTime();
        } catch (ParseException var6) {
            var6.printStackTrace();
        }

        return time;
    }

    public static String getFormatDate(int year, int monthOfYear, int dayOfMonth) {
        DecimalFormat nf = new DecimalFormat("00");
        return year + "-" + nf.format((long)(monthOfYear + 1)) + "-" + nf.format((long)dayOfMonth);
    }

    public static String getFormatTime(int hourOfDay, int minute) {
        DecimalFormat nf = new DecimalFormat("00");
        return nf.format((long)hourOfDay) + ":" + nf.format((long)minute);
    }

    public static String formatLatelyTime(String strTime) {
        if(null != strTime && !"".equals(strTime)) {
            SimpleDateFormat dfs = formatter;
            Date currentTime = null;
            Date commentTime = null;
            String str = null;

            try {
                currentTime = Calendar.getInstance().getTime();
                commentTime = dfs.parse(strTime);
                if(currentTime.getYear() == commentTime.getYear() && currentTime.getMonth() == commentTime.getMonth() && currentTime.getDate() == commentTime.getDate()) {
                    dfs = formatter_onlyHourMinutes;
                    str = dfs.format(commentTime);
                } else {
                    dfs = formatter_YearMonthDay;
                    str = dfs.format(commentTime);
                }
            } catch (ParseException var6) {
                var6.printStackTrace();
            }

            return str;
        } else {
            return "";
        }
    }
}
