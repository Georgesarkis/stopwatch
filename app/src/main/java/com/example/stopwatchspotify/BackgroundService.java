package com.example.stopwatchspotify;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.*;
import android.os.*;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class BackgroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this, "Service created!", Toast.LENGTH_LONG).show();

        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, 10000);
            }
        };

        handler.postDelayed(runnable, 15000);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            O.createNotification(this);
        } else {
            PreO.createNotification(this);
        }
    }

    @Override
    public void onDestroy() {
        //handler.removeCallbacks(runnable);
        super.onDestroy();
        Toast.makeText(this, "Service stopped", Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //startForeground();
        return START_STICKY;
    }
}