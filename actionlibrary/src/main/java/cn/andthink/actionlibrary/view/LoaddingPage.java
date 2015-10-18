package cn.andthink.actionlibrary.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Created by wuhaiyang on 2015/7/18.
 * 自定义布局  当进入界面的时候 将加载部分的业务逻辑代码抽取到LoaddingPage中
 *  以后根据业务需求尽量多使用组合控件进行一层外部封装 <br/>
 */
public abstract class LoaddingPage  extends FrameLayout{

    //定义几种常见的状态值
    public static final int STATUS_UNKNOW = 0; //未知状态
    public static final int STATUS_LODDING = 1; //  加载界面
    public static final int STATUS_ERROR = 2;// 加载错误界面
    public static final  int STATUS_EMPTY  = 3;// 服务器数据为空的状态
    public static final  int STATUS_SUCCESS = 4; //加载成功时候的状态


    private  int mCurrentStatus = 0;

    private View loaddingView ;// 加载数据的时候界
    private View errorView;//加载错误的时候所显示的界面
    private View emptyView;// 服务器数据返回为空的情况
    private View successView ;//成功时候状态显示的界面

    public void setCurrentStatus(int mCurrentStatus) {
        this.mCurrentStatus = mCurrentStatus;
    }

    public LoaddingPage(Context context) {
        super(context);
        init();
    }
    public LoaddingPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoaddingPage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init() {
        if (null == loaddingView) {
            loaddingView = createLoaddingView();
            this.addView(loaddingView,new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if (null == errorView) {
            errorView = createErrorView();
            this.addView(errorView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        if (null == emptyView) {
            emptyView = createEmptyView();
            this.addView(emptyView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }
        showPage();//根据不同的状态显示不同的界面
    }

    public void showPage() {
        if (null != loaddingView) {
            loaddingView.setVisibility(mCurrentStatus == STATUS_UNKNOW || mCurrentStatus == STATUS_LODDING ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != errorView) {
            errorView.setVisibility(mCurrentStatus == STATUS_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != emptyView) {
            emptyView.setVisibility(mCurrentStatus == STATUS_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if (null != successView) {
            emptyView.setVisibility(mCurrentStatus == STATUS_SUCCESS ? View.VISIBLE : View.INVISIBLE);
        }
        if (mCurrentStatus == STATUS_SUCCESS) {
            if (null == successView) {
                successView = createSuccessView();
                this.addView(successView, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
            }
            successView.setVisibility(VISIBLE);
        } else {
            if (null != successView) {
                successView.setVisibility(INVISIBLE);
            }
        }
    }

    protected abstract View createSuccessView();

    protected abstract View createEmptyView();

    protected abstract View createErrorView();

    protected abstract View createLoaddingView() ;

    public View getErrorView() {
        return errorView;
    }

    public void loadFirst() {
        if (mCurrentStatus != STATUS_LODDING) {
            mCurrentStatus = STATUS_LODDING;
        }
        showPage();
        loadData();
    }

    protected abstract void loadData();
}
