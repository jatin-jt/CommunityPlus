package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EarnActivity extends AppCompatActivity {

    TextView earnTitle, earnDesc, earnPoints, earnLoc, earnDur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn);

        earnTitle = (TextView) findViewById(R.id.earn_title);
        earnDur = (TextView) findViewById(R.id.earn_duration);
        earnDesc = (TextView) findViewById(R.id.earn_desc);
        earnLoc = (TextView) findViewById(R.id.earn_location);
        earnPoints = (TextView) findViewById(R.id.earn_points);


    }
}
