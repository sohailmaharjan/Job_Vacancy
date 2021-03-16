package com.job.jobvacancy;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class JobPostNotification extends Application {


    public static final String CHANNEL_1 = "Channel";
    public static final String CHANNEL_2 = "Channe2";

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    public void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel1 = new NotificationChannel(CHANNEL_1,
                    "Channel 1", NotificationManager.IMPORTANCE_HIGH);

            channel1.setDescription("This is channel 1");

            NotificationChannel channel2 = new NotificationChannel(CHANNEL_2,
                    "Channel 2", NotificationManager.IMPORTANCE_LOW);

            channel2.setDescription("This is channel 2");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
            manager.createNotificationChannel(channel2);

        }
    }

}



