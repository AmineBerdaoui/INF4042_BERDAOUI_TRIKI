package org.esiea.berdaoui_triki.todolistesiea;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;

public class Chrono extends ActionBarActivity implements View.OnClickListener{

    Button bStart;
    Button bPause;
    Button bReset;
    Chronometer chrono;
    long time = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bStart = (Button) findViewById(R.id.bStart);
        bPause = (Button) findViewById(R.id.bPause);
        bReset = (Button) findViewById(R.id.bReset);
        chrono = (Chronometer)findViewById(R.id.chrono);

        bStart.setOnClickListener(this);
        bPause.setOnClickListener(this);
        bReset.setOnClickListener(this);

    }

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
                break;

            case R.id.bReset:
                chrono.setBase(SystemClock.elapsedRealtime());
                break;

        }

    }
}