package com.example.stopwatchspotify.Model.Helper;
import android.app.Service;
import android.content.*;
import android.os.*;
import android.widget.Toast;

import com.example.stopwatchspotify.R;

public class ForegroundService extends Service {

    public Context context = this;
    public Handler handler = null;
    public static Runnable runnable = null;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        //Toast.makeText(this, getString(R.string.ServiceCreatedMessage), Toast.LENGTH_LONG).show();
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {
                handler.postDelayed(runnable, 10000);
            }
        };
        handler.postDelayed(runnable, 15000);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationForO.createNotification(this);
        } else {
            NotificationForPreO.createNotification(this);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, getString(R.string.ServiceStoppedMessage), Toast.LENGTH_LONG).show();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //startForeground();
        return START_STICKY;
    }
}