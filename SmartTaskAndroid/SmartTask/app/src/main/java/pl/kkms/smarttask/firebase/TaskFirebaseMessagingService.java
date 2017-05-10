package pl.kkms.smarttask.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import pl.kkms.smarttask.MainActivity;
import pl.kkms.smarttask.R;

/**
 * Created by Damian on 2017-05-05.
 */

public class TaskFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MesagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "FROM: " + remoteMessage.getFrom());

        // CHECK IF MESSAGE CONTAINS DATA
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "MESSAGE DATA: " + remoteMessage.getData());
        }

        // CHECK IF THE MESSAGE CONTAINS NOTIFICATION
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "MESSAGE BODY: " + remoteMessage.getNotification().getBody());
            sendNotification(remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(String body) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri notificationSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Firebase Message")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(notificationSound)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notBuilder.build());
    }
}
