package com.example.postboxoriginal;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class SendNotifications {
    public void createNotificationsChannel(){
        /*if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
            CharSequence name=getString(R.string.channel_name);
            String description=getString(R.string.channel_description);
            int importance= NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel=new NotificationChannel(CHANNEL_ID,name,importance);
            channel.setDescription(description);

            NotificationManager notificationmanager=getSystemService(Notification.class);
            notificationmanager.createNotificationChannel(channel);
        }*/
    }
}
