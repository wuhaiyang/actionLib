package cn.andthink.myframework.notification;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by wuhaiyang on 2015/8/10.
 * myframework
 */
public class MyService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent();
                intent.setAction("cn.andaction.test");
                sendBroadcast(intent);
                timer.cancel();
            }
        },3000,1000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
