package cn.andthink.actionlibrary.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;

import cn.andthink.actionlibrary.BaseApplication;


/**
 * Created by wuhaiyang on 2015/7/9.
 */
public class UiUtils {
    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }


    public static int dip2px(int dip) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    public static int px2dip(int px) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    public static Context getContext() {
        return BaseApplication.getApplication();
    }

    /**
     * 把runable 提交到主线程中运行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        if (android.os.Process.myTid() == BaseApplication.getMainTid()) {
            //运行在主线程中
            runnable.run();
        } else {
            BaseApplication.getHandler().post(runnable);
        }
    }

    private static Resources getResources() {
        return BaseApplication.getApplication().getResources();
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, null);
    }

    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }


    public static void postDelayed(Runnable autoTunTask, int time) {
        BaseApplication.getHandler().postDelayed(autoTunTask, time);
    }

    public static void cancel(Runnable autoTunTask) {
        BaseApplication.getHandler().removeCallbacks(autoTunTask);
    }

    /*public static void startActivity(Intent intent) {
        if (BaseActivity.activity == null) {
            //如果不在activity里面去打开activity 需要指定任务栈 需要设置标签
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getContext().startActivity(intent);
        } else {
            BaseActivity.activity.startActivity(intent);
        }
    }*/
}
