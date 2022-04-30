package com.evilca.firebaseapp.clases;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.widget.RemoteViews;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.evilca.firebaseapp.MainActivity;
import com.evilca.firebaseapp.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FirebaseMensajes  extends FirebaseMessagingService {

    //Se recibe de ambas formas
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        if(message.getData().size()>0){
            mostrarNotificacion(message.getData().get("title"), message.getData().get("message"));
        }
        if(message.getNotification() != null){
            mostrarNotificacion(message.getNotification().getTitle(),message.getNotification().getBody());
        }
    }

    private RemoteViews getDisenoPersonalizado(String title, String message) {
        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.notificacion);
        remoteViews.setImageViewResource(R.id.imgLogo, R.drawable.logo_reniec);
        remoteViews.setTextViewText(R.id.txtTitulo,title);
        remoteViews.setTextViewText(R.id.txtMensaje, message);
        return remoteViews;
    }


    private void mostrarNotificacion(String title, String message) {

        Intent intent     = new Intent(this, MainActivity.class);
        String channel_id = "web_app_channel";
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channel_id)
                .setSmallIcon(R.drawable.ic_baseline_face_24)
                .setSound(uri)
                .setAutoCancel(true)
                .setVibrate(new long[]{1000,1000,1000,1000,1000})
                .setOnlyAlertOnce(true)
                .setContentIntent(pendingIntent);

        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.JELLY_BEAN){
            builder = builder.setContent(getDisenoPersonalizado(title,message));
        }else {
            builder = builder.setContentTitle(title)
                    .setContentText(message)
                    .setSmallIcon(R.drawable.ic_baseline_face_24);
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(channel_id,"fbapp", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setSound(uri,null);
            notificationManager.createNotificationChannel((notificationChannel));
        }
        notificationManager.notify(0,builder.build());
}



}
