package cn.andthink.myframework;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wuhaiyang on 2015/7/30.
 */
public class SecondeActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText("ceshi");
        textView.setTextSize(22);
        setContentView(textView);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.entry_from_out,0);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
//
    }
}
