package com.example.stopwatchspotify.Model.Helper;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.example.stopwatchspotify.R;
import com.example.stopwatchspotify.View.MainActivity;


@TargetApi(25)
public class NotificationForPreO {
    private static final int NOTIF_ID = 1;
    private static final String NOTIF_CHANNEL_ID = "Channel_Id";

    public static void createNotification(Service context) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent P = PendingIntent.getActivity(context,2,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        Notification mNotification =
                new NotificationCompat.Builder(context)
                        .setContentTitle("Stopwatch")
                        .setContentIntent(P)
                        .setStyle(new NotificationCompat.BigTextStyle())
                        .build();

        context.startForeground(NOTIF_ID, mNotification);
    }
}