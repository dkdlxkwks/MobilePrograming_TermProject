package org.androidtown.termproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.TabHost;


public class MainActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Resources res = getResources();
        // Resource object to get Drawables
        TabHost tabHost = getTabHost();
        // The activity TabHost
        TabHost.TabSpec spec;
        // Resusable TabSpec for each tab
        Intent intent;
        // Reusable Intent for each tab
        // Create an Intent to launch an Activity for the tab (to be reused)
        intent = new Intent().setClass(this, Plan.class);
        // Initialize a TabSpec for each tab and add it to the TabHost
        spec = tabHost.newTabSpec("Plan").setIndicator("Plan",
                res.getDrawable(R.drawable.ic_tab)).setContent(intent);
        tabHost.addTab(spec);/*그룹 리스트 탭 생성*/
        // Do the same for the other tabs
        intent = new Intent().setClass(this, Exercise.class);
        spec = tabHost.newTabSpec("Exercise").setIndicator("Exercise",
                res.getDrawable(R.drawable.ic_tab)).setContent(intent);
        tabHost.addTab(spec); //Setting 탭 생성
        intent = new Intent().setClass(this, Note.class);
        spec = tabHost.newTabSpec("Note").setIndicator("Note",
                res.getDrawable(R.drawable.ic_tab)).setContent(intent);
        tabHost.addTab(spec);//New 탭 생성
        intent = new Intent().setClass(this, Report.class);
        spec = tabHost.newTabSpec("Report").setIndicator("Report",
                res.getDrawable(R.drawable.ic_tab)).setContent(intent);
        tabHost.addTab(spec);
        tabHost.setCurrentTab(0);
    }
}
