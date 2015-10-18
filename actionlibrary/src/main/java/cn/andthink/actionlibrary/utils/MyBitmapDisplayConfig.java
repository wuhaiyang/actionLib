package cn.andthink.actionlibrary.utils;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;

import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.core.BitmapSize;
import com.lidroid.xutils.bitmap.factory.BitmapFactory;
import com.lidroid.xutils.task.Priority;

/**
 * Created by wuhaiyang on 2015/7/18.
 *
 * 使用创建者模式设置加载图片的配置
 */
public class MyBitmapDisplayConfig {

    private MyBitmapDisplayConfig (){

    }
    public static class Builder {
        private BitmapSize mBitmapSize;
        private Animation mAnimation;
        private boolean autoRotation;
        private Bitmap.Config config;
        private BitmapFactory mFactory;
        private Drawable loadFailedDrawable;
        private Drawable loadingDrawable;
        private Priority priority;
        private boolean showOriginal;

        public void setBitmapSize(BitmapSize mBitmapSize) {
            this.mBitmapSize = mBitmapSize;
        }

        public void setAnimation(Animation mAnimation) {
            this.mAnimation = mAnimation;
        }

        public void setAutoRotation(boolean autoRotation) {
            this.autoRotation = autoRotation;
        }

        public void setConfig(Bitmap.Config config) {
            this.config = config;
        }

        public void setFactory(BitmapFactory mFactory) {
            this.mFactory = mFactory;
        }

        public void setLoadFailedDrawable(Drawable loadFailedDrawable) {
            this.loadFailedDrawable = loadFailedDrawable;
        }

        public void setLoadingDrawable(Drawable loadingDrawable) {
            this.loadingDrawable = loadingDrawable;
        }

        public void setPriority(Priority priority) {
            this.priority = priority;
        }

        public void setShowOriginal(boolean showOriginal) {
            this.showOriginal = showOriginal;
        }

        public Builder() {

        }


    }


}
