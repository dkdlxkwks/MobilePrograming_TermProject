package org.androidtown.termproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExerciseTimer extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_timer);

        final DBHelper_Exercise dbHelper = new DBHelper_Exercise(getApplicationContext(), "Exercise.db", null, 1);

        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        Button buttonStart = (Button) findViewById(R.id.buttonstart);
        Button buttonStop = (Button) findViewById(R.id.buttonstop);
        Button buttonReset = (Button) findViewById(R.id.buttonreset);

        final TextView etDate = (TextView) findViewById(R.id.Today);
        final TextView result = (TextView) findViewById(R.id.result2);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        etDate.setText(simpleDateFormat.format(date));

        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        buttonStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.stop();
                dbHelper.insert("Walk",Long.toString((SystemClock.elapsedRealtime()-chronometer.getBase())/1000));
                result.setText(dbHelper.getResult());
            }
        });

        buttonReset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                dbHelper.delete("Walk");
                result.setText(dbHelper.getResult());
            }
        });

    }
}
