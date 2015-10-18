package cn.andthink.actionlibrary.holder;

import android.view.View;

import com.lidroid.xutils.BitmapUtils;

import cn.andthink.actionlibrary.utils.BitmapHelper;

/**
 * Created by wuhaiyang on 2015/7/29.
 */
public abstract class BaseHolder<Data> {
    private View convertView;
    private Data mData;
    protected BitmapUtils mBitmapUtils;

    public BaseHolder() {
        convertView = initView();
        convertView.setTag(this);
        mBitmapUtils = BitmapHelper.getInstance();
    }
    public void bindData(Data data){
        this.mData = data;
        refreshView();
    }
    public View getConvertView() {
        return convertView;
    }
    protected abstract void refreshView();
    protected abstract View initView();
}
