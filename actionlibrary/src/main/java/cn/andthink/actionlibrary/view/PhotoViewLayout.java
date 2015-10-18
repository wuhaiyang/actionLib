package cn.andthink.actionlibrary.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;

import cn.andthink.actionlibrary.R;
import cn.andthink.actionlibrary.utils.BitmapHelper;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by wuhaiyang on 2015/7/19.
 */
public class PhotoViewLayout extends LinearLayout implements View.OnClickListener {

    private ProgressBar pb_loadding;
    private PhotoView mPhotoView;

    private OnClickListener mListener;
    private BitmapUtils mBitmapUtils;

    public PhotoViewLayout(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.view_photopreview,this,true);
        pb_loadding = (ProgressBar) view.findViewById(R.id.pb_loadding);
        mPhotoView = (PhotoView) view.findViewById(R.id.pv_imageview);
        mBitmapUtils = BitmapHelper.getInstance();
        mPhotoView.setOnClickListener(this);
    }
    public PhotoViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public PhotoViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    public void loadImage (String imgUrl){
        mBitmapUtils.display(mPhotoView, imgUrl, new BitmapLoadCallBack<PhotoView>() {
            @Override
            public void onLoadCompleted(PhotoView photoView, String s, Bitmap bitmap, BitmapDisplayConfig bitmapDisplayConfig, BitmapLoadFrom bitmapLoadFrom) {
                mPhotoView.setImageBitmap(bitmap);
                pb_loadding.setVisibility(GONE);
            }
            @Override
            public void onLoadFailed(PhotoView photoView, String s, Drawable drawable) {
                mPhotoView.setImageResource(R.drawable.default_error);
                pb_loadding.setVisibility(GONE);
            }
        });
    }
    public void setListener(OnClickListener mListener) {
        this.mListener = mListener;
    }
    @Override
    public void onClick(View v) {
        if (null != mListener)
            mListener.onClick(v);
    }
}
