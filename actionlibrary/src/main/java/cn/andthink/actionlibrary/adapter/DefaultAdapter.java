package cn.andthink.actionlibrary.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import cn.andthink.actionlibrary.holder.BaseHolder;

/**
 * Created by wuhaiyang on 2015/7/29.
 */
public abstract class DefaultAdapter<Data> extends BaseAdapter {

    private static final int DEFAULT_ITEM = 0; // default item
    protected List<Data> mDatas;//source data
    protected LayoutInflater mInfalter;
    protected Context mContext;

    public DefaultAdapter(Context context, List<Data> mDatas) {
        this.mDatas = mDatas;
        this.mContext = context;
        mInfalter = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BaseHolder holder = null;
        if (null == convertView) {
            holder = createHolder();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        // get Data maybe position > mDatas.size()
        Data data = mDatas.get(position);
        holder.bindData(data);
        return holder.getConvertView();
    }
    protected abstract BaseHolder createHolder();
}
