package org.androidtown.termproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;


public class ExerciseTimer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_timer);

        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        Button buttonStart = (Button) findViewById(R.id.buttonstart);
        Button buttonStop = (Button) findViewById(R.id.buttonstop);
        Button buttonReset = (Button) findViewById(R.id.buttonreset);

        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.start();
            }
        });

        buttonStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.stop();
            }
        });

        buttonReset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
            }
        });

    }
}
