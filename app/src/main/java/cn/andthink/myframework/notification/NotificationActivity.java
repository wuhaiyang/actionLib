package cn.andthink.myframework.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;


import cn.andthink.myframework.R;

/**
 * Created by wuhaiyang on 2015/7/30.
 */
public class NotificationActivity extends FragmentActivity implements View.OnClickListener {


    private static final int NOTIFICATION_ID_1 = 0;
    private static final int NOTIFICATION_ID_2 = 1;
    private static final int NOTIFICATION_ID_3 = 2;
    private static final int NOTIFICATION_ID_4 = 3;
    private static final int NOTIFICATION_ID_5 = 4;
    private static final int NOTIFICATION_ID_6 = 5;
    private static final int NOTIFICATION_ID_7 = 6;
    private static final int NOTIFICATION_ID_8 = 7;
    private static final int[] btns = new int[]{R.id.btn1, R.id.btn2,
            R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8,
            R.id.btn9};
    private Bitmap icon;
    private NotificationManager mNotificationManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        findView();

        initApi();

        Intent intent1 = new Intent(this, OtherActivity.class);
        startActivity(intent1);

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private boolean flag = true;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = new Intent();
            intent.setAction("cn.andaction.test");
            sendBroadcast(intent);
            if (flag) {
                sendEmptyMessageDelayed(0, 3000);
                flag = false;
            }
        }
    };

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }


    private void initApi() {
        icon = android.graphics.BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void findView() {
        for (int btn : btns) {
            findViewById(btn).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                showNormal();
                break;
            case R.id.btn2:
                showBigView_Text();
                break;
            case R.id.btn3:
                showBigView_Pic();
                break;
            case R.id.btn4:
                showBigView_Inbox();
                break;
            case R.id.btn5:
                showCustomView();
                break;
            case R.id.btn6:
                backApp();
                break;
            case R.id.btn7:
                backScreen();
                break;
            case R.id.btn8:
                showProgressBar();
                break;
            case R.id.btn9:
                dismiss();
                break;
            default:
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void dismiss() {
        mNotificationManager.cancelAll();
    }

    private void showProgressBar() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(
                this);
        builder.setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("showProgressBar").setContentInfo("contentInfo")
                .setOngoing(true).setContentTitle("ContentTitle")
                .setContentText("ContentText");
        new Thread(new Runnable() {

            @Override
            public void run() {
                int progress = 0;
                for (progress = 0; progress < 100; progress += 5) {
                    builder.setProgress(100, progress, false);
                    mNotificationManager.notify(NOTIFICATION_ID_7, builder.build());
                    try {
                        // Sleep for 5 seconds
                        Thread.sleep(2 * 1000);
                    } catch (InterruptedException e) {
                        System.out.println("sleep failure");
                    }
                }
                builder.setContentTitle("Download complete")
                        .setProgress(0, 0, false).setOngoing(false);
                mNotificationManager.notify(NOTIFICATION_ID_7, builder.build());
            }
        }).start();
    }

    private void backScreen() {
        Intent notifyIntent = new Intent(this, SpecialActivity.class);
        // Sets the Activity to start in a new, empty task
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        // Creates the PendingIntent
        PendingIntent notify_Intent = PendingIntent.getActivity(this, 0,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("backScreen").setContentInfo("contentInfo")
                .setContentTitle("ContentTitle").setContentText("ContentText")
                .setContentIntent(notify_Intent).setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL).build();
        mNotificationManager.notify(NOTIFICATION_ID_6, notification);
        this.finish();
    }

    private void backApp() {
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack
        stackBuilder.addParentStack(OtherActivity.class);
        // Adds the Intent to the top of the stack
        Intent resultIntent = new Intent(this, OtherActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        // Gets a PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("backApp").setContentInfo("contentInfo")
                .setContentTitle("ContentTitle").setContentText("ContentText")
                .setContentIntent(resultPendingIntent).setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL).build();
        mNotificationManager.notify(NOTIFICATION_ID_5, notification);
        this.finish();
    }

    private void showCustomView() {
        RemoteViews remoteViews = new RemoteViews(getPackageName(),
                R.layout.custom_notification);
        Intent intent = new Intent(this, TestMusicControl.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.paly_pause_music,
                pendingIntent);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContent(remoteViews).setSmallIcon(R.mipmap.music_icon)
                .setLargeIcon(icon).setOngoing(true)
                .setTicker("music is playing");
        mNotificationManager.notify(NOTIFICATION_ID_8, builder.build());
    }

    private void showBigView_Inbox() {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("this is a BigContentTitle").setSummaryText(
                "this is a SummaryText");
        for (int i = 0; i < 5; i++)
            inboxStyle.addLine("news:" + i);
        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("this is a ticker")
                .setContentInfo("this is a content info")
                .setContentTitle("this is a content title")
                .setContentText("this is a content text")
                .setStyle(inboxStyle)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();
        mNotificationManager.notify(NOTIFICATION_ID_4, notification);
    }

    private void showBigView_Pic() {
        NotificationCompat.BigPictureStyle pictureStyle = new NotificationCompat.BigPictureStyle();
        pictureStyle.setBigContentTitle("this is a big content Title")
                .setSummaryText("this is a big SummaryText").bigPicture(icon);
        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("this is a ticker")
                .setContentInfo("this is a content info")
                .setContentTitle("this is a content title")
                .setContentText("this is a content text")
                .setStyle(pictureStyle)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();
        mNotificationManager.notify(NOTIFICATION_ID_3, notification);
    }

    private void showBigView_Text() {
        NotificationCompat.BigTextStyle textStyle = new NotificationCompat.BigTextStyle();
        textStyle.setBigContentTitle("this is a big content title")
                .setSummaryText("this is a summaryText")
                .bigText("i am a fuck big text tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon).setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("this is a ticker")
                .setContentInfo("this is a content info")
                .setContentTitle("this is a content title")
                .setContentText("this is a content text")
                .setStyle(textStyle)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();
        mNotificationManager.notify(NOTIFICATION_ID_2, notification);
    }

    private void showNormal() {

        Notification notification = new NotificationCompat.Builder(this)
                .setLargeIcon(icon)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("this is a ticker")
                .setContentInfo("this is a content info")
                .setContentTitle("this is a content title")
                .setContentText("this is a content text")
                .setNumber(++messageNum)
                .setAutoCancel(true).setDefaults(Notification.DEFAULT_ALL)
                .build();
        mNotificationManager.notify(NOTIFICATION_ID_1, notification);
    }

    private static int messageNum = 0;
}
