package cn.andthink.actionlibrary.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wuhaiyang on 2015/7/18.
 */
public class TimeUtil {
    public static String timeFormat(long timeMillis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(new Date(timeMillis));
    }
    /**
     * 将时间戳转换为指定标准格式
     * @param time
     * @return
     */
    public static String formatDate(long time) {
        return timeFormat(time, "yyyy-MM-dd");
    }
    public static String formatPhotoDate(String path) {
        File file = new File(path);
        if (file.exists()) {
            long time = file.lastModified();
            return formatDate(time);
        }
        return "1970-01-01";
    }
}
