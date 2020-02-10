package com.example.stopwatchspotify.Model.Helper;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.stopwatchspotify.R;
import com.example.stopwatchspotify.View.MainActivity;


public class NotificationForO {
    private static final int NOTIF_ID = 1;
    public static final String CHANNEL_ID = "Channel_Id";

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createNotification(Service context) {
        String channelId = createChannel(context);
        Notification notification = buildNotification(context, channelId);
        context.startForeground(NOTIF_ID, notification);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static Notification buildNotification(Service context, String channelId) {
        Intent resultIntent = new Intent(context, MainActivity.class);
        PendingIntent P = PendingIntent.getActivity(context,2,resultIntent,PendingIntent.FLAG_UPDATE_CURRENT);
        return new Notification.Builder(context, channelId)
                .setContentTitle("Stopwatch")
                .setContentIntent(P)
                .setStyle(new Notification.BigTextStyle())
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    private static String createChannel(Service ctx) {
        NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        CharSequence channelName = "Playback channel";
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, channelName, importance);

        notificationManager.createNotificationChannel(notificationChannel);
        return CHANNEL_ID;
    }

}