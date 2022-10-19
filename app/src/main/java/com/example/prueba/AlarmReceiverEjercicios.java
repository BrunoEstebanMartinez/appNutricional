package com.example.prueba;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class AlarmReceiverEjercicios extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String event = intent.getStringExtra("event_E");
        String time  = intent.getStringExtra("time_E");
        int notId = intent.getIntExtra("id_E", 0);
        Intent activityIntent = new Intent(context, Ejercicios.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, activityIntent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = "channel_idE";
        CharSequence name = "channel_nameE";
        String descruption = "descriptionE";
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(channelId,name, NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription(descruption);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(context,channelId)
                .setSmallIcon(R.drawable.ic_stat_mood_notification)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentTitle(event)
                .setContentText(time)
                .setDeleteIntent(pendingIntent)
                .setGroup("Group_calendar_viewE")
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(notId,notification);


    }
    }

