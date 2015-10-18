package cn.andthink.actionlibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;

/**
 * Created by wuhaiyang on 2015/7/17.
 */
public abstract class BaseActionbarActivity extends ActionBarActivity{

    public static Activity mActivity ; //判断当前activity是否处于激活状态
    protected ActionBar mActionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onSaveInstance(savedInstanceState);
        mActionBar = getSupportActionBar();

        initView();

        operateBusiness();
    }
    protected void operateBusiness() {}
    protected abstract void initView();
    protected void onSaveInstance(Bundle savedInstanceState) {}
    /**
     * 界面恢复焦点的时候回调
     */
    @Override
    protected void onResume() {
        super.onResume();
        mActivity = this;
    }
    @Override
    protected void onPause() {
        super.onPause();
        mActivity = null;
    }

}
