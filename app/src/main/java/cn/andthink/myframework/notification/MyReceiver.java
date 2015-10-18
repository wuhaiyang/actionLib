package cn.andthink.myframework.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wuhaiyang on 2015/8/10.
 * myframework
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("TAG", "执行了onReceive方法");


        /**
         *
         * 以下为自己整理用户点击广播通知栏的业务逻辑的伪代码
         *
         *     if (!activityStack.isEmpty){
         *       Intent jumpIntent = new Intent(context, OtherActivity.class);
         jumpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
         context.startActivity(jumpIntent);
         *
         *
         *     } else {
         *         // 应用程序已经销毁 任务栈未空
         *         Intent intent1 = new Intent(context，activity1.class); intent1.setFlag(Intent.FLAG_ACTIVITY_NEW_TASK);
         *         ....
         *         Intent targetIntent = new Intent(context,targetActivity.class);targetIntent.setFlag(Intent.FLAG_ACTIVTY_NEW_TASK);
         *         Intent[] intents = new Intent[]{ intent1,....,targetIntent};
         *         context.startActivities(intents);  //
         *
         *        or
         *
         *        Intent targetIntent = new Intent (context,targetActivity.class); targetIntent.setFlag(Intent.FLAG_ACTIVITY_NEW_TASK);
         *        context.startActivities(targetIntent);
         *     }
         *
         *
         */

       /* Intent jumpIntent = new Intent(context, OtherActivity.class);

        jumpIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(jumpIntent);*/


       /* Intent intent1 = new Intent(context, NotificationActivity.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent intent2 = new Intent(context, OtherActivity.class);
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent[] intents = new Intent[]{
                intent1, intent2
        };
        context.startActivities(intents);*/


    }


}
