package cn.andthink.actionlibrary.utils;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.lidroid.xutils.BitmapUtils;

/**
 * Created by wuhaiyang on 2015/7/18.
 * 加载图片工具
 */
public class BitmapHelper {
    private static BitmapUtils mInstance;
    private BitmapHelper() {

    }

    public static BitmapUtils getInstance() {
        if (null == mInstance) {
            mInstance = new BitmapUtils(UiUtils.getContext(), FileUtil.getImageDir().getAbsolutePath(), 0.5f, 5 * 1024 * 1024);
            mInstance.configDefaultLoadingImage(new ColorDrawable(Color.parseColor("#f5f5f5")));//加载中的图片
        }
        return mInstance;
    }

    public static void clearMemoryCache() {
        if (null != mInstance) {
            mInstance.clearMemoryCache();
        }
    }


}
