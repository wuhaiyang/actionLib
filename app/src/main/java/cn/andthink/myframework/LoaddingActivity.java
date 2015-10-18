package cn.andthink.myframework;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.LinearLayout;

import org.json.JSONObject;

import java.util.Random;

import cn.andthink.actionlibrary.view.LoaddingPage;

/**
 * Created by wuhaiyang on 2015/7/31.
 */
public class LoaddingActivity extends FragmentActivity {

    private LinearLayout root_view;

    private LoaddingPage mLoaddingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadding);
        root_view = (LinearLayout) findViewById(R.id.root_view);

        mLoaddingPage = new LoaddingPage(this) {
            @Override
            protected View createSuccessView() {
                View view = View.inflate(LoaddingActivity.this, R.layout.act_success_view, null);
                return view;
            }

            @Override
            protected View createEmptyView() {
                View view = View.inflate(LoaddingActivity.this, R.layout.loadding_empty_view, null);
                return view;
            }

            @Override
            protected View createErrorView() {
                View view = View.inflate(LoaddingActivity.this, R.layout.loadding_error_view, null);
                return view;
            }

            @Override
            protected View createLoaddingView() {
                View view = View.inflate(LoaddingActivity.this, R.layout.loadding_view, null);
                return view;
            }

            @Override
            protected void loadData() {
                //

                doTimeAction();
            }
        };
        mLoaddingPage.getErrorView().findViewById(R.id.page_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoaddingPage.loadFirst();
            }
        });
        root_view.addView(mLoaddingPage, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mLoaddingPage.loadFirst();
    }

    private void doTimeAction() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mLoaddingPage.setCurrentStatus(LoaddingPage.STATUS_ERROR);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mLoaddingPage.showPage();
                    }
                });
            }
        }.start();
    }

}
