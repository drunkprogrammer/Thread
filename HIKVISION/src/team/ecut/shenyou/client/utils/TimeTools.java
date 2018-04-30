package team.ecut.shenyou.client.utils;

import java.text.SimpleDateFormat;

/*
 * Author: Seven
 * Email : cpwu@foxmail.com
 * 2018-04-07 星期六 15:33
 */
public class TimeTools {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String now() {
        return sdf.format(System.currentTimeMillis());
    }

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");

    public static String nowMS() {
        return sdf1.format(System.currentTimeMillis());
    }

    private static SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss:SS");

    public static String nowms() {
        return sdf2.format(System.currentTimeMillis());
    }
}
