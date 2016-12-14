package org.androidtown.termproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExerciseRecord extends AppCompatActivity {
    String year1 = "";
    String month = "";
    String day = "";

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exerciserecord);

        mContext = this;

        final TextView year = (TextView) findViewById(R.id.year);
        final TextView alltime = (TextView) findViewById(R.id.alltime);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        // 출력될 포맷 설정
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        year.setText(simpleDateFormat.format(date)+"년");
        year1 = simpleDateFormat.format(date);

        Spinner s1 = (Spinner)findViewById(R.id.spinner10);
        Spinner s2 = (Spinner)findViewById(R.id.spinner11);

        final TextView result = (TextView) findViewById(R.id.result2);

        Button button = (Button)findViewById(R.id.button3);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                month = (String)parent.getItemAtPosition(position);
                ((ExerciseRecord)(ExerciseRecord.mContext)).onResume();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                day = (String)parent.getItemAtPosition(position);
                ((ExerciseRecord)(ExerciseRecord.mContext)).onResume();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                DBHelper_Exercise dbHelper = new DBHelper_Exercise(getApplicationContext(), year1+month+day+"Walk.db", null, 1);
                DBHelper_Exercise dbHelper2 = new DBHelper_Exercise(getApplicationContext(), year1+month+day+"Run.db", null, 1);
                result.setText(dbHelper.getResult() + "\n" + dbHelper2.getResult());
                alltime.setText("  Walk "+Long.toString(dbHelper.timeResult()/60) + "분 " + Long.toString(dbHelper.timeResult()%60) + "초"+"\n  Run   "+Long.toString(dbHelper2.timeResult()/60) + "분 " + Long.toString(dbHelper2.timeResult()%60) + "초"
                );
            }
        });
    }
}
