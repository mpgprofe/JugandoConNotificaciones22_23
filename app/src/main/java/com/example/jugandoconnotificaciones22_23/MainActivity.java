package com.example.jugandoconnotificaciones22_23;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String ID_CANAL = "Nombre del canal";
    private static final int CODIGO_RESPUESTA = 123;
    Button buttonNotificacion, buttonNotificacion2, buttonNotificacion3,buttonNotificacionFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String mensaje = getIntent().getExtras().get("IDENTIFICADOR").toString();
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
        }


        buttonNotificacion = findViewById(R.id.buttonNotificacion);
        buttonNotificacion2 = findViewById(R.id.buttonNotificacion2);
        buttonNotificacion3 = findViewById(R.id.buttonNotificacion3);
        buttonNotificacionFoto=findViewById(R.id.buttonNotificacionFoto);

        buttonNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarNotificacion();
            }
        });
        buttonNotificacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarNotificacionTextoLargo();
            }
        });

        buttonNotificacion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lanzarNotificacionConMuchoTexto();
            }
        });
        buttonNotificacionFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lazarNotificacionConFoto();
            }
        });
    }

    private void lazarNotificacionConFoto() {
        String idChannnel = "Canal 4";
        String nombreCanal = "Canal con Foto";

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);


        builder.setSmallIcon(R.drawable.albert).
                setContentTitle("Ejemplo de notificacion con foto").
                setAutoCancel(false).
                setContentText("Aquí tenemos notificación con imagen");

       NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
       Bitmap bitmapAlbert = BitmapFactory.decodeResource(getResources(),R.drawable.albert);
       bigPictureStyle.bigPicture(bitmapAlbert);
       bigPictureStyle.setBigContentTitle("Albert, un tío listo");
       bigPictureStyle.setSummaryText("Sabía mucho este hombre");

        builder.setStyle(bigPictureStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(idChannnel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannnel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }
        notificationManager.notify(4, builder.build());
    }

    private void lanzarNotificacionConMuchoTexto() {
        String idChannnel = "Canal 3";
        String nombreCanal = "Canal Texto  muy largo";

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);


        builder.setSmallIcon(R.drawable.albert).
                setContentTitle("Ejemplo de notificacion").
                setAutoCancel(false).
                setContentText("Aquí tenemos el texto muy largo");

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle("Esta notificación para mostrar mucha información");
        inboxStyle.addLine("Aquí va una primera línea");
        inboxStyle.addLine("Aquí otra línea más.");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("Probamos Ctrl+D para copiar líneas");
        inboxStyle.addLine("El profesor ya se cansó de poner líneas");

        builder.setStyle(inboxStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(idChannnel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannnel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }
        notificationManager.notify(3, builder.build());


    }

    private void lanzarNotificacionTextoLargo() {
        String idChannnel = "Canal 2";
        String nombreCanal = "Canal Texto largo";

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);


        builder.setSmallIcon(R.drawable.albert).
                setContentTitle("Ejemplo de notificacion").
                setAutoCancel(false).
                setContentText("Aquí tenemos el texto cortito");

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Esta cabecera tiene mucho más texto");
        bigTextStyle.bigText("Albert Einstein (alemán: /ˈalbɛɐ̯t ˈʔaɪnʃtaɪn/; Ulm, Imperio alemán; 14 de marzo de 1879-Princeton, Estados Unidos; 18 de abril de 1955) fue un físico alemán de origen judío, nacionalizado después suizo, austriaco y estadounidense. Se le considera el científico más importante, conocido y popular del siglo XX.1\u200B2\u200B\n" +
                "\n" +
                "En 1905, cuando era un joven físico desconocido, empleado en la Oficina de Patentes de Berna, publicó su teoría de la relatividad especial. En ella incorporó, en un marco teórico simple fundamentado en postulados físicos sencillos, conceptos y fenómenos estudiados antes por Henri Poincaré y Hendrik Lorentz. Como una consecuencia lógica de esta teoría, dedujo la ecuación de la física más conocida a nivel popular: la equivalencia masa-energía, E=mc². Ese año, publicó otros trabajos que sentarían algunas de las bases de la física estadística y de la mecánica cuántica.\n" +
                "\n" +
                "En 1915, presentó la teoría de la relatividad general, en la que reformuló por completo el concepto de la gravedad.3\u200B Una de las consecuencias fue el surgimiento del estudio científico del origen y la evolución del universo por la rama de la física denominada cosmología. En 1919, cuando las observaciones británicas de un eclipse solar confirmaron sus predicciones acerca de la curvatura de la luz, fue idolatrado por la prensa.4\u200B Einstein se convirtió en un icono popular de la ciencia mundialmente famoso, un privilegio al alcance de muy pocos científicos.5\u200B ");
        bigTextStyle.setSummaryText("Un tipo listo");

        builder.setStyle(bigTextStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(idChannnel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannnel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }
        notificationManager.notify(2, builder.build());


    }

    private void lanzarNotificacion() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_foreground).
                setContentTitle("Ejemplo de notificacion").
                setAutoCancel(false).
                setContentText("Aquí pondremos el texto de nuestra notificación");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String idChannnel = "Canal 1";
            String nombreCanal = "Canal Florencio Pintado";
            NotificationChannel notificationChannel = new NotificationChannel(idChannnel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);


            builder.setChannelId(idChannnel);

            notificationManager.createNotificationChannel(notificationChannel);

        } else {
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

        builder.setAutoCancel(true);//Se borra al pulsar sobre la notificación

        Notification notification = builder.build();
        notificationManager.notify(1, notification);
    }
}














