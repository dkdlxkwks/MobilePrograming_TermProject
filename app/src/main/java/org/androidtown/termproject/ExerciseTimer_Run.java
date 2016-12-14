package org.androidtown.termproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ExerciseTimer_Run extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_timer_run);

        final Chronometer chronometer = (Chronometer) findViewById(R.id.chronometer);
        Button buttonStart = (Button) findViewById(R.id.buttonstart);
        Button buttonStop = (Button) findViewById(R.id.buttonstop);
        Button buttonReset = (Button) findViewById(R.id.buttonreset);
        Button calorie = (Button) findViewById(R.id.calorie);

        final TextView etDate = (TextView) findViewById(R.id.Today);
        final TextView result = (TextView) findViewById(R.id.result2);
        final TextView alltime = (TextView) findViewById(R.id.alltime);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
        etDate.setText(simpleDateFormat.format(date));

        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        String DBDay = simpleDateFormat2.format(date)+"Run.db";

        final DBHelper_Exercise dbHelper = new DBHelper_Exercise(getApplicationContext(), DBDay, null, 1);

        result.setText(dbHelper.getResult());
        alltime.setText(Long.toString(dbHelper.timeResult()/60) + "분 " + Long.toString(dbHelper.timeResult()%60) + "초");

        calorie.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Calorie_Run.class);
                startActivity(intent);
            }
        });

        buttonStart.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
        });

        buttonStop.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.stop();
                dbHelper.insert("Run",(SystemClock.elapsedRealtime()-chronometer.getBase())/1000);
                result.setText(dbHelper.getResult());
                alltime.setText(Long.toString(dbHelper.timeResult()/60) + "분 " + Long.toString(dbHelper.timeResult()%60) + "초");
            }
        });

        buttonReset.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();

                AlertDialog.Builder builder = new AlertDialog.Builder(ExerciseTimer_Run.this);

                builder.setTitle("주의");
                builder.setMessage("오늘의 운동 기록을 삭제하시겠습니까?");
                builder.setIcon(android.R.drawable.ic_dialog_alert);

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                    }
                });

                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        dbHelper.delete("Run");
                        result.setText(dbHelper.getResult());
                        alltime.setText(0 + "분 " + 0 + "초");
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }
}
