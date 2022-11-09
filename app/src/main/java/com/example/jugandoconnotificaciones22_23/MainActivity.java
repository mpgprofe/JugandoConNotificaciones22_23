package com.example.jugandoconnotificaciones22_23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String ID_CANAL = "Nombre del canal";
    private static final int CODIGO_RESPUESTA = 123;
    Button buttonNotificacion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras()!=null){
            String mensaje = getIntent().getExtras().get("IDENTIFICADOR").toString();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }


        buttonNotificacion = findViewById(R.id.buttonNotificacion);

        buttonNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarNotificacion();
            }
        });
    }

    private void lanzarNotificacion() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_background).
                setContentTitle("Ejemplo de notificacion").
                setAutoCancel(false).
                setContentText("Aquí pondremos el texto de nuestra notificación");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String idChannnel = "Canal 1";
            String nombreCanal = "Canal Florencio Pintado";
            NotificationChannel notificationChannel = new NotificationChannel(idChannnel,nombreCanal,NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            

            builder.setChannelId(idChannnel);

            notificationManager.createNotificationChannel(notificationChannel);

        }else{
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }
        //Código para dar funcionalidad a la notificación
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("IDENTIFICADOR", "Soy la notificación simple");
        taskStackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(CODIGO_RESPUESTA, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        //Fin código para dar funcionalidad a la notificación


        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }
}














