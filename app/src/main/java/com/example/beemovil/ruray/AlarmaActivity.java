package com.example.beemovil.ruray;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmaActivity extends BroadcastReceiver {

    NotificationCompat.Builder notificacion;
    private static final int idUnica = 51623;
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder mBuilder;
        NotificationManager mNotifyMgr =(NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        int icono = R.mipmap.ic_launcher;


        mBuilder =new NotificationCompat.Builder(context)

                .setSmallIcon(icono)
                .setContentTitle("Tarea Pendiente")
                .setContentText("Es hora de hace la tarea!")
                .setVibrate(new long[] {100, 250, 100, 500})
                .setAutoCancel(true);



        mNotifyMgr.notify(1, mBuilder.build());

        Toast.makeText(context,"Alarma",Toast.LENGTH_SHORT).show();
    }
}
