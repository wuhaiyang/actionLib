package cn.andthink.myframework;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import cn.bingoogolapple.refreshlayout.BGAMoocStyleRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;
import cn.bingoogolapple.refreshlayout.BGARefreshViewHolder;

/**
 * Created by wuhaiyang on 2015/7/25.
 */
public class RefreshListViewActivity extends FragmentActivity implements BGARefreshLayout.BGARefreshLayoutDelegate, AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {
    private BGARefreshLayout mRefreshLayout;
    private ListView lv_listview_data;

    private List<String> mDatas;
    private MyTask myTask;

    private RelativeLayout mRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.rl_listview_refresh);
        lv_listview_data = (ListView) findViewById(R.id.lv_listview_data);
        mDatas = new ArrayList<>();
        myTask = new MyTask();
        for (int i = 0; i < 5; i++) {
            mDatas.add(i + "项");
        }
        initRefreshAttr();
        lv_listview_data.setOnItemClickListener(this);
        lv_listview_data.setOnItemLongClickListener(this);

        MyAdapter adapter = new MyAdapter();
        lv_listview_data.setAdapter(adapter);

        mRelativeLayout.getRight();
    }

    private void initRefreshAttr() {
        mRefreshLayout.setDelegate(this);
        BGAMoocStyleRefreshViewHolder moocStyleRefreshViewHolder = new BGAMoocStyleRefreshViewHolder(this, true);
        moocStyleRefreshViewHolder.setUltimateColor(getResources().getColor(R.color.custom_imoocstyle));
        moocStyleRefreshViewHolder.setOriginalBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.custom_mooc_icon));
//        moocStyleRefreshViewHolder.setLoadMoreBackgroundColorRes(R.color.custom_imoocstyle);
        moocStyleRefreshViewHolder.setSpringDistanceScale(0.2f);
        moocStyleRefreshViewHolder.setLoadingMoreText("正在加载更多");

//        moocStyleRefreshViewHolder.setRefreshViewBackgroundColorRes(R.color.custom_imoocstyle);
        mRefreshLayout.setRefreshViewHolder(moocStyleRefreshViewHolder);
        // mRefreshLayout.setIsShowLoadingMoreView(false);
//        mRefreshLayout.setCustomHeaderView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mRefreshLayout.beginRefreshing();
    }
    private class MyAdapter extends BaseAdapter {

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
            TextView textView = new TextView(RefreshListViewActivity.this);
            textView.setHeight(70);
            String itemString = mDatas.get(position);
            textView.setGravity(Gravity.CENTER);
            textView.setText(itemString);
            return textView;
        }
    }
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout bgaRefreshLayout) {
    }
    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout bgaRefreshLayout) {
        myTask.execute(true);
        return true;
    }
    private class MyTask extends AsyncTask<Boolean, Void, Void> {
        private boolean isLoaddingMore;

        @Override
        protected Void doInBackground(Boolean... params) {
            SystemClock.sleep(3000);
            isLoaddingMore = params[0];
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (isLoaddingMore) {
                List<String> tmp = new ArrayList<>();
                for (int i = 100; i < 110; i++) {
                    tmp.add(i + "向");
                }
                mDatas.addAll(tmp);
                lv_listview_data.deferNotifyDataSetChanged();
                mRefreshLayout.endLoadingMore();
            } else mRefreshLayout.endRefreshing();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "onItemLongClick" + position, Toast.LENGTH_SHORT).show();
        return true;
    }
}
