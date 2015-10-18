package cn.andthink.myframework;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by wuhaiyang on 2015/7/31.
 */
public class AppToolBarLayout extends RelativeLayout {

    private Button btn_back;
    private TextView tv_title;
    private TextView tv_right;
    public AppToolBarLayout(Context context) {
        super(context);
        initView(context);
    }

    public AppToolBarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.tool_bar, this);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_right = (TextView) view.findViewById(R.id.tv_right);
    }

    public AppToolBarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }
}
