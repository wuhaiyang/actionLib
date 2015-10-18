package cn.andthink.actionlibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wuhaiyang on 2015/7/20.
 */
public class SharePrefrenceUtil {
    private static final String PREFERENCE_NAME = "cachePre";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private SharePrefrenceUtil (){
        sharedPreferences = UiUtils.getContext().getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    private static SharePrefrenceUtil mInstance;

    public static SharePrefrenceUtil getInstance() {
        if (null == mInstance) {
            mInstance = new SharePrefrenceUtil();
        }
        return mInstance;
    }


}
