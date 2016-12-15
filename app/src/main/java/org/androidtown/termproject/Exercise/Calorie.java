package org.androidtown.termproject.Exercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.androidtown.termproject.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calorie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calorie);

        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMdd");
        String DBDay = simpleDateFormat2.format(date)+"Walk.db";

        final DBHelper_Exercise dbHelper = new DBHelper_Exercise(getApplicationContext(), DBDay, null, 1);
        String ExerciseTime = Long.toString(dbHelper.timeResult()/60) + "분 " + Long.toString(dbHelper.timeResult()%60) + "초";

        Button insert = (Button) findViewById(R.id.insert);

        final TextView calshow = (TextView) findViewById(R.id.calshow);

        final EditText etDate = (EditText) findViewById(R.id.date);
        final EditText etItem = (EditText) findViewById(R.id.item);
        etDate.setText(ExerciseTime);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long Time = dbHelper.timeResult()/6;
                long weight = 0;
                weight = Long.parseLong(etItem.getText().toString());

                double cal = cal(Time, weight);
                cal = Double.parseDouble(String.format("%.2f",cal));

                calshow.setText(Double.toString(cal) + "kcal");

            }
        });
    }

    public double cal(long time, long weight) {
        double cal = 0 ;
        if(weight >= 55 && weight <= 65){
            cal = 0.54*time;
        }
        else if(weight > 65 && weight <= 75){
            cal = 0.64*time;
        }
        else if(weight > 75 && weight <= 85){
            cal = 0.73*time;
        }
        else if(weight > 85){
            cal = 0.82*time;
        }
        else if(weight < 55){
            cal = 0.44*time;
        }

        return cal;
    }
}
