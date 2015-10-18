package cn.andthink.myframework.notification;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import cn.andthink.myframework.R;

public class OtherActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_other);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		Log.w("TAG","执行了onNewIntent方法");
		super.onNewIntent(intent);
	}
}
