package com.example.stopwatchspotify;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;

import androidx.core.app.NotificationCompat;

@TargetApi(25)
public class PreO {
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    public static void createNotification(Service context) {

        Notification mNotification =
                new NotificationCompat.Builder(context)
                        .setContentTitle("Stopwatch")
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .build();

        context.startForeground(
                NOTIF_ID, mNotification);
    }
}