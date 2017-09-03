package com.complus.community;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ComplaintActivity extends AppCompatActivity {

    TextView complaintTitle, complaintDesc, complaintDate, complaintCat, complaintThreat;
    ImageView complaintPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);

        complaintTitle = (TextView) findViewById(R.id.complaint_title);
        complaintCat = (TextView) findViewById(R.id.complaint_cat);
        complaintDesc = (TextView) findViewById(R.id.complaint_desc);
        complaintDate = (TextView) findViewById(R.id.complaint_date);
        complaintThreat = (TextView) findViewById(R.id.complaint_threat);

        complaintPic = (ImageView) findViewById(R.id.complaint_pic);



    }
}
