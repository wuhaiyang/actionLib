package cn.andthink.actionlibrary;

import android.app.Application;
import android.os.Handler;

/**
 * Created by wuhaiyang on 2015/7/18.
 */
public class BaseApplication extends Application {

    private static BaseApplication application;
    private static int mainTid ;
    private static Handler mHandler ;
    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        mainTid = android.os.Process.myTid();
        mHandler = new Handler();
    }

    public static BaseApplication getApplication() {
        return application;
    }

    public static int getMainTid() {
        return mainTid;
    }

    public static Handler getHandler() {
        return mHandler;
    }
}
