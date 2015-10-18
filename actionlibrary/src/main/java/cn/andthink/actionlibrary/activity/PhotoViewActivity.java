package cn.andthink.actionlibrary.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.LinkedList;
import java.util.List;

import cn.andthink.actionlibrary.R;
import cn.andthink.actionlibrary.utils.AnimationUtil;
import cn.andthink.actionlibrary.view.PhotoViewLayout;

/**
 * Created by wuhaiyang on 2015/7/19.
 */
public class PhotoViewActivity extends Activity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private RelativeLayout layoutTop;
    private ImageButton btnBack;
    private TextView tvPercent;

    private List<String> mPhotos;
    private boolean isUp;
    private int current;

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.activity_photopreview);

        findView();
        btnBack.setOnClickListener(this);
        mViewPager.setOnPageChangeListener(this);

        getExtras();

        bindData();

        overridePendingTransition(R.anim.activity_alpha_action_in, 0); // 渐入效果
    }

    private void bindData() {
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setCurrentItem(current);
    }

    private void getExtras() {
        Bundle bundle = getIntent().getBundleExtra("photoUrls");
        mPhotos = bundle.getStringArrayList("photos");
    }

    private void findView() {
        layoutTop = (RelativeLayout) findViewById(R.id.layout_top_app);
        btnBack = (ImageButton) findViewById(R.id.btn_back_app);
        tvPercent = (TextView) findViewById(R.id.tv_percent_app);
        mViewPager = (ViewPager) findViewById(R.id.vp_base_app);
    }
    private LinkedList<PhotoViewLayout> views = new LinkedList<>();
    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mPhotos.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoViewLayout layout = null;
            if (views.size() > 0 ) {
                layout = views.remove(0);
            } else {
                layout = new PhotoViewLayout(PhotoViewActivity.this);
            }
            layout.setListener(photoItemClickListener);
            layout.loadImage(mPhotos.get(position));
            container.addView(layout);
            return layout;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            PhotoViewLayout layout = (PhotoViewLayout) object;
            views.add(layout);
            container.removeView(layout);
        }
    };
    /** 图片点击事件回调 */
    private View.OnClickListener photoItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (!isUp) {
                new AnimationUtil(getApplicationContext(), R.anim.translate_up)
                        .setInterpolator(new LinearInterpolator()).setFillAfter(true).startAnimation(layoutTop);
                isUp = true;
            } else {
                new AnimationUtil(getApplicationContext(), R.anim.translate_down_current)
                        .setInterpolator(new LinearInterpolator()).setFillAfter(true).startAnimation(layoutTop);
                isUp = false;
            }
        }
    };
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_app){
            finish();
            views.clear();
        }
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }
    @Override
    public void onPageSelected(int position) {
        current = position;
        updatePercent();
    }
    protected void updatePercent() {
        tvPercent.setText((current + 1) + "/" + mPhotos.size());
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
