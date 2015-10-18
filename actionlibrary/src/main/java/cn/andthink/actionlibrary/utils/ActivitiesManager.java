package cn.andthink.actionlibrary.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by wuhaiyang on 2015/7/17.
 * 管理activity任务栈
 */
public class ActivitiesManager {
    private static Stack<Activity> mActivityStacks;
    private static ActivitiesManager mInstance;

    private ActivitiesManager() {
        mActivityStacks = new Stack<>();
    }

    public static ActivitiesManager getInstance() {
        if (null == mInstance) {
            mInstance = new ActivitiesManager();
        }
        return mInstance;
    }

    public void addActivity(Activity activity) {
        if (null == activity) {
            throw new RuntimeException("please check your Activity which you want to add ActivityStack");
        }
        synchronized (mActivityStacks) {
            mActivityStacks.add(activity);
        }
    }

    public boolean finishActivity(Activity activity) {
        if (null == activity) {
            throw new RuntimeException("please check your Activity which you want to finish");
        }
        synchronized (mActivityStacks) {
            boolean remove = mActivityStacks.remove(activity);
            if (!activity.isFinishing()) activity.finish();
            return remove;
        }
    }

    public void finishActivity(Class clazz) {
        Activity activity = null;
        synchronized (mActivityStacks) {
            for (Activity tmp : mActivityStacks) {
                if (tmp.getClass().equals(clazz)) {
                    activity = tmp;
                }
            }
            if (null != activity) {
                finishActivity(activity);
            }
        }
    }

    public Activity getTopActivity() {
        return mActivityStacks.lastElement();
    }

    public Activity getBottomActivity() {
        return mActivityStacks.firstElement();
    }

    /**
     * @param cls
     * @return
     */
    public Activity getActivityByClass(Class cls) {
        for (Activity activity : mActivityStacks) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    public boolean hasActivity(Class cls) {
        for (Activity activity : mActivityStacks) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return
     */
    public boolean isStackEmpty() {
        if (null == mActivityStacks) {
            return true;
        }
        return mActivityStacks.isEmpty();
    }

    public void finishAll() {
        if (mActivityStacks == null || mActivityStacks.isEmpty()) {
            return;
        }
        for (Activity activity : mActivityStacks) {
            activity.finish();
        }
        mActivityStacks.clear();//释放掉栈的对activity的引用
    }
    public void forceExit(){
        finishAll();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
