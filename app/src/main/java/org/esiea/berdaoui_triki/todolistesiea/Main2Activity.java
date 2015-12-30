package org.esiea.berdaoui_triki.todolistesiea;

import android.annotation.TargetApi;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

public class Main2Activity extends ActionBarActivity implements View.OnClickListener {

    Button bBack;
    Button bStart;
    Button bPause;
    Button bReset;
    Chronometer chrono;
    long time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        bStart = (Button) findViewById(R.id.bStart);
        bPause = (Button) findViewById(R.id.bPause);
        bReset = (Button) findViewById(R.id.bReset);
        chrono = (Chronometer)findViewById(R.id.chrono);
        bBack = (Button) findViewById(R.id.bLink);




        bStart.setOnClickListener(this);
        bPause.setOnClickListener(this);
        bReset.setOnClickListener(this);
        bBack.setOnClickListener(this);

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View arg0){
        switch(arg0.getId()){
            case R.id.bStart:
                chrono.setBase(SystemClock.elapsedRealtime() + time);
                chrono.start();
                break;

            case R.id.bPause:
                time = chrono.getBase() - SystemClock.elapsedRealtime();
                chrono.stop();
                //notification();
                break;

            case R.id.bReset:
                chrono.setBase(SystemClock.elapsedRealtime());
                break;

            case R.id.bLink:
                Context context = getApplicationContext();
                CharSequence text = "Ceci est un toast";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
               finish();
                break;
        }

    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void notification(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this).setSmallIcon(R.drawable.ic_launcher).setContentTitle("Notification").setContentText("Reprend ton souffle");
        Intent resultIntent = new Intent(this, Main2Activity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(Main2Activity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
