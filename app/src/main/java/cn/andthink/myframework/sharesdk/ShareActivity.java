package cn.andthink.myframework.sharesdk;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.andthink.myframework.R;
import cn.andthink.sharesdk.OnekeyShare;
import cn.andthink.sharesdk.OnekeyShareTheme;
import cn.sharesdk.framework.ShareSDK;


public class ShareActivity extends ActionBarActivity {


    private RelativeLayout mRelativeLayout;
    private PullToRefreshListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mListView = new PullToRefreshListView(this);
        mListView.setMode(PullToRefreshBase.Mode.BOTH);
        List<String> mDatas = new ArrayList<>();


        for (int i = 0; i < 20; i++) {
            mDatas.add(i + "");
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mDatas);

        mListView.setAdapter(adapter);

        setContentView(mListView);


        mListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.w("TAG", "调用了onPullDownToRefresh方法");
                longRunning();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                Log.w("TAG", "调用了onPullUpToRefresh方法");
            }
        });
        mListView.setRefreshing();
       /* setContentView(R.layout.activity_share);

        mRelativeLayout = (RelativeLayout) findViewById(R.id.rl_view);

        ShareSDK.initSDK(this);
        ShareSDK.setConnTimeout(20000);
        ShareSDK.setReadTimeout(20000);

        findViewById(R.id.btn_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });*/
    }

    private void longRunning() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mListView.onRefreshComplete();
                    }
                });
            }
        }.start();
    }

    private void share() {
        OnekeyShare oks = new OnekeyShare();
        oks.setTitle("this is fucking Title");
        oks.setText("this is fucking Text");
        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/05/21/oESpJ78_533x800.jpg");
        oks.setUrl("http://www.mob.com");
        oks.setVenueName("北京");
        oks.setVenueDescription("this is fucking beautiful place");
        oks.setSilent(false);
        oks.setTheme(OnekeyShareTheme.CLASSIC);
        oks.setDialogMode();

        oks.disableSSOWhenAuthorize();

        oks.setEditPageBackground(mRelativeLayout);


        oks.show(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
