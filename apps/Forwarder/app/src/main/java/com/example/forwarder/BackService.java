package com.example.forwarder;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class BackService extends Service {
    public BackService() {
    }
    private Boolean isrunning;
    private Context context;
    private Thread backgroundThread;

    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId){
//
//        // do your jobs here
//
////        startForeground();
//
//        return super.onStartCommand(intent, flags, startId);
//    }

    @Override
    public void onCreate() {
        this.context=this;
        this.isrunning=false;
        this.backgroundThread=new Thread(myTask);

    }
    private Runnable myTask=new Runnable() {
        @Override
        public void run() {
            System.out.println("background running");
            stopSelf();
        }
    };

    /*private void startForeground() {
        Intent notificationIntent = new Intent(this, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        startForeground(NOTIF_ID, new NotificationCompat.Builder(this,
                NOTIF_CHANNEL_ID) // don't forget create a notification channel first
                .setOngoing(true)
                .setSmallIcon(R.drawable.ic_notify)
                .setContentTitle(getString(R.string.app_name))
                .setContentText("Service is running background")
                .setContentIntent(pendingIntent)
                .build());
    }
*/
    @Override
    public void onDestroy() {
        this.isrunning=false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

      if(!this.isrunning){
          this.isrunning=true;
          this.backgroundThread.start();

      }
      return START_STICKY;
    }
}
