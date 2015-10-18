package cn.andthink.actionlibrary.utils;

import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by wuhaiyang on 2015/7/18.
 */
public class FileUtil {

    private static final String ROOT = "AppliactionName";

    private static final String CACHE = "cache";//缓存应用程序数据的文件夹
    private static final String IMAGE = "image"; //缓存应用程序的图片的文件夹
    //  根据业务需求可以添加更多的缓存文件夹
    private static final String CAMERA_TEMP = "camera_pic_temp"; // 缓存拍照临时存储的图片

    public static File getDir(String str) {
        StringBuilder sb = new StringBuilder();
        if (isSDAvailable()) {
            sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());// /mnt/sdcard
            sb.append(File.separator); // /mnt/sdcard/
            sb.append(ROOT); //  /mnt/sdcard/ApplicationName
            sb.append(File.separator); //  /mnt/sdcard/ApplicationName/
            sb.append(str); // /mnt/sdcard/ApplicationName/str
        } else {
            File cacheDir = UiUtils.getContext().getCacheDir();
            sb.append(cacheDir.getAbsolutePath());// /data/data/包名/cache
            sb.append(File.separator);
            sb.append(str);
        }
        File file = new File(sb.toString());
        if (!file.exists() || !file.isDirectory()) {
            file.mkdirs(); //创建文件夹
        }
        return file;
    }

    public static File getCacheDir() {
        return getDir(CACHE);
    }

    public static File getImageDir() {
        return getDir(IMAGE);
    }

    public static File getCameraPicFile() {
        File dir = getDir(CAMERA_TEMP);
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String fileName = "multi_image_" + timeStamp + "";
        File file = new File(dir, fileName + ".jpg");
        return file;
    }


    public static boolean isSDAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
}
